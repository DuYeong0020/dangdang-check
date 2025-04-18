package com.dangdang.check.domain.customer;

public interface CustomerPhoneService {

    CustomerPhoneInfo registerCustomerPhone(CustomerPhoneCommand.RegisterPhoneRequest request);

    CustomerPhoneInfo modifyCustomerPhone(CustomerPhoneCommand.ModifyCustomerPhoneRequest request);
}
