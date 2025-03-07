package com.dangdang.check.infrastrucure.store;

import com.dangdang.check.domain.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreJpaRepository extends JpaRepository<Store, Long> {
}
