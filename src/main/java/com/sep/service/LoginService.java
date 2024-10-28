package com.sep.service;

import com.sep.model.User;
import com.sep.repository.UserRepository;
import com.sep.response.LoginResponse;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Resource
    private UserRepository userRepository;

    public LoginResponse login(String username, String password) {
        User user = userRepository.queryByUsername(username);
        if (user == null) {
            return new LoginResponse().setLoginSuccess(false);
        }

        if (!user.getPassword().equals(password)) {
            return new LoginResponse().setLoginSuccess(false);
        }

        return new LoginResponse().setLoginSuccess(true)
                .setUsername(username)
                .setUserRole(user.getUserRole());
    }
}
