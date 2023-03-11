package com.human_resource.hr_management.v1.services;

import com.human_resource.hr_management.v1.DAO.EmployeeDAO;
import com.human_resource.hr_management.v1.exceptionHandler.EmployeeNotFoundException;
import com.human_resource.hr_management.v1.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> getEmployees() {
        return this.employeeDAO.list();
    }

    @Override
    public Optional<Employee> getEmployeeById(String UUID) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeDAO.getBy(UUID);
        return Optional.ofNullable(employee.orElseThrow(() -> new EmployeeNotFoundException("Employee not found")));
    }

    @Override
    public void createEmployee(Employee employee) {
        if(employee.getEmployee_id() == null){
            employee.setEmployee_id(UUID.randomUUID().toString());
        }

        this.employeeDAO.create(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        this.employeeDAO.update(employee);
    }

    @Override
    public void deleteEmployee(String UUID) {
        this.employeeDAO.delete(UUID);
    }
}
