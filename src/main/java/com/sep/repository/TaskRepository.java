package com.sep.repository;

import com.sep.model.Task;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TaskRepository {

    private final List<Task> tasks = new ArrayList<>();

    public void createTask(Task task) {
        tasks.add(task);
    }

    public void updateTask(Task task) {
        tasks.removeIf(t -> Objects.equals(t.getTaskId(), task.getTaskId()));
        tasks.add(task);
    }

    public Task getById(Long taskId) {
        return tasks.stream().filter(t -> t.getTaskId().equals(taskId)).findFirst().orElse(null);
    }

    public List<Task> getTasksByAssignee(String assignee) {
        return Optional.of(tasks.stream()
                        .filter(task -> task.getAssignee().equals(assignee))
                        .toList())
                .orElse(Collections.emptyList());
    }

    public List<Task> getTasksByApplicationId(Long applicationId) {
        return Optional.of(tasks.stream()
                        .filter(task -> Objects.equals(applicationId, task.getApplicationId()))
                        .toList())
                .orElse(Collections.emptyList());
    }
}
