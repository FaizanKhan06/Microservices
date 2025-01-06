package com.demo.employee_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.employee_service.dao.EmployeeRepository;
import com.demo.employee_service.dao.entity.Employee;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository repo;

    public List<Employee> getAllEmployees(){
        return repo.findAll();
    }

    public List<Employee> getEmployeesByDeptId(int deptId){
        return repo.findByDeptId(deptId);
    }

    public Employee getAEmployee(int employeeId){
        return repo.findById(employeeId).orElse(null);
    }

    public Employee addEmployee(Employee newEmployee){
        return repo.saveAndFlush(newEmployee);
    }

    public Employee updateEmployee(Employee updateEmployee){
        return repo.save(updateEmployee);
    }

    public void deleteEmployee(int employeeId){
        repo.deleteById(employeeId);
    }
}
