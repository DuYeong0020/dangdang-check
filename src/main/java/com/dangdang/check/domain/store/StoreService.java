package com.dangdang.check.domain.store;

public interface StoreService {

    StoreInfo registerStore(StoreCommand.RegisterStoreRequest request);

    StoreInfo approveStore(Long storeId);

    StoreInfo rejectStore(Long storeId, String reason);
}
