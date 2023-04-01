package com.human_resource.hr_management.v1.services.impl;

import com.human_resource.hr_management.v1.DAO.RoleDAO;
import com.human_resource.hr_management.v1.model.Role;
import com.human_resource.hr_management.v1.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public List<Role> getRoles() {
        return roleDAO.list();
    }

    @Override
    public Optional<Role> getRolesById(String UUID) {
        return Optional.empty();
    }

    @Override
    public void createRole(Role role) {
        if(role.getRole_id() == null){
            role.setRole_id(UUID.randomUUID().toString());
        }
        roleDAO.create(role);
    }

    @Override
    public void updateRole(Role role) {
        roleDAO.update(role);
    }

    @Override
    public void deleteRole(String UUID) {
        roleDAO.delete(UUID);
    }
}
