package com.dangdang.check.domain.store;

import com.dangdang.check.domain.employee.Employee;
import com.dangdang.check.domain.employee.EmployeeReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreValidator storeValidator;
    private final StoreStore storeStore;
    private final EmployeeReader employeeReader;

    @Override
    @Transactional
    public StoreInfo registerStore(StoreCommand.RegisterStoreRequest request) {
        storeValidator.checkRegisterStore(request);
        Employee employee = employeeReader.findByLoginId(request.getLoginId());
        Store initStore = request.toEntity(RegistrationStatus.PENDING, employee);
        Store store = storeStore.storeStore(initStore);
        employee.modifyStore(store);
        return new StoreInfo(store);
    }
}
