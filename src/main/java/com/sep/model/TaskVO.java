package com.sep.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TaskVO {
    private String taskName;
    private String taskDesc;
    private String taskId;
    private String assignee;
    private String comment;
    private String eventName;
}
