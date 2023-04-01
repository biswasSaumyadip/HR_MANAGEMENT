package com.human_resource.hr_management.v1.DAO;

import com.human_resource.hr_management.v1.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class RegistrationDAOImpl implements RegistrationDAO {


    private final JdbcTemplate jdbcTemplate;
    private final Logger log = LoggerFactory.getLogger(RegistrationDAOImpl.class);

    public RegistrationDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<User> rowMapper = (rs, rowNumber)->{
        User user = new User();
        user.setUser_id(rs.getString("user_id"));
        user.setUsername(rs.getString("username"));
        user.setUser_email(rs.getString("user_email"));
        user.setUser_password(rs.getString("user_password"));
        return user;
    };

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        String sql = "select * from users where user_email=?";

        try{
            User user = jdbcTemplate.queryForObject(sql, rowMapper, email);
            return Optional.ofNullable(user);
        }catch (DataAccessException exception){
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getUserByUsername(String userName) {
        return Optional.empty();
    }

    @Override
    public void saveUser(User user) {
        String sql = "insert into users(user_id, username, user_email, user_password) values (?,?,?,?) ";
        int rowAdded = jdbcTemplate.update(sql, user.getUser_id(), user.getUsername(), user.getUser_email(), user.getUser_password());
        if (rowAdded == 1) {
            log.info("User registered successfully.");
        } else {
            log.error("Registration failed!");
        }
    }

    @Override
    public Optional<User> getUserById(String user_id) {
        String sql = "select * from users where user_id=?";

        try{
            User user = jdbcTemplate.queryForObject(sql, rowMapper, user_id);
            return Optional.ofNullable(user);
        }catch (DataAccessException exception){
            return Optional.empty();
        }
    }
}
