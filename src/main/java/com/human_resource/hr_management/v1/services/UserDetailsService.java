package com.human_resource.hr_management.v1.services;

import com.human_resource.hr_management.v1.model.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface UserDetailsService {

    User getUserById(String user_id);
    public Collection<? extends GrantedAuthority> getAuthorities();
}
