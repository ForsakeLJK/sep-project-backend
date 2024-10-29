package com.sep.repository;

import com.sep.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepository {
    private final List<User> users = new ArrayList<>();

    @PostConstruct
    public void populateUsers() {
        users.add(new User()
                .setUserRole("HR")
                .setUserName("HR")
                .setDepartment("Human Resource")
                .setPassword("12345"));

        users.add(new User()
                .setUserRole("FM")
                .setUserName("FM")
                .setDepartment("Finance")
                .setPassword("12345"));

        users.add(new User()
                .setUserRole("CS")
                .setUserName("CS")
                .setDepartment("Administration")
                .setPassword("12345"));

        users.add(new User()
                .setUserRole("PM")
                .setUserName("PM")
                .setDepartment("Production")
                .setPassword("12345"));

        users.add(new User()
                .setUserRole("SM")
                .setUserName("SM")
                .setDepartment("Service")
                .setPassword("12345"));

        users.add(new User()
                .setUserRole("SCS")
                .setUserName("SCS")
                .setDepartment("Administration")
                .setPassword("12345"));

        users.add(new User()
                .setUserRole("AM")
                .setUserName("AM")
                .setDepartment("Administration")
                .setPassword("12345"));

        users.add(new User()
                .setUserRole("Sub")
                .setUserName("Tobias")
                .setDepartment("Photograph")
                .setPassword("12345"));

        users.add(new User()
                .setUserRole("Sub")
                .setUserName("Adam")
                .setDepartment("Music")
                .setPassword("12345"));

        users.add(new User()
                .setUserRole("Sub")
                .setUserName("Helen")
                .setDepartment("Chef")
                .setPassword("12345"));
    }

    public User queryByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUserName().equals(username))
                .findFirst()
                .orElse(null);
    }

    public List<User> queryByRole(String role) {
        return Optional.of(users.stream()
                        .filter(u -> u.getUserRole().equals(role))
                        .toList())
                .orElse(new ArrayList<>());
    }
}
