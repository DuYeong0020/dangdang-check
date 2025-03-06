package com.dangdang.check.domain.employee;

public interface EmployeeValidator {

    void checkRegisterEmployee(EmployeeCommand.RegisterEmployeeRequest request);
}
