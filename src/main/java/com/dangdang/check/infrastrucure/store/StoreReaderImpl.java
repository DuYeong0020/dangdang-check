package com.dangdang.check.infrastrucure.store;

import com.dangdang.check.domain.store.Store;
import com.dangdang.check.domain.store.StoreReader;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreReaderImpl implements StoreReader {

    private final StoreJpaRepository storeJpaRepository;

    @Override
    public Store findById(Long id) {
        return storeJpaRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}