package com.sep.utils;

import org.springframework.stereotype.Service;

@Service
public class IdGenerator {
    private final Snowflake SNOWFLAKE = new Snowflake();

    public long generateId() {
        return SNOWFLAKE.nextId();
    }
}
