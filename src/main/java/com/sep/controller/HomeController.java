package com.sep.controller;

import com.sep.request.LoginRequest;
import com.sep.response.LoginResponse;
import com.sep.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/sep")
public class HomeController {

    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> homePage(@RequestBody LoginRequest req) {
        LoginResponse response = loginService.login(req.getUsername(), req.getPassword());
        return ResponseEntity.ok(response);
    }
}
