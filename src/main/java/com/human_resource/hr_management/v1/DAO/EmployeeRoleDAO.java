package com.human_resource.hr_management.v1.DAO;

import com.human_resource.hr_management.v1.model.EmployeesRoles;
import com.human_resource.hr_management.v1.model.EmployeeDetails;

import java.util.List;

public interface EmployeeRoleDAO extends DAO<EmployeesRoles>{

    List<EmployeeDetails> getAllEmployeeWithRole();

    EmployeeDetails getEmployeeWithRole(String employee_id);

}
