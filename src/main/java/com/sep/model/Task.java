package com.sep.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Task {
    private Long taskId;
    private Long applicationId;
    private String taskName;
    private String taskDesc;
    private String comment;
    private String assignee;
}
