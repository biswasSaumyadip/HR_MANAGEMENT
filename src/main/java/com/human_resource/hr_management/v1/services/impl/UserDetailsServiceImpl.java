package com.human_resource.hr_management.v1.services.impl;

import com.human_resource.hr_management.v1.DAO.RegistrationDAO;
import com.human_resource.hr_management.v1.Security.UserPrincipal;
import com.human_resource.hr_management.v1.model.User;
import com.human_resource.hr_management.v1.services.UserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final RegistrationDAO registrationDAO;

    public UserDetailsServiceImpl(RegistrationDAO registrationDAO) {
        this.registrationDAO = registrationDAO;
    }

    @Override
    public User getUserById(String user_id) {

        return this.registrationDAO.getUserById(user_id).orElse(null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
