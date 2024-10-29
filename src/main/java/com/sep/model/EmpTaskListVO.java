package com.sep.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@Data
public class EmpTaskListVO {
    private List<TaskVO> taskList;
}
