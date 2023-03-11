package com.human_resource.hr_management.v1.controller;

import com.human_resource.hr_management.v1.model.Employee;
import com.human_resource.hr_management.v1.services.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;
    private final Logger log = LoggerFactory.getLogger(EmployeeController.class);
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            List<Employee> employees = employeeService.getEmployees();
            if (employees.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.status(HttpStatus.OK).body(employees);
        } catch (DataAccessException e) {
            log.error("Failed to retrieve employees", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String employeeId) {
        Optional<Employee> employeeOptional = employeeService.getEmployeeById(employeeId);
        return employeeOptional.map(employee -> ResponseEntity.ok().body(employee))
                .orElse(ResponseEntity.notFound().build());
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

    @PutMapping
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee){
        try{
            employeeService.updateEmployee(employee);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }catch (DataAccessException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String employeeId){
        try {
            employeeService.deleteEmployee(employeeId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Employee with ID " + employeeId + " was successfully deleted.");
        } catch (DataAccessException e) {
            log.error("Failed to delete employee with ID " + employeeId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete employee with ID " + employeeId);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with ID " + employeeId + " not found.");
        }
    }

}
