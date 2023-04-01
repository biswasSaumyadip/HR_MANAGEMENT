package com.human_resource.hr_management.v1;

import com.human_resource.hr_management.v1.Security.JwtAuthenticationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EntityScan(basePackageClasses = { HrManagementApplication.class })
public class HrManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrManagementApplication.class, args);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

}
