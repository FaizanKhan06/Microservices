package com.demo.department_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.department_service.dao.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{

}
