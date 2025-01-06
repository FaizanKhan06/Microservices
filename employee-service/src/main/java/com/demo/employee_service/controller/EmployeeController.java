package com.demo.employee_service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.employee_service.dao.entity.Employee;
import com.demo.employee_service.feignclientsinterface.DepartmentClient;
import com.demo.employee_service.pojo.DepartmentPojo;
import com.demo.employee_service.pojo.EmployeePojo;
import com.demo.employee_service.service.EmployeeService;


@RestController
@RequestMapping("")

public class EmployeeController {
    @Autowired
    EmployeeService service;

    @Autowired
    private DepartmentClient departmentClient;

    @GetMapping("/api/employees/bydept/{deptId}")
    public ResponseEntity<List<Employee>> getEmployeesByDeptId(@PathVariable int deptId){
        return new ResponseEntity<>(service.getEmployeesByDeptId(deptId), HttpStatus.OK);
    }

    
    @GetMapping("/api/employees")
    public ResponseEntity<List<EmployeePojo>> getAllEmployees(){
        List<Employee> emps = service.getAllEmployees();
        List<EmployeePojo> employeePojos = new ArrayList<>();

        emps.stream().forEach(emp -> {
            EmployeePojo employeePojo = new EmployeePojo();
            DepartmentPojo departmentPojo = departmentClient.getDepartmentById(emp.getDeptId());
            employeePojo.setEmpId(emp.getEmpId());
            employeePojo.setEmpName(emp.getEmpName());
            employeePojo.setEmpDesignation(emp.getEmpDesignation());
            employeePojo.setDepartment(departmentPojo);
            employeePojos.add(employeePojo);
        });

        return new ResponseEntity<>(employeePojos, HttpStatus.OK);
    }

    @GetMapping("/api/employees/{employeeId}")
    public ResponseEntity<EmployeePojo> getAEmployee(@PathVariable int employeeId){
        Employee emp = service.getAEmployee(employeeId);
        if(emp!=null){
            EmployeePojo employeePojo = new EmployeePojo();
            DepartmentPojo departmentPojo = departmentClient.getDepartmentById(emp.getDeptId());
            employeePojo.setEmpId(emp.getEmpId());
            employeePojo.setEmpName(emp.getEmpName());
            employeePojo.setEmpDesignation(emp.getEmpDesignation());
            employeePojo.setDepartment(departmentPojo);
            return new ResponseEntity<>(employeePojo, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/api/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee newEmployee){
        return new ResponseEntity<>(service.addEmployee(newEmployee), HttpStatus.OK);
    }

    @PutMapping("/api/employees")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee updateEmployee){
        return new ResponseEntity<>(service.updateEmployee(updateEmployee), HttpStatus.OK);
    }

    @DeleteMapping("/api/employees/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int employeeId){
        service.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
