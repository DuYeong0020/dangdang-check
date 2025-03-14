package com.dangdang.check.infrastrucure.customer;

import com.dangdang.check.domain.customer.Customer;
import com.dangdang.check.domain.customer.CustomerReader;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerJpaReader implements CustomerReader {

    private final CustomerJpaRepository customerJpaRepository;

    @Override
    public Customer findById(Long id) {
        return customerJpaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
