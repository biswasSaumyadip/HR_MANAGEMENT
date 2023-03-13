package com.human_resource.hr_management.v1.controller;


import com.human_resource.hr_management.v1.model.EmployeeDetails;
import com.human_resource.hr_management.v1.model.EmployeeDetailsRequest;
import com.human_resource.hr_management.v1.services.EmployeeDetailsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/employee/details")
public class EmployeeDetailsController {

    private EmployeeDetailsServiceImpl employeeDetailsService;

    public EmployeeDetailsController(EmployeeDetailsServiceImpl employeeDetailsService) {
        this.employeeDetailsService = employeeDetailsService;
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
        Optional<EmployeeDetails> employeeDetails = this.employeeDetailsService.getEmployeeDetails(request);

        return employeeDetails.map(employee -> ResponseEntity.ok().body(employee))
                .orElse(ResponseEntity.notFound().build());
    }
}
