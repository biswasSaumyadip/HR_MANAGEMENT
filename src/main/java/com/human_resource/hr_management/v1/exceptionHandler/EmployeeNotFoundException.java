package com.human_resource.hr_management.v1.exceptionHandler;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String message){
        super(message);
    }
}
