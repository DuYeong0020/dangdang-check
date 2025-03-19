package com.dangdang.check.infrastrucure.customer;

import com.dangdang.check.common.exception.UnauthorizedException;
import com.dangdang.check.domain.customer.*;
import com.dangdang.check.domain.employee.Employee;
import com.dangdang.check.domain.employee.EmployeeReader;
import com.dangdang.check.domain.store.RegistrationStatus;
import com.dangdang.check.domain.store.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerPhoneValidatorImpl implements CustomerPhoneValidator {

    private final EmployeeReader employeeReader;
    private final CustomerReader customerReader;

    @Override
    public void checkRegisterCustomerPhone(CustomerPhoneCommand.RegisterPhoneRequest request) {
        Employee employee = employeeReader.findByLoginId(request.getLoginId());
        Customer customer = customerReader.findById(request.getCustomerId());
        List<CustomerPhone> phones = customer.getPhones();

        checkEmployeeHasStore(employee);
        checkStoreIsOpen(employee.getStore());
        checkSameStore(customer, employee);
        checkDuplicatePhoneNumber(phones, request.getPhoneNumber());
    }

    private void checkDuplicatePhoneNumber(List<CustomerPhone> phones, String phoneNumber) {
        if (phones == null || phones.isEmpty()) {
            return;
        }

        boolean isDuplicate = phones.stream()
                .anyMatch(phone -> phone.getPhoneNumber().equals(phoneNumber));

        if (isDuplicate) {
            throw new IllegalArgumentException("이미 등록된 전화번호입니다: " + phoneNumber);
        }
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

    private void checkSameStore(Customer customer, Employee employee) {
        if (customer.getStore() == null || employee.getStore() == null) {
            throw new UnauthorizedException("고객 또는 직원의 가게 정보가 올바르지 않습니다.");
        }

        if (!customer.getStore().getId().equals(employee.getStore().getId())) {
            throw new UnauthorizedException("해당 고객의 펫을 수정할 권한이 없습니다.");
        }
    }


}
