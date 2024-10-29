package com.sep.controller;

import com.alibaba.fastjson.JSONObject;
import com.sep.model.GetApplicationsVO;
import com.sep.request.CreateApplicationRequest;
import com.sep.request.LoginRequest;
import com.sep.response.LoginResponse;
import com.sep.service.ApplicationService;
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

    @Resource
    private ApplicationService applicationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> homePage(@RequestBody LoginRequest req) {
        LoginResponse response = loginService.login(req.getUsername(), req.getPassword());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/createApplication")
    public ResponseEntity<JSONObject> createApplication(@RequestBody CreateApplicationRequest req) {
        boolean success = applicationService.createApplication(req);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("createSuccess", success);
        return ResponseEntity.ok(jsonObject);
    }

    @GetMapping("/applications")
    public ResponseEntity<GetApplicationsVO> getApplications(@RequestParam String username) {
        return ResponseEntity.ok(applicationService.getApplications(username));
    }
}
