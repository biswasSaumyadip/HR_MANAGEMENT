package com.human_resource.hr_management.v1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {
    private String username;
    private String user_email;
    private String user_password;
}
