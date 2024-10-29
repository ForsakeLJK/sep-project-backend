package com.sep.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EmpVO {
    private String name;
    private String department;
}
