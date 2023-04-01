package com.human_resource.hr_management.v1.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {
    private String user_id;
    private String username;
    private String user_email;
    private String user_password;

}
