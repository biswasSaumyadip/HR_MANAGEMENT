package com.human_resource.hr_management.v1.services;

import com.human_resource.hr_management.v1.model.EmployeeWithRole;
import com.human_resource.hr_management.v1.model.EmployeesRoles;

import java.util.Optional;

public interface EmployeeRoleService {

    void createEmployeeRole(EmployeesRoles employeesRoles);

    Optional<EmployeesRoles> getEmployeeRolesById(String uuid);

    Optional<EmployeeWithRole> getEmployeeDetails(String uuid);
}
