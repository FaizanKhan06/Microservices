package com.demo.employee_service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.employee_service.dao.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    List<Employee> findByDeptId(int deptId);
}
