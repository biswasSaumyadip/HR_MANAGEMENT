package com.human_resource.hr_management.v1.exceptionHandler;

public class EmployeeCreationException extends RuntimeException{
    public EmployeeCreationException(String message) {
        super(message);
    }
}
