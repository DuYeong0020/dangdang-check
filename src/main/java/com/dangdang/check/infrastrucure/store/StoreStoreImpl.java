package com.dangdang.check.infrastrucure.store;

import com.dangdang.check.domain.store.Store;
import com.dangdang.check.domain.store.StoreStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreStoreImpl implements StoreStore {

    private final StoreJpaRepository storeJpaRepository;

    @Override
    public Store storeStore(Store store) {
        return storeJpaRepository.save(store);
    }
}
