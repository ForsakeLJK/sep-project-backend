package com.sep.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class ReviewApplicationRequest {
    private String applicationId;
    private String action;
    private String username;
    private String comment;
}