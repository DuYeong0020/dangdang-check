package com.dangdang.check.domain.employee;


public interface EmployeeReader {

    Employee findByLoginId(String loginId);
}