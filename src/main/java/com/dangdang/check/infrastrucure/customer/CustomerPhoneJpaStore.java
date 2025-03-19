package com.dangdang.check.infrastrucure.customer;

import com.dangdang.check.domain.customer.CustomerPhone;
import com.dangdang.check.domain.customer.CustomerPhoneStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerPhoneJpaStore implements CustomerPhoneStore {

    private final CustomerPhoneJpaRepository customerPhoneJpaRepository;

    @Override
    public CustomerPhone storeCustomerPhone(CustomerPhone customerPhone) {
        return customerPhoneJpaRepository.save(customerPhone);
    }
}
