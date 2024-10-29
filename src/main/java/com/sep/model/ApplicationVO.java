package com.sep.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ApplicationVO {
    private String applicationId;
    private String eventName;
    private String eventDesc;
    private String eventStatus;
    private boolean needReview;
    private boolean showSetStatus;
    private String nextStatus;
    private String financialComment;
    // todo: tasks
    // todo: create task button
}
