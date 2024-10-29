package com.sep.model;

import com.sep.enums.RequestStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SepRequest {
    private Long requestId;
    private String requestName;
    private Long applicationId;
    private String requestDesc;
    private RequestStatusEnum status;
    // budget or resource
    private String type;
}
