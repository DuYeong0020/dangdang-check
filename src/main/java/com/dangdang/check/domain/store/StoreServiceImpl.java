package com.dangdang.check.domain.store;

import com.dangdang.check.domain.employee.Employee;
import com.dangdang.check.domain.employee.EmployeeReader;
import com.dangdang.check.infrastrucure.store.StoreCriteria;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreValidator storeValidator;
    private final StoreStore storeStore;
    private final StoreReader storeReader;
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

    @Override
    public Page<StoreSummaryInfo> getStoresByCriteria(StoreCriteria.GetStores criteria) {
        return storeReader.findByCriteria(criteria);
    }

    @Override
    @Transactional
    public StoreInfo approveStore(Long storeId) {
        Store store = storeReader.findById(storeId);
        BusinessInfo businessInfo = store.getBusinessInfo();
        if (businessInfo == null) {
            throw new EntityNotFoundException("BusinessInfo not found for storeId: " + storeId);
        }
        businessInfo.approve();
        return new StoreInfo(store);
    }

    @Override
    @Transactional
    public StoreInfo rejectStore(Long storeId, String reason) {
        Store store = storeReader.findById(storeId);
        BusinessInfo businessInfo = store.getBusinessInfo();
        if (businessInfo == null) {
            throw new EntityNotFoundException("BusinessInfo not found for storeId: " + storeId);
        }
        businessInfo.reject(reason);
        return new StoreInfo(store);
    }
}
