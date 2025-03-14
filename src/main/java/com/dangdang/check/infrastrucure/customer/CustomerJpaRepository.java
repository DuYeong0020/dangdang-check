package com.dangdang.check.infrastrucure.customer;

import com.dangdang.check.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {
}
