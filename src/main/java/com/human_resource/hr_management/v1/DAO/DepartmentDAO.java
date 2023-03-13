package com.human_resource.hr_management.v1.DAO;

import com.human_resource.hr_management.v1.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentDAO implements DAO<Department> {

    private final JdbcTemplate jdbcTemplate;
    private final Logger log = LoggerFactory.getLogger(DepartmentDAO.class);

    public DepartmentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<Department> rowMapper = (rs, rowNumber) -> {
        Department department = new Department();
        department.setDepartment_id(rs.getString("department_id"));
        department.setDepartment_name(rs.getString("department_name"));

        return department;
    };

    @Override
    public List<Department> list() {
        String sql = "Select * from departments";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void create(Department department) {
        String sql = "INSERT INTO departments(department_id, department_name) VALUES (:department_id, :department_name)";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("department_id", department.getDepartment_id());
        parameters.addValue("department_name", department.getDepartment_name());

        try {
            int rowsAffected = jdbcTemplate.update(sql, parameters);
            if (rowsAffected == 1) {
                log.info("New department " + department.getDepartment_name() + " added");
            } else {
                log.error("Failed to register " + department.getDepartment_name() + " as a new department");
            }
        } catch (DataAccessException e) {
            log.error("Failed to register " + department.getDepartment_name() + " as a new department: " + e.getMessage());
        }
    }

    @Override
    public Optional<Department> getBy(String uuid) {
        String sql = "SELECT * FROM departments WHERE department_id=:uuid";

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("uuid", uuid);

        try {
            List<Department> departments = jdbcTemplate.query(sql, rowMapper, parameters);
            return departments.isEmpty() ? Optional.empty() : Optional.of(departments.get(0));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Department department) {
        String sql = "update departments set department_name=? where department_id=?";
        int rowNumberEffected = jdbcTemplate.update(sql, department.getDepartment_name());

        if (rowNumberEffected == 1) {
            log.info("Department name updated!");
        } else {
            log.error("Department name updating failed!");
        }
    }

    @Override
    public void delete(String uuid) {
        int rowNumberEffected = jdbcTemplate.update("delete from departments where department_id=?");

        if (rowNumberEffected == 1) {
            log.info("Department deleted!");
        } else {
            log.error("Department failed to update!");
        }
    }
}
