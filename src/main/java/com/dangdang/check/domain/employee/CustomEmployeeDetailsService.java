package com.dangdang.check.domain.employee;

import com.dangdang.check.infrastrucure.employee.EmployeeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomEmployeeDetailsService implements UserDetailsService, EmployeeService {

    private final EmployeeReader employeeReader;
    private final EmployeeStore employeeStore;
    private final EmployeeValidator employeeValidator;
    private final PasswordEncoder passwordEncoder;

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