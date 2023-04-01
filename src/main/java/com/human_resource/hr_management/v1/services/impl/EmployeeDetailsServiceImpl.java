package com.human_resource.hr_management.v1.services.impl;

import com.human_resource.hr_management.v1.DAO.EmployeeDAO;
import com.human_resource.hr_management.v1.DAO.RoleDAO;
import com.human_resource.hr_management.v1.model.*;
import com.human_resource.hr_management.v1.services.EmployeeDetailsService;
import com.human_resource.hr_management.v1.services.EmployeeRoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

    private final EmployeeDAO employeeDAO;

    private final RoleDAO roleDAO;

    private final EmployeeRoleService employeeRoleService;

    public EmployeeDetailsServiceImpl(EmployeeDAO employeeDAO, RoleDAO roleDAO, EmployeeRoleService employeeRoleService) {
        this.employeeDAO = employeeDAO;
        this.roleDAO = roleDAO;
        this.employeeRoleService = employeeRoleService;
    }

    @Override
    public Optional<EmployeeDetails> getEmployeeDetails(EmployeeDetailsRequest request) {
        EmployeesRoles employeesRoles = employeeRoleService.getEmployeeRolesById(request.getUuid()).orElse(null);
        Employee employee = this.employeeDAO.getBy(request.getUuid()).orElse(null);

        EmployeeDetails employeeDetails = new EmployeeDetails();

        if(employee != null) {
            employeeDetails.setEmployee(employee);

            if(employeesRoles != null && request.isIncludeRoles()){
                Role role = this.roleDAO.getBy(employeesRoles.getRole_id()).orElse(null);
                employeeDetails.setRole(role);
            }
        }

        return Optional.of(employeeDetails);
    }
}
