package com.depth.Spring.Deep.service;

import com.depth.Spring.Deep.entity.Department;
import com.depth.Spring.Deep.error.DepartmentNotFoundException;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Department savedDepartment(Department department);

    List<Department> fetchDepartmentList();

    Optional<Department> fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    void deleteDepartmentById(Long departmentId);

    Department updateDepartment(Long departmentId, Department department);

    Department fetchDepartmentByName(String departmentName);
}
