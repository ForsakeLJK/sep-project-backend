package com.sep.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ChangeStatusRequest {
    private String username;
    private String applicationId;
}
