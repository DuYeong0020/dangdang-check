package com.dangdang.check.domain.employee;

public interface EmployeeService {

    EmployeeInfo registerEmployee(EmployeeCommand.RegisterEmployeeRequest request);
}
