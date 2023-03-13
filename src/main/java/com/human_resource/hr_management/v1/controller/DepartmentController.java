package com.human_resource.hr_management.v1.controller;


import com.human_resource.hr_management.v1.model.Department;
import com.human_resource.hr_management.v1.services.DepartmentServiceImpl;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("department")
public class DepartmentController {
    private final DepartmentServiceImpl departmentService;

    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<?> getDepartments() {
        return ResponseEntity.ok().body(departmentService.getDepartments());
    }

    @GetMapping("{department_id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable String department_id) {
        Optional<Department> departmentOptional = this.departmentService.getDepartmentById(department_id);
        return departmentOptional.map(department -> ResponseEntity.ok().body(department)).orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<?> createDepartment(@RequestBody Department department) {
        try {
            departmentService.createDepartment(department);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataAccessException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @PutMapping
    public ResponseEntity<?> updateDepartment(@RequestBody Department department) {
        try {
            departmentService.updateDepartment(department);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (DataAccessException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteDepartment(@PathVariable String uuid) {
        try {
            departmentService.deleteDepartment(uuid);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Employee with ID " + uuid + " was successfully deleted.");
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete employee with ID " + uuid);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with ID " + uuid + " not found.");
        }
    }

}
