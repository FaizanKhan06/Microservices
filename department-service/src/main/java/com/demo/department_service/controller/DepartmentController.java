package com.demo.department_service.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
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
import org.springframework.web.client.RestClient;

import com.demo.department_service.dao.entity.Department;
import com.demo.department_service.pojo.DepartmentPojo;
import com.demo.department_service.pojo.EmployeePojo;
import com.demo.department_service.service.DepartmentService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("")

public class DepartmentController {
    @Autowired
    DepartmentService service;

    @GetMapping("/api/departments")
    public ResponseEntity<List<Department>> getAllDepartment(){
        return new ResponseEntity<>(service.getAllDepartment(), HttpStatus.OK);
    }

    @CircuitBreaker(name="ciremp", fallbackMethod = "fallbackEmployee")
    @GetMapping("/api/departments/{departmentId}")
    public ResponseEntity<DepartmentPojo> getADepartment(@PathVariable int departmentId){
        RestClient restClient = RestClient.create();
        List<EmployeePojo> allEmps = restClient
                                .get()
                                .uri("http://employee-cntr:8082/api/employees/bydept/"+departmentId)
                                .retrieve()
                                .body(List.class);
        DepartmentPojo deptPojo = new DepartmentPojo(); 
        Department department = service.getADepartment(departmentId);

        if(department!=null){
            BeanUtils.copyProperties(department, deptPojo);
            deptPojo.setEmps(allEmps);
        }
        return new ResponseEntity<>(deptPojo, HttpStatus.OK);
    }

    public ResponseEntity<DepartmentPojo> fallbackEmployee(Exception e) {
		return new ResponseEntity<>(new DepartmentPojo(-1, null, null), HttpStatus.OK);
	 }

    @PostMapping("/api/departments")
    public ResponseEntity<Department> addDepartment(@RequestBody Department newDepartment){
        return new ResponseEntity<>(service.addDepartment(newDepartment), HttpStatus.OK);
    }

    @PutMapping("/api/departments")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department updateDepartment){
        return new ResponseEntity<>(service.updateDepartment(updateDepartment), HttpStatus.OK);
    }

    @DeleteMapping("/api/departments")
    public ResponseEntity<Void> deleteDepartment(@PathVariable int departmentId){
        service.deleteDepartment(departmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
