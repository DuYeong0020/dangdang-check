package com.dangdang.check.infrastrucure.customer;

import com.dangdang.check.domain.customer.CustomerCommand;
import com.dangdang.check.domain.customer.CustomerValidator;
import com.dangdang.check.domain.employee.Employee;
import com.dangdang.check.domain.employee.EmployeeReader;
import com.dangdang.check.domain.store.RegistrationStatus;
import com.dangdang.check.domain.store.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerValidatorImpl implements CustomerValidator {

    private final EmployeeReader employeeReader;

    @Override
    public void checkRegisterCustomerWithPets(CustomerCommand.RegisterCustomerWithPetsRequest request) {
        Employee employee = employeeReader.findByLoginId(request.getLoginId());
        checkEmployeeHasStore(employee);
        checkStoreIsOpen(employee.getStore());
    }

    private void checkStoreIsOpen(Store store) {
        if (store.isDeleted() || store.getBusinessInfo().isDeleted()
                || store.getBusinessInfo().getRegistrationStatus() == RegistrationStatus.REJECTED
                || store.getBusinessInfo().getRegistrationStatus() == RegistrationStatus.PENDING) {
            throw new RuntimeException("가게가 삭제되었습니다.");
        }
    }

    private void checkEmployeeHasStore(Employee employee) {
        if (employee.getStore() == null) {
            throw new RuntimeException("직원이 속한 가게(Store)가 없습니다.");
        }
    }
}
