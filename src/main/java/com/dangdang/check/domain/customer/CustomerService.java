package com.dangdang.check.domain.customer;


public interface CustomerService {

    CustomerInfo registerCustomerWithPets(CustomerCommand.RegisterCustomerWithPetsRequest request);
}
