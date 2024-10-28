package com.sep.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class LoginResponse {
    private String username;
    private String userRole;
    private boolean loginSuccess;
}
