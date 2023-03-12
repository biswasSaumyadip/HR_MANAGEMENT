package com.human_resource.hr_management.v1.DAO;

import com.human_resource.hr_management.v1.model.Employee_role;
import com.human_resource.hr_management.v1.model.Employee_with_role;

import java.util.List;

public interface EmployeeRoleDAO extends DAO<Employee_role>{

    List<Employee_with_role> getAllEmployeeWithRole();

    Employee_with_role getEmployeeWithRole();

}
