package com.sep.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RecruitEmpRequest {
    private String name;
    private String department;
}
