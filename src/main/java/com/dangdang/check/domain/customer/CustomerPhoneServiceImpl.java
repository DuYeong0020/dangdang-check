package com.dangdang.check.domain.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerPhoneServiceImpl implements CustomerPhoneService {

    private final CustomerPhoneValidator customerPhoneValidator;
    private final CustomerPhoneStore customerPhoneStore;
    private final CustomerReader customerReader;


    @Override
    @Transactional
    public CustomerPhoneInfo registerCustomerPhone(CustomerPhoneCommand.RegisterPhoneRequest request) {
        customerPhoneValidator.checkRegisterCustomerPhone(request);
        Customer customer = customerReader.findById(request.getCustomerId());
        CustomerPhone initCustomerPhone = new CustomerPhone(request.getPhoneLabelOrDefault(), request.getPhoneNumber(), request.getPhoneType());
        CustomerPhone customerPhone = customerPhoneStore.storeCustomerPhone(initCustomerPhone);
        customer.addPhone(customerPhone);

        return new CustomerPhoneInfo(customerPhone);
    }


}