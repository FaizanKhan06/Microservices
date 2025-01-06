package com.demo.department_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.department_service.dao.DepartmentRepository;
import com.demo.department_service.dao.entity.Department;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository repo;

    public List<Department> getAllDepartment(){
        return repo.findAll();
    }

    public Department getADepartment(int departmentId){
        return repo.findById(departmentId).orElse(null);
    }

    public Department addDepartment(Department newDepartment){
        return repo.saveAndFlush(newDepartment);
    }

    public Department updateDepartment(Department updateDepartment){
        return repo.save(updateDepartment);
    }

    public void deleteDepartment(int departmentId){
        repo.deleteById(departmentId);
    }
}
