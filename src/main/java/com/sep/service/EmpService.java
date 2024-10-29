package com.sep.service;

import com.sep.model.*;
import com.sep.repository.TaskRepository;
import com.sep.repository.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmpService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private TaskRepository taskRepository;

    public GetEmpsVO getAvailableEmps(String username, String applicationId) {
        List<User> subEmps = userRepository.queryByRole("Sub");
        List<Task> tasks = taskRepository.getTasksByApplicationId(Long.valueOf(applicationId));

        Set<String> occupiedEmp = tasks.stream()
                .map(Task::getAssignee)
                .collect(Collectors.toSet());

        List<EmpVO> availableEmps = subEmps.stream()
                .filter(e -> !occupiedEmp.contains(e.getUserName()))
                .map(u -> new EmpVO().setName(u.getUserName()).setDepartment(u.getDepartment()))
                .toList();

        return new GetEmpsVO().setEmpList(availableEmps);
    }

    public boolean addEmp(RecruitEmpRequest req) {

        User user = new User()
                .setUserRole("Sub")
                .setPassword("12345")
                .setDepartment(req.getDepartment())
                .setUserName(req.getName());

        userRepository.addUser(user);

        return true;
    }
}
