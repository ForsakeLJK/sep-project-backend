package com.sep.service;

import com.sep.model.Task;
import com.sep.model.TaskListVO;
import com.sep.model.TaskVO;
import com.sep.repository.TaskRepository;
import com.sep.request.AssignTaskRequest;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Resource
    private TaskRepository taskRepository;

    @Resource
    private IdGenService idGenService;

    public boolean assignTask(AssignTaskRequest req) {
        long taskId = idGenService.generateId();

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
}
