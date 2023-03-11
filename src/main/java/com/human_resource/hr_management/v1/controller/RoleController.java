package com.human_resource.hr_management.v1.controller;

import com.human_resource.hr_management.v1.model.Role;
import com.human_resource.hr_management.v1.services.RoleService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<Role>> getRoles() {
        return ResponseEntity.ok(roleService.getRoles());
    }

    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody Role role) {
        try {
            roleService.createRole(role);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataAccessException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
