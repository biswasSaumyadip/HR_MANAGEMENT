package com.human_resource.hr_management.v1.controller;

import com.human_resource.hr_management.v1.exceptionHandler.EmployeeNotFoundException;
import com.human_resource.hr_management.v1.model.Employee;
import com.human_resource.hr_management.v1.services.EmployeeServiceImpl;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee){
        try{
            employeeService.createEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch(DataAccessException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
