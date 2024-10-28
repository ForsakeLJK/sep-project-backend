package com.sep.model;

import com.sep.enums.EventStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EventApplication {
    private Long applicationId;
    private String eventName;
    private String eventDesc;
    private EventStatusEnum eventStatus;
}
