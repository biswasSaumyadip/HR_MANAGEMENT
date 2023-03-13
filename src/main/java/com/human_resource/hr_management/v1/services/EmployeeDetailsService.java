package com.human_resource.hr_management.v1.services;

import com.human_resource.hr_management.v1.model.EmployeeDetails;
import com.human_resource.hr_management.v1.model.EmployeeDetailsRequest;

import java.util.Optional;

public interface EmployeeDetailsService {
    Optional<EmployeeDetails> getEmployeeDetails(EmployeeDetailsRequest request);
}
