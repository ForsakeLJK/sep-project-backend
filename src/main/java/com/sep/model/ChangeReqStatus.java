package com.sep.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ChangeReqStatus {
    private String requestId;
    private String action;
    private String username;
}
