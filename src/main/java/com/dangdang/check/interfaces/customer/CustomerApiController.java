package com.dangdang.check.interfaces.customer;

import com.dangdang.check.common.argumentresolver.Login;
import com.dangdang.check.common.response.CommonResponse;
import com.dangdang.check.domain.customer.CustomerCommand;
import com.dangdang.check.domain.customer.CustomerInfo;
import com.dangdang.check.domain.customer.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CustomerApiController {

    private final CustomerService customerService;

    @PostMapping("/api/customers")
    public CommonResponse<CustomerDto.RegisterCustomerWithPetsResponse> registerCustomerWithPets(@Login String loginId,
                                                                                                 @RequestBody @Valid CustomerDto.RegisterCustomerWithPetsRequest request) {
        CustomerCommand.RegisterCustomerWithPetsRequest command = request.toCommand(loginId);
        CustomerInfo customerInfo = customerService.registerCustomerWithPets(command);
        CustomerDto.RegisterCustomerWithPetsResponse response = new CustomerDto.RegisterCustomerWithPetsResponse(customerInfo);
        return CommonResponse.success(response);
    }

    @PatchMapping("/api/customers/{customerId}")
    public CommonResponse<CustomerDto.ModifyCustomerResponse> modifyCustomer(@Login String loginId, @PathVariable Long customerId,
                                                       @RequestBody @Valid CustomerDto.ModifyCustomerRequest request) {
        CustomerCommand.ModifyCustomerRequest command = request.toCommand(loginId, customerId);
        CustomerInfo customerInfo = customerService.modifyCustomer(command);
        CustomerDto.ModifyCustomerResponse response = new CustomerDto.ModifyCustomerResponse(customerInfo);
        return CommonResponse.success(response);
    }
}
