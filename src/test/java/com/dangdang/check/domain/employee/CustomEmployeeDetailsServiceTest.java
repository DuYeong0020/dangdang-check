package com.dangdang.check.domain.employee;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomEmployeeDetailsServiceTest {

    @Mock
    private EmployeeStore employeeStore;

    @Mock
    private EmployeeReader employeeReader;

    @Mock
    private EmployeeValidator employeeValidator;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CustomEmployeeDetailsService customEmployeeDetailsService;

    @Test
    @DisplayName("직원 등록 성공")
    void registerEmployee_직원등록_성공() {
        // given
        EmployeeCommand.RegisterEmployeeRequest request = new EmployeeCommand.RegisterEmployeeRequest("test","test", "test!!","test@gmail.com",  "01012345678");
        String encodedPassword = "encodedPassword";
        Employee initEmployee = new Employee("test", "test", "test!!", "test@gmail.com", "01012345678");

        given(passwordEncoder.encode(request.getPassword())).willReturn(encodedPassword);
        given(employeeStore.storeEmployee(any(Employee.class))).willReturn(initEmployee);

        // when
        EmployeeInfo employeeInfo = customEmployeeDetailsService.registerEmployee(request);

        // then
        verify(employeeValidator).checkRegisterEmployee(request);
        verify(passwordEncoder).encode(request.getPassword());
        verify(employeeStore).storeEmployee(any(Employee.class));

        assertNotNull(encodedPassword);
        assertEquals("test", employeeInfo.getLoginId());
        assertEquals("test", employeeInfo.getNickname());
        assertEquals(Role.DEFAULT, employeeInfo.getRole());
    }

    @Test
    @DisplayName("직원 정보 조회 성공")
    void loadUserByUsername_직원정보조회_성공() {
        // given
        String loginId = "test";
        Employee initEmployee = new Employee("test", "test", "test!!", "test@gmail.com", "01012345678");
        given(employeeReader.findByLoginId(loginId)).willReturn(initEmployee);

        // when
        UserDetails userDetails = customEmployeeDetailsService.loadUserByUsername(loginId);

        // then
        assertNotNull(userDetails);
        assertEquals(loginId, userDetails.getUsername());
        assertEquals("test!!", userDetails.getPassword());
        assertInstanceOf(CustomEmployeeDetails.class, userDetails);
        verify(employeeReader).findByLoginId(loginId);
    }
}