package com.human_resource.hr_management.v1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeDetailsRequest {
    private String uuid;
    private boolean includeRoles;
    private boolean includeDepartment;
}
