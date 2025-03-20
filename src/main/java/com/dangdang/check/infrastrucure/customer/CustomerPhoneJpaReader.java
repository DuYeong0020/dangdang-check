package com.dangdang.check.infrastrucure.customer;

import com.dangdang.check.domain.customer.CustomerPhone;
import com.dangdang.check.domain.customer.CustomerPhoneReader;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerPhoneJpaReader implements CustomerPhoneReader {

    private final CustomerPhoneJpaRepository customerPhoneJpaRepository;

    @Override
    public CustomerPhone findById(Long id) {
        return customerPhoneJpaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
