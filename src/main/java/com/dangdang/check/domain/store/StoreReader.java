package com.dangdang.check.domain.store;

import com.dangdang.check.infrastrucure.store.StoreCriteria;
import org.springframework.data.domain.Page;

public interface StoreReader {
    Store findById(Long id);

    Page<StoreSummaryInfo> findByCriteria(StoreCriteria.GetStores criteria);
}