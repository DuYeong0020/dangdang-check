package com.dangdang.check.interfaces.employee;

import com.dangdang.check.domain.employee.EmployeeCommand;
import com.dangdang.check.domain.employee.EmployeeInfo;
import com.dangdang.check.domain.employee.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;

public class EmployeeDto {

    @Getter
    @ToString
    public static class RegisterEmployeeRequest {
        @NotBlank(message = "nickname은 필수값입니다")
        @Size(min = 2, max = 8, message = "nickname은 최소 2글자, 최대 8글자 입니다.")
        private String nickname;

        @NotBlank(message = "loginId는 필수값입니다.")
        @Size(min = 5, max = 15, message = "loginId는 최소 5자, 최대 15자이어야 합니다.")
        @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "loginId는 영문자와 숫자만 허용됩니다.")
        private String loginId;

        @NotBlank(message = "password는 필수값입니다")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$",
                message = "비밀번호는 '숫자', '문자', '특수문자'를 최소 1개 이상 포함하며, 8자에서 16자까지 허용됩니다.")
        private String password;

        @NotBlank(message = "email은 필수값입니다")
        @Email(message = "email 형식에 맞아야 합니다")
        private String email;

        @NotBlank(message = "휴대폰 번호는 필수 입력 값입니다.")
        @Pattern(regexp = "^\\d{10,11}$", message = "휴대폰 번호는 10~11자리 숫자로 입력해주세요.")
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
