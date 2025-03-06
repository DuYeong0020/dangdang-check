package com.dangdang.check.infrastrucure.employee;

import com.dangdang.check.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeJpaRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByLoginId(String loginId);
    Optional<Employee> findByEmail(String email);
    Optional<Employee> findByNickname(String nickname);
    Optional<Employee> findByMobilePhone(String mobilePhone);
}
