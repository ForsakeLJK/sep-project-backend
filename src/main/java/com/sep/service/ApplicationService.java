package com.sep.service;

import com.sep.enums.EventStatusEnum;
import com.sep.model.EventApplication;
import com.sep.repository.ApplicationRepository;
import com.sep.request.CreateApplicationRequest;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
    @Resource
    private ApplicationRepository applicationRepository;
    @Resource
    private IdGenService idGenService;

    public boolean createApplication(CreateApplicationRequest req) {
        EventApplication eventApplication = new EventApplication()
                .setApplicationId(idGenService.generateId())
                .setEventStatus(EventStatusEnum.REVIEWING)
                .setEventName(req.getEventName())
                .setEventDesc(req.getEventDesc());
        applicationRepository.createApplication(eventApplication);
        return true;
    }
}