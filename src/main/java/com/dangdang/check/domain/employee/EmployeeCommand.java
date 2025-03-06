package com.dangdang.check.domain.employee;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class EmployeeCommand {

    @Getter
    @ToString
    public static class RegisterEmployeeRequest {

        private final String nickname;
        private final String loginId;
        private final String password;
        private final String email;
        private final String mobilePhone;

        @Builder
        public RegisterEmployeeRequest(String nickname, String loginId, String password, String email, String mobilePhone) {
            this.nickname = nickname;
            this.loginId = loginId;
            this.password = password;
            this.email = email;
            this.mobilePhone = mobilePhone;
        }

        public Employee toEntity(String encodedPassword) {
            return Employee.builder()
                    .nickname(nickname)
                    .loginId(loginId)
                    .password(encodedPassword)
                    .email(email)
                    .mobilePhone(mobilePhone)
                    .build();
        }
    }
}
