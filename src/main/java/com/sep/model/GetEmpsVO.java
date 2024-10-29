package com.sep.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class GetEmpsVO {
    private List<EmpVO> empList;
}