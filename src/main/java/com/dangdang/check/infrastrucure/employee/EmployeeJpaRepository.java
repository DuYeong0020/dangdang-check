package com.dangdang.check.infrastrucure.employee;

import com.dangdang.check.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeJpaRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByLoginIdAndIsDeletedFalse(String loginId);
    Optional<Employee> findByEmailAndIsDeletedFalse(String email);
    Optional<Employee> findByNicknameAndIsDeletedFalse(String nickname);
    Optional<Employee> findByMobilePhoneAndIsDeletedFalse(String mobilePhone);
}
