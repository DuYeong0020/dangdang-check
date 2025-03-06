package com.dangdang.check.interfaces.employee;

import com.dangdang.check.common.response.CommonResponse;
import com.dangdang.check.domain.employee.CustomEmployeeDetailsService;
import com.dangdang.check.domain.employee.EmployeeCommand;
import com.dangdang.check.domain.employee.EmployeeInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmployeeApiController {

    private final CustomEmployeeDetailsService customEmployeeDetailsService;

    @PostMapping("/v1/register")
    public CommonResponse<EmployeeDto.RegisterEmployeeResponse> register(@RequestBody @Valid EmployeeDto.RegisterEmployeeRequest request) {
        EmployeeCommand.RegisterEmployeeRequest command = request.toCommand();
        EmployeeInfo employeeInfo = customEmployeeDetailsService.registerEmployee(command);
        EmployeeDto.RegisterEmployeeResponse response = new EmployeeDto.RegisterEmployeeResponse(employeeInfo);
        return CommonResponse.success(response);
    }
}
