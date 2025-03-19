package com.dangdang.check.domain.customer;

public interface CustomerValidator {
    void checkRegisterCustomerWithPets(CustomerCommand.RegisterCustomerWithPetsRequest request);

    void checkModifyCustomer(CustomerCommand.ModifyCustomerRequest request);
}
