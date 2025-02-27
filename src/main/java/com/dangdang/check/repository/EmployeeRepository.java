package com.dangdang.check.repository;

import com.dangdang.check.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByLoginId(String loginId);
}
