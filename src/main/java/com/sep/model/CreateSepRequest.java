package com.sep.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateSepRequest {
    private String requestName;
    private String requestDesc;
    private String applicationId;
    private String username;
    private String type;
}
