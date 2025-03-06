package com.dangdang.check.domain.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomEmployeeDetailsService implements UserDetailsService, EmployeeService {

    private final EmployeeReader employeeReader;
    private final EmployeeStore employeeStore;
    private final EmployeeValidator employeeValidator;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public EmployeeInfo registerEmployee(EmployeeCommand.RegisterEmployeeRequest request) {
        employeeValidator.checkRegisterEmployee(request);
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Employee initEmployee = request.toEntity(encodedPassword);
        Employee employee = employeeStore.storeEmployee(initEmployee);
        return new EmployeeInfo(employee);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeReader.findByLoginId(username);
        return new CustomEmployeeDetails(employee);
    }
}