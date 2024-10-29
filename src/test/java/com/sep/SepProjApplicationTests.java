package com.sep;

import com.sep.model.*;
import com.sep.request.AssignTaskRequest;
import com.sep.request.ChangeStatusRequest;
import com.sep.request.CommentTaskRequest;
import com.sep.request.CreateApplicationRequest;
import com.sep.response.LoginResponse;
import com.sep.service.*;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SepProjApplicationTests {

    @Resource
    private ApplicationService applicationService;

    @Resource
    private EmpService empService;

    @Resource
    private LoginService loginService;

    @Resource
    private RequestService requestService;

    @Resource
    private TaskService taskService;

    @Test
    void createApplicationTest() {
        CreateApplicationRequest req = new CreateApplicationRequest()
                .setEventName("test")
                .setEventDesc("test");
        boolean success = applicationService.createApplication(req);
        Assertions.assertTrue(success);
    }

    @Test
    void getApplicationsTest() {
        GetApplicationsVO result = applicationService.getApplications("PM");
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.getApplicationList().isEmpty());
    }

    @Test
    void reviewApplicationTest() {
        GetApplicationsVO appVo = applicationService.getApplications("PM");
        ApplicationVO applicationVO = appVo.getApplicationList()
                .get(0);

        ReviewApplicationRequest req = new ReviewApplicationRequest()
                .setApplicationId(applicationVO.getApplicationId())
                .setUsername("SCS")
                .setAction("approve");

        boolean success = applicationService.reviewApplication(req);
        Assertions.assertTrue(success);
    }

    @Test
    void changeApplicationStatusTest() {
        GetApplicationsVO appVo = applicationService.getApplications("PM");
        ApplicationVO applicationVO = appVo.getApplicationList()
                .get(0);

        ChangeStatusRequest req = new ChangeStatusRequest()
                .setUsername("PM")
                .setApplicationId(applicationVO.getApplicationId());

        boolean success = applicationService.changeStatus(req);
        Assertions.assertTrue(success);
    }

    @Test
    void getAvailableEmpsTest() {
        GetApplicationsVO appVo = applicationService.getApplications("PM");
        ApplicationVO applicationVO = appVo.getApplicationList()
                .get(0);

        GetEmpsVO emps = empService.getAvailableEmps("PM", applicationVO.getApplicationId());
        Assertions.assertNotNull(emps);
        Assertions.assertFalse(emps.getEmpList().isEmpty());
    }

    @Test
    void addEmpTest() {
        RecruitEmpRequest req = new RecruitEmpRequest()
                .setDepartment("Music")
                .setName("Poe");
        boolean success = empService.addEmp(req);
        Assertions.assertTrue(success);
    }

    @Test
    void loginTest() {
        LoginResponse resp = loginService.login("HR", "12345");
        Assertions.assertNotNull(resp);
        Assertions.assertEquals("HR", resp.getUsername());
        Assertions.assertEquals("HR", resp.getUserRole());
    }

    @Test
    void getBudgetReqListTest() {
        GetApplicationsVO appVo = applicationService.getApplications("PM");
        ApplicationVO applicationVO = appVo.getApplicationList()
                .get(0);

        CreateSepRequest req = new CreateSepRequest()
                .setType("budget")
                .setApplicationId(applicationVO.getApplicationId())
                .setUsername("PM")
                .setRequestDesc("test")
                .setRequestDesc("test");
        requestService.createReq(req);

        SepReqListVO vo = requestService.getBudgetReqList("FM");
        Assertions.assertNotNull(vo);
        Assertions.assertFalse(vo.getSepReqList().isEmpty());
    }

    @Test
    void getResourceReqListTest() {
        GetApplicationsVO appVo = applicationService.getApplications("PM");
        ApplicationVO applicationVO = appVo.getApplicationList()
                .get(0);

        CreateSepRequest req = new CreateSepRequest()
                .setType("resource")
                .setApplicationId(applicationVO.getApplicationId())
                .setUsername("PM")
                .setRequestDesc("test")
                .setRequestDesc("test");
        requestService.createReq(req);

        SepReqListVO vo = requestService.getResourceReqList("HR");
        Assertions.assertNotNull(vo);
        Assertions.assertFalse(vo.getSepReqList().isEmpty());
    }

    @Test
    void createReqTest() {
        GetApplicationsVO appVo = applicationService.getApplications("PM");
        ApplicationVO applicationVO = appVo.getApplicationList()
                .get(0);

        CreateSepRequest req = new CreateSepRequest()
                .setType("budget")
                .setApplicationId(applicationVO.getApplicationId())
                .setUsername("PM")
                .setRequestDesc("test")
                .setRequestDesc("test");
        boolean success = requestService.createReq(req);
        Assertions.assertTrue(success);
    }

    @Test
    void changeReqStateTest() {
        GetApplicationsVO appVo = applicationService.getApplications("PM");
        ApplicationVO applicationVO = appVo.getApplicationList()
                .get(0);

        CreateSepRequest req = new CreateSepRequest()
                .setType("budget")
                .setApplicationId(applicationVO.getApplicationId())
                .setUsername("PM")
                .setRequestDesc("test")
                .setRequestDesc("test");
        requestService.createReq(req);
        SepReqListVO vo = requestService.getBudgetReqList("FM");
        SepReqVO sepReqVO = vo.getSepReqList()
                .get(0);
        ChangeReqStatus changeReq = new ChangeReqStatus()
                .setAction("approve")
                .setRequestId(sepReqVO.getRequestId())
                .setUsername("FM");
        boolean success = requestService.changeStatus(changeReq);
        Assertions.assertTrue(success);
    }

    @Test
    void assignTaskTest() {
        GetApplicationsVO appVo = applicationService.getApplications("PM");
        ApplicationVO applicationVO = appVo.getApplicationList()
                .get(0);

        AssignTaskRequest req = new AssignTaskRequest()
                .setApplicationId(applicationVO.getApplicationId())
                .setTaskName("test")
                .setTaskDesc("test")
                .setUsername("PM")
                .setEmployeeName("Tobias");
        boolean success = taskService.assignTask(req);
        Assertions.assertTrue(success);
    }

    @Test
    void getTaskListTest() {
        GetApplicationsVO appVo = applicationService.getApplications("PM");
        ApplicationVO applicationVO = appVo.getApplicationList()
                .get(0);

        AssignTaskRequest req = new AssignTaskRequest()
                .setApplicationId(applicationVO.getApplicationId())
                .setTaskName("test")
                .setTaskDesc("test")
                .setUsername("PM")
                .setEmployeeName("Tobias");
        taskService.assignTask(req);

        TaskListVO taskList = taskService.getTaskList("PM", applicationVO.getApplicationId());
        Assertions.assertNotNull(taskList);
        Assertions.assertFalse(taskList.getTaskList().isEmpty());
    }

    @Test
    void getEmpTaskListTest() {
        GetApplicationsVO appVo = applicationService.getApplications("PM");
        ApplicationVO applicationVO = appVo.getApplicationList()
                .get(0);

        AssignTaskRequest req = new AssignTaskRequest()
                .setApplicationId(applicationVO.getApplicationId())
                .setTaskName("test")
                .setTaskDesc("test")
                .setUsername("PM")
                .setEmployeeName("Tobias");
        taskService.assignTask(req);

        EmpTaskListVO taskList = taskService.getEmpTaskList("Tobias");
        Assertions.assertNotNull(taskList);
        Assertions.assertFalse(taskList.getTaskList().isEmpty());
    }

    @Test
    void commentTaskTest() {
        GetApplicationsVO appVo = applicationService.getApplications("PM");
        ApplicationVO applicationVO = appVo.getApplicationList()
                .get(0);

        AssignTaskRequest req = new AssignTaskRequest()
                .setApplicationId(applicationVO.getApplicationId())
                .setTaskName("test")
                .setTaskDesc("test")
                .setUsername("PM")
                .setEmployeeName("Tobias");
        taskService.assignTask(req);
        EmpTaskListVO taskList = taskService.getEmpTaskList("Tobias");
        TaskVO taskVO = taskList.getTaskList().get(0);
        CommentTaskRequest ctr = new CommentTaskRequest()
                .setComment("test")
                .setTaskId(taskVO.getTaskId())
                .setUsername("Tobias");
        boolean success = taskService.commentTask(ctr);
        Assertions.assertTrue(success);
    }
}
