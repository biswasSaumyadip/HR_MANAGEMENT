package com.human_resource.hr_management.v1.controller;

import com.human_resource.hr_management.v1.exceptionHandler.EmployeeNotFoundException;
import com.human_resource.hr_management.v1.model.Employee;
import com.human_resource.hr_management.v1.services.EmployeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getEmployees();
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<?> getEmployeeBy(@PathVariable String employeeId){
        try{
            return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
        }catch (EmployeeNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
