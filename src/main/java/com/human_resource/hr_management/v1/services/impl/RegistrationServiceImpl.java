package com.human_resource.hr_management.v1.services.impl;

import com.human_resource.hr_management.v1.DAO.RegistrationDAO;
import com.human_resource.hr_management.v1.model.User;
import com.human_resource.hr_management.v1.services.RegistrationService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationDAO registrationDAO;

    public RegistrationServiceImpl(RegistrationDAO registrationDAO) {
        this.registrationDAO = registrationDAO;
    }

    @Override
    public String SaveUser(User user) {
        if (user.getUser_id() == null) {
            user.setUser_id(UUID.randomUUID().toString());
        }

        try {

            registrationDAO.saveUser(user);
            return "Registration was successful";
        } catch (DataAccessException exception) {
            return "Registration was not successful!";
        }
    }
}
