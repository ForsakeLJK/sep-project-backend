package com.sep.controller;

import com.alibaba.fastjson.JSONObject;
import com.sep.model.GetApplicationsVO;
import com.sep.model.GetEmpsVO;
import com.sep.model.ReviewApplicationRequest;
import com.sep.model.TaskListVO;
import com.sep.request.AssignTaskRequest;
import com.sep.request.ChangeStatusRequest;
import com.sep.request.CreateApplicationRequest;
import com.sep.request.LoginRequest;
import com.sep.response.LoginResponse;
import com.sep.service.ApplicationService;
import com.sep.service.EmpService;
import com.sep.service.LoginService;
import com.sep.service.TaskService;
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

    @Resource
    private EmpService empService;

    @Resource
    private TaskService taskService;

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

    @PostMapping("/reviewApplication")
    public ResponseEntity<JSONObject> reviewApplication(@RequestBody ReviewApplicationRequest req) {
        boolean success = applicationService.reviewApplication(req);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reviewSuccess", success);
        return ResponseEntity.ok(jsonObject);
    }

    @GetMapping("/employee")
    public ResponseEntity<GetEmpsVO> getEmps(@RequestParam String username, @RequestParam String applicationId) {
        GetEmpsVO getEmpsVO = empService.getAvailableEmps(username, applicationId);
        return ResponseEntity.ok(getEmpsVO);
    }

    @PostMapping("/taskAssign")
    public ResponseEntity<JSONObject> assignTask(@RequestBody AssignTaskRequest req) {
        boolean success = taskService.assignTask(req);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("assignSuccess", success);
        return ResponseEntity.ok(jsonObject);
    }

    @GetMapping("/taskList")
    public ResponseEntity<TaskListVO> getTaskList(@RequestParam String username, @RequestParam String applicationId) {
        TaskListVO taskListVO = taskService.getTaskList(username, applicationId);
        return ResponseEntity.ok(taskListVO);
    }

    @PostMapping("/changeStatus")
    public ResponseEntity<JSONObject> changeStatus(@RequestBody ChangeStatusRequest req) {

        boolean success = applicationService.changeStatus(req);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("changeSuccess", success);
        return ResponseEntity.ok(jsonObject);
    }
}
