package com.human_resource.hr_management.v1.services;

import com.human_resource.hr_management.v1.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> getDepartments();

    Optional<Department> getDepartmentById(String uuid);

    void createDepartment(Department department);

    void updateDepartment(Department department);

    void deleteDepartment(String uuid);
}
