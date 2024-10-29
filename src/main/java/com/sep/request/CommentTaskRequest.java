package com.sep.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CommentTaskRequest {
    private String comment;
    private String taskId;
    private String username;
}
