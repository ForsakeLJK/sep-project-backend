package com.sep.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {
    private String userName;
    private String password;
    private String userRole;
    private String department;
}
