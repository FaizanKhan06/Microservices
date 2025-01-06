package com.demo.department_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmployeePojo {
    private int empId;
    private String empName;
    private String empDesignation;
    private int deptId;
}
