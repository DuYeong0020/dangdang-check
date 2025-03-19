package com.dangdang.check.infrastrucure.customer;

import com.dangdang.check.domain.customer.CustomerPhone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerPhoneJpaRepository extends JpaRepository<CustomerPhone, Long> {
}
