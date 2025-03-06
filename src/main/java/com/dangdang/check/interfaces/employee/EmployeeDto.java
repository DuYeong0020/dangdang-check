package com.dangdang.check.interfaces.employee;

import com.dangdang.check.domain.employee.EmployeeCommand;
import com.dangdang.check.domain.employee.EmployeeInfo;
import com.dangdang.check.domain.employee.Role;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.bind.annotation.RequestParam;

public class EmployeeDto {

    @Getter
    @ToString
    public static class RegisterEmployeeRequest {
        private String nickname;
        private String loginId;
        private String password;
        private String email;
        private String mobilePhone;

        public EmployeeCommand.RegisterEmployeeRequest toCommand() {
            return EmployeeCommand.RegisterEmployeeRequest.builder()
                    .nickname(nickname)
                    .loginId(loginId)
                    .password(password)
                    .email(email)
                    .mobilePhone(mobilePhone)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class RegisterEmployeeResponse {
        private final Long id;
        private final String nickname;
        private final String loginId;
        private final String email;
        private final Role role;
        private final String mobilePhone;

        public RegisterEmployeeResponse(EmployeeInfo employeeInfo) {
            this.id = employeeInfo.getId();
            this.nickname = employeeInfo.getNickname();
            this.loginId = employeeInfo.getLoginId();
            this.email = employeeInfo.getEmail();
            this.role = employeeInfo.getRole();
            this.mobilePhone = employeeInfo.getMobilePhone();
        }
    }
}
