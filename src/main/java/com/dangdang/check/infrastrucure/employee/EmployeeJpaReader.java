package com.dangdang.check.infrastrucure.employee;

import com.dangdang.check.domain.employee.Employee;
import com.dangdang.check.domain.employee.EmployeeReader;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeJpaReader implements EmployeeReader {

    private final EmployeeJpaRepository employeeJpaRepository;

    @Override
    public Employee findByLoginId(String loginId) {
        return employeeJpaRepository.findByLoginId(loginId)
                .orElseThrow(EntityNotFoundException::new);
    }
}