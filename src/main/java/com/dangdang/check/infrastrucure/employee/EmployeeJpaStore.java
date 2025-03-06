package com.dangdang.check.infrastrucure.employee;

import com.dangdang.check.domain.employee.Employee;
import com.dangdang.check.domain.employee.EmployeeStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeJpaStore implements EmployeeStore {

    private final EmployeeJpaRepository employeeJpaRepository;

    @Override
    public Employee storeEmployee(Employee employee) {
        return employeeJpaRepository.save(employee);
    }
}