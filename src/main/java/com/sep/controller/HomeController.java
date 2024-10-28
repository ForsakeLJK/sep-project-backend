package com.sep.controller;

import com.sep.request.LoginRequest;
import com.sep.response.LoginResponse;
import com.sep.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sep")
public class HomeController {

    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> homePage(@RequestBody LoginRequest req) {
        LoginResponse response = loginService.login(req.getUsername(), req.getPassword());
        return buildHttpResp(response, HttpStatus.OK);
    }

    private <T> ResponseEntity<T> buildHttpResp(T res, HttpStatus httpStatus) {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "http://localhost:3000");
        return new ResponseEntity<>(res, headers, httpStatus);
    }

    private <T> ResponseEntity<T> buildHttpResp(T res, MultiValueMap<String, String> headers, HttpStatus httpStatus) {
        headers.add("Access-Control-Allow-Origin", "http://localhost:3000");
        return new ResponseEntity<>(res, headers, httpStatus);
    }
}
