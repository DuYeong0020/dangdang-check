package com.dangdang.check.infrastrucure.customer;

import com.dangdang.check.domain.customer.CustomerPhone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerPhoneJpaRepository extends JpaRepository<CustomerPhone, Long> {

    Optional<CustomerPhone> findByIdAndIsDeletedFalse(Long id);
}
