package com.sep.repository;

import com.sep.model.EventApplication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
                .filter(application -> application.getApplicationId()
                        .equals(applicationId))
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
}
