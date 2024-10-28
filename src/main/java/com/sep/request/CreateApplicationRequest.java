package com.sep.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateApplicationRequest {
    private String eventName;
    private String eventDesc;
}
