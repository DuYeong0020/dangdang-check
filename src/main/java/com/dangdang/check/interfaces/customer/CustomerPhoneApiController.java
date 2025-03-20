package com.dangdang.check.interfaces.customer;

import com.dangdang.check.common.argumentresolver.Login;
import com.dangdang.check.common.response.CommonResponse;
import com.dangdang.check.domain.customer.CustomerPhoneCommand;
import com.dangdang.check.domain.customer.CustomerPhoneInfo;
import com.dangdang.check.domain.customer.CustomerPhoneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerPhoneApiController {

    private final CustomerPhoneService customerPhoneService;

    @PostMapping("/api/customers/{customerId}/phones")
    public CommonResponse<CustomerPhoneDto.RegisterCustomerPhoneResponse> registerCustomerPhone(@Login String loginId, @PathVariable Long customerId,
                                                                                                @RequestBody @Valid CustomerPhoneDto.RegisterCustomerPhoneRequest request) {
        CustomerPhoneCommand.RegisterPhoneRequest command = request.toCommand(loginId, customerId);
        CustomerPhoneInfo phoneInfo = customerPhoneService.registerCustomerPhone(command);
        CustomerPhoneDto.RegisterCustomerPhoneResponse response = new CustomerPhoneDto.RegisterCustomerPhoneResponse(phoneInfo);
        return CommonResponse.success(response);
    }

    @PostMapping("/api/customers/{customerId}/phones/{phoneId}")
    public CommonResponse<CustomerPhoneDto.ModifyCustomerPhoneResponse> modifyCustomerPhone(@Login String loginId, @PathVariable Long customerId, @PathVariable Long phoneId,
                                                                                             @RequestBody @Valid CustomerPhoneDto.ModifyCustomerPhoneRequest request) {
        CustomerPhoneCommand.ModifyCustomerPhoneRequest command = request.toCommand(loginId, customerId, phoneId);
        CustomerPhoneInfo phoneInfo = customerPhoneService.modifyCustomerPhone(command);
        CustomerPhoneDto.ModifyCustomerPhoneResponse response = new CustomerPhoneDto.ModifyCustomerPhoneResponse(phoneInfo);
        return CommonResponse.success(response);
    }
}
