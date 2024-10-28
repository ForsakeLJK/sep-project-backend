package com.sep.service;

import com.sep.utils.Snowflake;
import org.springframework.stereotype.Service;

@Service
public class IdGenService {
    private final Snowflake SNOWFLAKE = new Snowflake();

    public long generateId() {
        return SNOWFLAKE.nextId();
    }
}
