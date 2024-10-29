package com.sep.service;

import com.sep.model.*;
import com.sep.repository.ApplicationRepository;
import com.sep.repository.TaskRepository;
import com.sep.request.AssignTaskRequest;
import com.sep.request.CommentTaskRequest;
import com.sep.utils.IdGenerator;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Resource
    private TaskRepository taskRepository;

    @Resource
    private IdGenerator idGenerator;
    @Autowired
    private ApplicationRepository applicationRepository;

    public boolean assignTask(AssignTaskRequest req) {
        long taskId = idGenerator.generateId();

        Task task = new Task()
                .setTaskId(taskId)
                .setTaskName(req.getTaskName())
                .setTaskDesc(req.getTaskDesc())
                .setApplicationId(Long.valueOf(req.getApplicationId()))
                .setAssignee(req.getEmployeeName());

        taskRepository.createTask(task);

        return true;
    }

    public TaskListVO getTaskList(String username, String applicationId) {

        List<Task> taskList = taskRepository.getTasksByApplicationId(Long.valueOf(applicationId));

        List<TaskVO> taskVOList = taskList.stream()
                .map(t -> new TaskVO()
                        .setTaskId(String.valueOf(t.getTaskId()))
                        .setAssignee(t.getAssignee())
                        .setTaskName(t.getTaskName())
                        .setTaskDesc(t.getTaskDesc())
                        .setComment(t.getComment())
                ).toList();

        return new TaskListVO().setTaskList(taskVOList);
    }

    public EmpTaskListVO getEmpTaskList(String username) {
        List<Task> empTaskInfoList = taskRepository.getTasksByAssignee(username);
        List<TaskVO> taskVOList = empTaskInfoList.stream()
                .map(t -> {
                    TaskVO v = new TaskVO();
                    v.setTaskId(String.valueOf(t.getTaskId()));
                    v.setAssignee(t.getAssignee());
                    v.setTaskName(t.getTaskName());
                    v.setTaskDesc(t.getTaskDesc());
                    v.setComment(t.getComment());
                    EventApplication app = applicationRepository.getById(t.getApplicationId());
                    v.setEventName(app.getEventName());
                    return v;
                }).toList();

        return new EmpTaskListVO().setTaskList(taskVOList);
    }

    public boolean commentTask(CommentTaskRequest req) {
        Task task = taskRepository.getById(Long.valueOf(req.getTaskId()));
        if (task == null) {
            return false;
        }

        task.setComment(req.getComment());
        taskRepository.updateTask(task);
        return true;
    }
}
