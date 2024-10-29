package com.sep.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SepReqVO {
    private String requestId;
    private String requestName;
    private String requestDesc;
    private String applicationId;
    private String status;
}
