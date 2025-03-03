package com.dangdang.check.domain.employee.controller;

import com.dangdang.check.domain.employee.entity.Employee;
import com.dangdang.check.domain.employee.service.CustomEmployeeDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final CustomEmployeeDetailsService customEmployeeDetailsService;

    @PostMapping("/v1/register")
    public Employee register(@RequestBody Employee employee) {
        return customEmployeeDetailsService.register(employee);
    }
}
