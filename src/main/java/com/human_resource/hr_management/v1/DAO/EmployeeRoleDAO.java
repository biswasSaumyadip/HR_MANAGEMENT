package com.human_resource.hr_management.v1.DAO;

import com.human_resource.hr_management.v1.model.EmployeesRoles;
import com.human_resource.hr_management.v1.model.EmployeeWithRole;

import java.util.List;

public interface EmployeeRoleDAO extends DAO<EmployeesRoles>{

    List<EmployeeWithRole> getAllEmployeeWithRole();

    EmployeeWithRole getEmployeeWithRole(String employee_id);

}
