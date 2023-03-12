package com.human_resource.hr_management.v1.DAO;

import com.human_resource.hr_management.v1.model.Employee_role;
import com.human_resource.hr_management.v1.model.Employee_with_role;

import java.util.List;
import java.util.Optional;

public class EmployeeRoleDAOImpl implements EmployeeRoleDAO{
    @Override
    public List<Employee_role> list() {
        return null;
    }

    @Override
    public void create(Employee_role employeeRole) {

    }

    @Override
    public Optional<Employee_role> getBy(String uuid) {
        return Optional.empty();
    }

    @Override
    public void update(Employee_role employeeRole) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public List<Employee_with_role> getAllEmployeeWithRole() {
        return null;
    }

    @Override
    public Employee_with_role getEmployeeWithRole() {
        return null;
    }
}
