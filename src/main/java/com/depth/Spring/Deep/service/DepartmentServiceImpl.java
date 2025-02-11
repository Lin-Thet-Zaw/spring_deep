package com.depth.Spring.Deep.service;

import com.depth.Spring.Deep.entity.Department;
import com.depth.Spring.Deep.error.DepartmentNotFoundException;
import com.depth.Spring.Deep.repository.DepartmentRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        Department existData = departmentRepository.findById(departmentId).get();
        if(Objects.nonNull(department.getDepartmentName()) &&
        !"".equalsIgnoreCase(department.getDepartmentName())){
            existData.setDepartmentName(department.getDepartmentName());
        }

        if(Objects.nonNull(department.getDepartmentAddress()) &&
        !"".equalsIgnoreCase(department.getDepartmentAddress())){
            existData.setDepartmentAddress(department.getDepartmentAddress());
        }

        if(Objects.nonNull(department.getDepartmentCode()) &&
                !"".equalsIgnoreCase(department.getDepartmentCode())){
            existData.setDepartmentCode(department.getDepartmentCode());
        }

        return  departmentRepository.save(existData);
    }

    @Override
    public Department fetchDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }

    @Override
    public Optional<Department> fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isEmpty()){
            throw new DepartmentNotFoundException("Department not Available");
        }
        return department;
    }
    @Override
    public Department savedDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }
}
