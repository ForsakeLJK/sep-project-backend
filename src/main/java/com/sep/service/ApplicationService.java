package com.sep.service;

import com.sep.enums.EventStatusEnum;
import com.sep.model.*;
import com.sep.repository.ApplicationRepository;
import com.sep.repository.UserRepository;
import com.sep.request.CreateApplicationRequest;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ApplicationService {
    @Resource
    private ApplicationRepository applicationRepository;
    @Resource
    private IdGenService idGenService;
    @Resource
    private UserRepository userRepository;

    public boolean createApplication(CreateApplicationRequest req) {
        EventApplication eventApplication = new EventApplication().setApplicationId(idGenService.generateId())
                .setEventStatus(EventStatusEnum.REVIEWING)
                .setEventName(req.getEventName())
                .setEventDesc(req.getEventDesc())
                .setCurrentReviewRole("SCS");
        applicationRepository.createApplication(eventApplication);
        return true;
    }

    public GetApplicationsVO getApplications(String username) {
        User user = userRepository.queryByUsername(username);
        if (user == null) {
            return new GetApplicationsVO().setApplicationList(Collections.emptyList());
        }

        List<EventApplication> allApplications = applicationRepository.getAllApplications();
        List<ApplicationVO> voList = allApplications.stream()
                .sorted(getComparator(user))
                .map(a -> new ApplicationVO()
                        .setApplicationId(String.valueOf(a.getApplicationId()))
                        .setEventDesc(a.getEventDesc())
                        .setEventStatus(a.getEventStatus().getDesc())
                        .setEventName(a.getEventName())
                        .setNeedReview(user.getUserRole().equals(a.getCurrentReviewRole()))
                )

                .toList();

        return new GetApplicationsVO().setApplicationList(voList);
    }

    private Comparator<? super EventApplication> getComparator(User user) {
        if ("PM".equals(user.getUserRole())) {
            return Comparator.comparingInt(app -> app.getEventStatus().getPmPriority());
        }
        return Comparator.comparingInt(app -> app.getEventStatus().getRevPriority());
    }

    public boolean reviewApplication(ReviewApplicationRequest req) {

        User user = userRepository.queryByUsername(req.getUsername());
        if (user == null) {
            return false;
        }

        EventApplication app = applicationRepository.getById(Long.valueOf(req.getApplicationId()));
        if (app == null) {
            return false;
        }

        if ("reject".equals(req.getAction())) {
            app.setEventStatus(EventStatusEnum.REJECTED);
            applicationRepository.updateApplication(app);
            return true;
        } else if ("approve".equals(req.getAction())) {
            String nextRole = findNextReviewRole(app.getCurrentReviewRole());
            if (nextRole == null) {
                app.setEventStatus(EventStatusEnum.OPEN);
                app.setCurrentReviewRole(null);
            } else {
                app.setCurrentReviewRole(nextRole);
            }
            applicationRepository.updateApplication(app);
            return true;

        }
        return false;
    }

    private String findNextReviewRole(String currentRole) {
        if (currentRole == null) {
            return null;
        }
        return switch (currentRole) {
            case "SCS" -> "FM";
            case "FM" -> "AM";
            default -> null;
        };

    }
}