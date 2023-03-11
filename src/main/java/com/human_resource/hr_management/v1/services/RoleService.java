package com.human_resource.hr_management.v1.services;

import com.human_resource.hr_management.v1.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> getRoles();

    Optional<Role> getRolesById(String UUID);

    void createRole(Role role);

    void updateRole(Role role);

    void deleteRole(String UUID);
}
