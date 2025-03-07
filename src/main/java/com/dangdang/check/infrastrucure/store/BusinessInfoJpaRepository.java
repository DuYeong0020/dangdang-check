package com.dangdang.check.infrastrucure.store;

import com.dangdang.check.domain.store.BusinessInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusinessInfoJpaRepository extends JpaRepository<BusinessInfo, Long> {
    Optional<BusinessInfo> findByBusinessRegistrationNumberAndIsDeletedFalse(String businessRegistrationNumber);
}