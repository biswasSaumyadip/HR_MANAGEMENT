package com.human_resource.hr_management.v1.controller;

import com.human_resource.hr_management.v1.model.EmployeeDetails;
import com.human_resource.hr_management.v1.model.EmployeeDetailsRequest;
import com.human_resource.hr_management.v1.model.EmployeesRoles;
import com.human_resource.hr_management.v1.services.EmployeeRoleServiceImpl;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/employeesRoles")
public class EmployeeRoleController {

    private final EmployeeRoleServiceImpl employeeRoleService;


    public EmployeeRoleController(EmployeeRoleServiceImpl employeeRoleService) {
        this.employeeRoleService = employeeRoleService;
    }

    @PostMapping
    public ResponseEntity<?> createEmployeesRoles(@RequestBody EmployeesRoles employeesRoles){
        try {
            employeeRoleService.createEmployeeRole(employeesRoles);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (DataAccessException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
