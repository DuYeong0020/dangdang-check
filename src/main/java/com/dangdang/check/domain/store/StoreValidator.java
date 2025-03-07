package com.dangdang.check.domain.store;

public interface StoreValidator {
    void checkRegisterStore(StoreCommand.RegisterStoreRequest request);
}
