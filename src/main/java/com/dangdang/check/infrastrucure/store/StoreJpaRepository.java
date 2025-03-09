package com.dangdang.check.infrastrucure.store;

import com.dangdang.check.domain.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreJpaRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByIdAndIsDeletedFalse(Long id);
}