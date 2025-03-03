package com.dangdang.check.domain.employee.service;

import com.dangdang.check.domain.employee.entity.Employee;
import com.dangdang.check.domain.employee.dto.CustomEmployeeDetails;
import com.dangdang.check.domain.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomEmployeeDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public Employee register(Employee employee) {
        employee.modifyPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByLoginId(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return new CustomEmployeeDetails(employee);
    }
}