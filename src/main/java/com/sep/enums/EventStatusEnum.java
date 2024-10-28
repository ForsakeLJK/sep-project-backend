package com.sep.enums;

import lombok.Getter;

@Getter
public enum EventStatusEnum {
    REVIEWING("reviewing"),
    OPEN("open"),
    IN_PROGRESS("in progress"),
    CLOSED("closed");

    private final String desc;

    EventStatusEnum(String desc) {
        this.desc = desc;
    }
}