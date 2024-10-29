package com.sep.repository;

import com.sep.enums.EventStatusEnum;
import com.sep.model.EventApplication;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ApplicationRepository {
    private final List<EventApplication> applications = new ArrayList<>();

    public void createApplication(EventApplication application) {
        applications.add(application);
    }

    public List<EventApplication> getAllApplications() {
        return new ArrayList<>(applications);
    }

    public EventApplication getById(Long applicationId) {
        return applications.stream()
                .filter(application -> Objects.equals(applicationId, application.getApplicationId()))
                .findFirst()
                .orElse(null);
    }

    public void updateApplication(EventApplication application) {
        deleteById(application.getApplicationId());
        applications.add(application);
    }

    public void deleteById(Long applicationId) {
        applications.removeIf(a -> a.getApplicationId().equals(applicationId));
    }

    @PostConstruct
    public void populateApplications() {
        applications.add(
                new EventApplication()
                        .setApplicationId(123467890L)
                        .setEventName("test")
                        .setEventDesc("test")
                        .setEventStatus(EventStatusEnum.OPEN)
                        .setFinancialComment("comment")
        );
    }
}
