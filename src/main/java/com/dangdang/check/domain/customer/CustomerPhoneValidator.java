package com.dangdang.check.domain.customer;

public interface CustomerPhoneValidator {

    void checkRegisterCustomerPhone(CustomerPhoneCommand.RegisterPhoneRequest request);
}
