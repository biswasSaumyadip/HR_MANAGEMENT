package com.human_resource.hr_management.v1.controller;

import com.human_resource.hr_management.v1.model.EmployeeWithRole;
import com.human_resource.hr_management.v1.model.EmployeesRoles;
import com.human_resource.hr_management.v1.services.EmployeeRoleServiceImpl;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeRoleController {

    private final EmployeeRoleServiceImpl employeeRoleService;


    public EmployeeRoleController(EmployeeRoleServiceImpl employeeRoleService) {
        this.employeeRoleService = employeeRoleService;
    }

    @PostMapping("/withRoles")
    public ResponseEntity<?> createEmployeesRoles(@RequestBody EmployeesRoles employeesRoles){
        try {
            employeeRoleService.createEmployeeRole(employeesRoles);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (DataAccessException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/details/{employeeId}")
    public ResponseEntity<?> getEmployeeDetails(@PathVariable String employeeId){
        Optional<EmployeeWithRole> employeeDetails = this.employeeRoleService.getEmployeeDetails(employeeId);
        return employeeDetails.map(employee -> ResponseEntity.ok().body(employee))
                .orElse(ResponseEntity.notFound().build());
    }
}
