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
@RequestMapping("/employees/details")
public class EmployeeRoleController {

    private final EmployeeRoleServiceImpl employeeRoleService;


    public EmployeeRoleController(EmployeeRoleServiceImpl employeeRoleService) {
        this.employeeRoleService = employeeRoleService;
    }

    @PostMapping("/roles")
    public ResponseEntity<?> createEmployeesRoles(@RequestBody EmployeesRoles employeesRoles){
        try {
            employeeRoleService.createEmployeeRole(employeesRoles);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (DataAccessException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<?> getEmployeeDetails(
            @PathVariable String employeeId,
            @RequestParam(name = "include", required = false) String include
    ){
        boolean includeRoles = false;
        boolean includeDepartment = false;

        if(include != null){
            String[] includeValues = include.split(",");
            for(String value: includeValues){
                if(value.equals("roles")){
                    includeRoles = true;
                }else if(value.equals("department")){
                    includeDepartment = true;
                }
            }
        }

        EmployeeDetailsRequest request = new EmployeeDetailsRequest(employeeId, includeRoles, includeDepartment);
        Optional<EmployeeDetails> employeeDetails = this.employeeRoleService.getEmployeeDetails(request);

        return employeeDetails.map(employee -> ResponseEntity.ok().body(employee))
                .orElse(ResponseEntity.notFound().build());
    }
}
