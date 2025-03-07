package com.dangdang.check.infrastrucure.store;

import com.dangdang.check.domain.employee.Employee;
import com.dangdang.check.domain.employee.EmployeeReader;
import com.dangdang.check.domain.store.StoreCommand;
import com.dangdang.check.domain.store.StoreValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreValidatorImpl implements StoreValidator {

    private final EmployeeReader employeeReader;
    private final BusinessInfoJpaRepository businessInfoJpaRepository;


    @Override
    public void checkRegisterStore(StoreCommand.RegisterStoreRequest request) {
        checkOwnerAlreadyHasStore(request.getLoginId());
        checkDuplicateBusinessRegistrationNumber(request.getBusinessRegistrationNumber());
    }

    private void checkOwnerAlreadyHasStore(String loginId) {
        Employee employee = employeeReader.findByLoginId(loginId);
        if (employee.getStore() != null) {
            throw new RuntimeException("Owner already has store");
        }
    }

    private void checkDuplicateBusinessRegistrationNumber(String businessRegistrationNumber) {
        businessInfoJpaRepository.findByBusinessRegistrationNumberAndIsDeletedFalse(businessRegistrationNumber)
                .ifPresent(businessInfo -> {
                    throw new RuntimeException("Duplicate business registration number");
                });
    }
}
