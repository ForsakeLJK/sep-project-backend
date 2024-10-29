package com.sep.service;

import com.sep.enums.EventStatusEnum;
import com.sep.model.ApplicationVO;
import com.sep.model.EventApplication;
import com.sep.model.GetApplicationsVO;
import com.sep.model.User;
import com.sep.repository.ApplicationRepository;
import com.sep.repository.UserRepository;
import com.sep.request.CreateApplicationRequest;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
        EventApplication eventApplication = new EventApplication()
                .setApplicationId(idGenService.generateId())
                .setEventStatus(EventStatusEnum.REVIEWING)
                .setEventName(req.getEventName())
                .setEventDesc(req.getEventDesc());
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
                .map(
                        a -> new ApplicationVO()
                                .setApplicationId(a.getApplicationId())
                                .setEventDesc(a.getEventDesc())
                                .setEventStatus(a.getEventStatus().getDesc())
                                .setEventName(a.getEventName())
                ).toList();

        return new GetApplicationsVO().setApplicationList(voList);
    }
}