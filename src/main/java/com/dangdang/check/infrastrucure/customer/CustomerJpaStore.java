package com.dangdang.check.infrastrucure.customer;

import com.dangdang.check.domain.customer.Customer;
import com.dangdang.check.domain.customer.CustomerStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerJpaStore implements CustomerStore {

    private final CustomerJpaRepository customerJpaRepository;


    @Override
    public Customer storeCustomer(Customer customer) {
        return customerJpaRepository.save(customer);
    }
}
