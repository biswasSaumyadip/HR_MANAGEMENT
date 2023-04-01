package com.human_resource.hr_management.v1.DAO;

import com.human_resource.hr_management.v1.model.User;

import java.util.List;
import java.util.Optional;

public interface RegistrationDAO {

    List<User> getUsers();

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserByUsername(String userName);

    Optional<User> getUserById(String user_id);

    void saveUser(User user);
}
