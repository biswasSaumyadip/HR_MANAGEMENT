package com.human_resource.hr_management.v1.services;

import com.human_resource.hr_management.v1.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getEmployees();

    Optional<Employee> getEmployeeById(String UUID);

    void createEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(String UUID);

}
