package com.human_resource.hr_management.v1.DAO;

import com.human_resource.hr_management.v1.exceptionHandler.EmployeeCreationException;
import com.human_resource.hr_management.v1.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeDAO implements DAO<Employee> {
    private static final Logger log = LoggerFactory.getLogger(EmployeeDAO.class);
    private JdbcTemplate jdbcTemplate;
    RowMapper<Employee> rowMapper = (rs, rowNum) -> {
        Employee employee = new Employee();
        employee.setEmployee_id(rs.getString("employee_id"));
        employee.setFirst_name(rs.getString("first_name"));
        employee.setLast_name(rs.getString("last_name"));
        employee.setPhone(rs.getString("phone"));
        employee.setEmail(rs.getString("email"));
        employee.setHire_date(rs.getDate("hire_date"));
        employee.setTermination_date(rs.getDate("termination_date"));
        return employee;
    };

    @Autowired
    public EmployeeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> list() {
        String sql = "select * from employees";
        return this.jdbcTemplate.query(sql, this.rowMapper);
    }

    public void create(Employee employee) {
        String sql = "insert into  employees(employee_id, first_name, last_name, email, phone, hire_date, termination_date) values (? , ?, ?, ?, ? ,? , ?)";
        int insert = this.jdbcTemplate.update(sql, new Object[]{employee.getEmployee_id(), employee.getFirst_name(), employee.getLast_name(), employee.getEmail(), employee.getPhone(), employee.getHire_date(), employee.getTermination_date()});
        String var10000 = employee.getFirst_name();
        String fullName = var10000 + " " + employee.getLast_name();
        if (insert == 1) {
            log.info("New Employee registered: " + fullName);
        } else {
            throw new EmployeeCreationException("Error creating employee " + fullName);
        }
    }

    public Optional<Employee> getBy(String uuid) {
        return Optional.empty();
    }

    public void update(Employee employee, String uuid) {
    }

    public void delete(String uuid) {
    }
}
