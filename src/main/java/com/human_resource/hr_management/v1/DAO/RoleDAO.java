package com.human_resource.hr_management.v1.DAO;

import com.human_resource.hr_management.v1.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleDAO implements DAO<Role> {

    private final JdbcTemplate jdbcTemplate;
    private final Logger log = LoggerFactory.getLogger(RoleDAO.class);

    public RoleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<Role> rowMapper = ((rs, rowNum) -> {
        Role role = new Role();
        role.setRole_id(rs.getString("role_id"));
        role.setRole_name(rs.getString("role_name"));
        return role;
    });

    @Override
    public List<Role> list() {
        String sql = "SELECT * from roles";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void create(Role role) {
        String sql = "insert into roles(role_id, role_name) values (?,?)";
        int rowAdded = jdbcTemplate.update(sql, role.getRole_id(), role.getRole_name());

        if (rowAdded == 1) {
            log.info(role.getRole_name() + " role created.");
        } else {
            log.error("Role creation failed!");
        }
    }

    @Override
    public Optional<Role> getBy(String uuid) {
        String sql = "select * from roles where role_id=?";
        try {
            Role role = jdbcTemplate.queryForObject(sql, rowMapper, uuid);
            return Optional.ofNullable(role);
        } catch (DataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Role role) {
        String sql = "update roles set role_name=? where role_id=?";
        int rowUpdated = jdbcTemplate.update(sql, role.getRole_name());

        if (rowUpdated == 1) {
            log.info(role.getRole_name() + " role updated!");
        } else {
            log.error(role.getRole_name() + " could not be updated.");
        }
    }

    @Override
    public void delete(String uuid) {
        int deletedRow = jdbcTemplate.update("delete from roles where role_id=?");
        if (deletedRow == 1) {
            log.info("Role was deleted");
        }else{
            log.error("Role was not deleted.");
        }
    }
}
