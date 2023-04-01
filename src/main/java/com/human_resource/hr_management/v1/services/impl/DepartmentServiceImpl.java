package com.human_resource.hr_management.v1.services.impl;

import com.human_resource.hr_management.v1.DAO.DepartmentDAO;
import com.human_resource.hr_management.v1.model.Department;
import com.human_resource.hr_management.v1.services.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDAO departmentDAO;

    public DepartmentServiceImpl(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    @Override
    public List<Department> getDepartments() {
        return this.departmentDAO.list();
    }

    @Override
    public Optional<Department> getDepartmentById(String uuid) {
        return this.departmentDAO.getBy(uuid);
    }

    @Override
    public void createDepartment(Department department) {
        this.departmentDAO.create(department);
    }

    @Override
    public void updateDepartment(Department department) {
        this.departmentDAO.update(department);
    }

    @Override
    public void deleteDepartment(String uuid) {
        this.departmentDAO.delete(uuid);
    }
}
