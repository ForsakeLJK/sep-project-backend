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
}
