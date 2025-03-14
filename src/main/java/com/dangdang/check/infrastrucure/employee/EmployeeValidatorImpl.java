package com.dangdang.check.infrastrucure.employee;

import com.dangdang.check.domain.employee.EmployeeCommand;
import com.dangdang.check.domain.employee.EmployeeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeValidatorImpl implements EmployeeValidator {

    private final EmployeeJpaRepository employeeJpaRepository;

    @Override
    public void checkRegisterEmployee(EmployeeCommand.RegisterEmployeeRequest request) {
        checkDuplicateNickname(request.getNickname());
        checkDuplicateLoginId(request.getLoginId());
        checkDuplicateEmail(request.getEmail());
        checkDuplicateMobilePhone(request.getMobilePhone());
    }

    private void checkDuplicateMobilePhone(String mobilePhone) {
        employeeJpaRepository.findByMobilePhoneAndIsDeletedFalse(mobilePhone)
                .ifPresent(employee -> {
                    throw new RuntimeException("Duplicate mobile phone number");
                });
    }

    private void checkDuplicateEmail(String email) {
        employeeJpaRepository.findByEmailAndIsDeletedFalse(email)
                .ifPresent(employee -> {
                    throw new RuntimeException("Duplicate email");
                });
    }

    private void checkDuplicateLoginId(String loginId) {
        employeeJpaRepository.findByLoginIdAndIsDeletedFalse(loginId)
                .ifPresent(employee -> {
                    throw new RuntimeException("Duplicate loginId");
                });
    }

    private void checkDuplicateNickname(String nickname) {
        employeeJpaRepository.findByNicknameAndIsDeletedFalse(nickname)
                .ifPresent(employee -> {
                    throw new RuntimeException("Duplicate nickname");
                });
    }


}
