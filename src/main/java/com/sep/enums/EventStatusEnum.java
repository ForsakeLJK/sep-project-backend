package com.sep.enums;

import lombok.Getter;

@Getter
public enum EventStatusEnum {
    REVIEWING("reviewing", 1, 3),
    OPEN("open", 2, 1),
    IN_PROGRESS("in progress", 3, 2),
    CLOSED("closed", 4, 4),
    REJECTED("rejected", 5, 5);

    private final String desc;
    private final int revPriority;
    private final int pmPriority;

    EventStatusEnum(String desc, int revPriority, int pmPriority) {
        this.desc = desc;
        this.revPriority = revPriority;
        this.pmPriority = pmPriority;
    }
}