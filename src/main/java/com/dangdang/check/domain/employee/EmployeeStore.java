package com.dangdang.check.domain.employee;

public interface EmployeeStore {

    Employee storeEmployee(Employee employee);

    void resetEmployeesOfStore(Long storeId);
}