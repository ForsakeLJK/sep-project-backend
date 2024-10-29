package com.sep.repository;

import com.sep.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepository {
    private final List<User> users = new ArrayList<>();

    @PostConstruct
    public void populateUsers() {
        users.add(new User()
                .setUserRole("HR")
                .setUserName("HR")
                .setPassword("12345"));

        users.add(new User()
                .setUserRole("FM")
                .setUserName("FM")
                .setPassword("12345"));

        users.add(new User()
                .setUserRole("CS")
                .setUserName("CS")
                .setPassword("12345"));

        users.add(new User()
                .setUserRole("PM")
                .setUserName("PM")
                .setPassword("12345"));

        users.add(new User()
                .setUserRole("REVIEWER")
                .setUserName("AM")
                .setPassword("12345"));

        users.add(new User()
                .setUserRole("Sub")
                .setUserName("Sub1")
                .setPassword("12345"));
    }

    public User queryByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUserName().equals(username))
                .findFirst()
                .orElse(null);
    }
}
