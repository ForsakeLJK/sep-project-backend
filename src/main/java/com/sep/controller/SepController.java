package com.sep.controller;

import com.alibaba.fastjson.JSONObject;
import com.sep.model.*;
import com.sep.request.*;
import com.sep.response.LoginResponse;
import com.sep.service.*;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/sep")
public class SepController {

    @Resource
    private LoginService loginService;

    @Resource
    private ApplicationService applicationService;

    @Resource
    private EmpService empService;

    @Resource
    private TaskService taskService;

    @Resource
    private RequestService requestService;

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

    @GetMapping("/empTaskList")
    public ResponseEntity<EmpTaskListVO> getEmpTaskList(@RequestParam String username) {
        EmpTaskListVO empTaskListVO = taskService.getEmpTaskList(username);
        return ResponseEntity.ok(empTaskListVO);
    }

    @PostMapping("/commentTask")
    public ResponseEntity<JSONObject> commentTask(@RequestBody CommentTaskRequest req) {
        boolean success = taskService.commentTask(req);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("commentSuccess", success);
        return ResponseEntity.ok(jsonObject);
    }

    @GetMapping("/budgetReqList")
    public ResponseEntity<SepReqListVO> getBudgetReqList(@RequestParam String username) {
        SepReqListVO reqListVO = requestService.getBudgetReqList(username);
        return ResponseEntity.ok(reqListVO);
    }

    @GetMapping("/resourceReqList")
    public ResponseEntity<SepReqListVO> getResourceReqList(@RequestParam String username) {
        SepReqListVO reqListVO = requestService.getResourceReqList(username);
        return ResponseEntity.ok(reqListVO);
    }

    @PostMapping("/createReq")
    public ResponseEntity<JSONObject> createReq(@RequestBody CreateSepRequest req) {
        boolean success = requestService.createReq(req);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("createSuccess", success);
        return ResponseEntity.ok(jsonObject);
    }

    @PostMapping("/changeReqStatus")
    public ResponseEntity<JSONObject> changeReqStatus(@RequestBody ChangeReqStatus req) {
        boolean success = requestService.changeStatus(req);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("changeSuccess", success);
        return ResponseEntity.ok(jsonObject);
    }

    @PostMapping("/recruitEmp")
    public ResponseEntity<JSONObject> recruitEmp(@RequestBody RecruitEmpRequest req) {
        boolean success = empService.addEmp(req);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("recruitSuccess", success);
        return ResponseEntity.ok(jsonObject);
    }
}
