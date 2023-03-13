package com.human_resource.hr_management.v1.services;

import com.human_resource.hr_management.v1.DAO.EmployeeDAO;
import com.human_resource.hr_management.v1.DAO.EmployeeRoleDAOImpl;
import com.human_resource.hr_management.v1.DAO.RoleDAO;
import com.human_resource.hr_management.v1.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeRoleServiceImpl implements EmployeeRoleService {

    private final Logger log = LoggerFactory.getLogger(EmployeeRoleServiceImpl.class);

    private final EmployeeRoleDAOImpl employeeRoleDAO;
    private final EmployeeDAO employeeDAO;

    private final RoleDAO roleDAO;

    public EmployeeRoleServiceImpl(EmployeeRoleDAOImpl employeeRoleDAO, EmployeeDAO employeeDAO, RoleDAO roleDAO) {
        this.employeeRoleDAO = employeeRoleDAO;
        this.employeeDAO = employeeDAO;
        this.roleDAO = roleDAO;
    }

    @Override
    public void createEmployeeRole(EmployeesRoles employeesRoles) {
        Employee employee = this.employeeDAO.getBy(employeesRoles.getEmployee_id()).orElse(null);
        Role role = this.roleDAO.getBy(employeesRoles.getRole_id()).orElse(null);

        if (employee != null && role != null) {
            String fullName = employee.getFirst_name() + " " + employee.getLast_name();
            this.employeeRoleDAO.create(employeesRoles);

            log.info(role.getRole_name() + " role is assigned to " + fullName);
        } else {
            log.error("Role not assigned.");
        }
    }

    @Override
    public Optional<EmployeesRoles> getEmployeeRolesById(String uuid) {
        EmployeesRoles employeeRole = this.employeeRoleDAO.getBy(uuid).orElse(null);
        if(employeeRole == null){
            return Optional.empty();
        }
        return Optional.ofNullable(employeeRole);
    }
}
