package com.human_resource.hr_management.v1.DAO;

import com.human_resource.hr_management.v1.model.EmployeesRoles;
import com.human_resource.hr_management.v1.model.EmployeeDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class EmployeeRoleDAOImpl implements EmployeeRoleDAO{

    private final Logger log = LoggerFactory.getLogger(EmployeeRoleDAOImpl.class);
    private final JdbcTemplate jdbcTemplate;

        RowMapper<EmployeesRoles> employeeRoleRowMapper = (rs, rowNum)->{
        EmployeesRoles employeesRoles = new EmployeesRoles();
        employeesRoles.setEmployee_id(rs.getString("employee_id"));
        employeesRoles.setRole_id(rs.getString("role_id"));
        employeesRoles.setStart_date(rs.getDate("start_date"));
        employeesRoles.setEnd_date(rs.getDate("end_date"));

        return employeesRoles;
    };

    public EmployeeRoleDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<EmployeesRoles> list() {
        String sql = "select * from employees_roles";
        return jdbcTemplate.query(sql, employeeRoleRowMapper);
    }

    @Override
    public void create(EmployeesRoles employeesRoles) {
        String sql = "insert into employees_roles(employee_id, role_id, start_date, end_date) values(?,?,?,?)";
        int rowNumEffected = jdbcTemplate.update(sql, employeesRoles.getEmployee_id(), employeesRoles.getRole_id(),
                employeesRoles.getStart_date(), employeesRoles.getEnd_date());

        if(rowNumEffected == 1){
            log.info("One row effected.");
        }else{
            log.error("BAD SQL GRAMMAR");
        }
    }

    @Override
    public Optional<EmployeesRoles> getBy(String uuid) {
        String sql = "select * from employees_roles where employee_id=?";
       try{
           EmployeesRoles employeeRoles = jdbcTemplate.queryForObject(sql, employeeRoleRowMapper, uuid);
           return Optional.ofNullable(employeeRoles);
       }catch (DataAccessException exception){
           return Optional.empty();
       }
    }

    @Override
    public void update(EmployeesRoles employeesRoles) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public List<EmployeeDetails> getAllEmployeeWithRole() {
        return null;
    }

    @Override
    public EmployeeDetails getEmployeeWithRole(String employee_id) {
        return null;
    }
}
