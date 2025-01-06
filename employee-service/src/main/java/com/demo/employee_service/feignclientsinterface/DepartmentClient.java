package com.demo.employee_service.feignclientsinterface;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.demo.employee_service.pojo.DepartmentPojo;

@FeignClient(name = "department-service", url = "http://department-cntr:8081/")
public interface DepartmentClient {

    @GetMapping("/api/departments/{id}")
    DepartmentPojo getDepartmentById(@PathVariable("id") int id);
}
