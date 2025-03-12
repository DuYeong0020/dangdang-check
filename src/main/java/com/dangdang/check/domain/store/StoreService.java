package com.dangdang.check.domain.store;

import com.dangdang.check.infrastrucure.store.StoreCriteria;
import org.springframework.data.domain.Page;

public interface StoreService {

    StoreInfo registerStore(StoreCommand.RegisterStoreRequest request);

    StoreInfo approveStore(Long storeId);

    StoreInfo rejectStore(Long storeId, String reason);

    StoreInfo getStoreById(Long storeId);

    Page<StoreSummaryInfo> getStoresByCriteria(StoreCriteria.GetStores criteria);
}
