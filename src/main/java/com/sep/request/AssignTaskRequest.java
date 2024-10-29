package com.sep.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AssignTaskRequest {
    private String taskName;
    private String taskDesc;
    private String username;
    private String employeeName;
    private String applicationId;
}
