package com.sep.enums;

import lombok.Getter;

@Getter
public enum RequestStatusEnum {
    APPROVED("approved"),
    REJECTED("rejected"),
    REVIEWING("reviewing");

    private final String desc;

    RequestStatusEnum(String desc) {
        this.desc = desc;
    }
}
