package com.human_resource.hr_management.v1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeWithRole extends Employee {
    private Role role;

    public void setEmployee(Employee employee) {
        this.setEmployee_id(employee.getEmployee_id());
        this.setFirst_name(employee.getFirst_name());
        this.setLast_name(employee.getLast_name());
        this.setEmail(employee.getEmail());
        this.setPhone(employee.getPhone());
        this.setHire_date(employee.getHire_date());
        this.setTermination_date(employee.getTermination_date());
    }
}
