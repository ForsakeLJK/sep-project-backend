package com.sep.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class TaskListVO {
    private List<TaskVO> taskList;
}
