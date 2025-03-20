package com.dangdang.check.interfaces.customer;

import com.dangdang.check.domain.customer.CustomerPhoneCommand;
import com.dangdang.check.domain.customer.CustomerPhoneInfo;
import com.dangdang.check.domain.customer.PhoneType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.ToString;

public class CustomerPhoneDto {

    @Getter
    @ToString
    public static class RegisterCustomerPhoneRequest {

        private String label;

        @NotBlank(message = "전화번호는 필수 입력 값입니다.")
        @Pattern(
                regexp = "^(0\\d{1,2}\\d{3,4}\\d{4}|01[016789]\\d{7,8})$",
                message = "올바른 전화번호 형식이 아닙니다. (예: 0212345678 또는 01012345678)"
        )
        private String phoneNumber;

        @NotNull(message = "전화번호 유형은 필수입니다.")
        private PhoneType phoneType;

        public CustomerPhoneCommand.RegisterPhoneRequest toCommand(String loginId, Long customerId) {
            return CustomerPhoneCommand.RegisterPhoneRequest.builder()
                    .loginId(loginId)
                    .customerId(customerId)
                    .label(label)
                    .phoneNumber(phoneNumber)
                    .phoneType(phoneType)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class RegisterCustomerPhoneResponse {
        private final Long id;
        private final String label;
        private final String phoneNumber;
        private final PhoneType phoneType;

        public RegisterCustomerPhoneResponse(CustomerPhoneInfo customerPhoneInfo) {
            this.id = customerPhoneInfo.getId();
            this.label = customerPhoneInfo.getLabel();
            this.phoneNumber = customerPhoneInfo.getPhoneNumber();
            this.phoneType = customerPhoneInfo.getPhoneType();
        }
    }
    @Getter
    @ToString
    public static class ModifyCustomerPhoneRequest {
        private String label;
        @Pattern(
                regexp = "^(0\\d{1,2}\\d{3,4}\\d{4}|01[016789]\\d{7,8})$",
                message = "올바른 전화번호 형식이 아닙니다. (예: 0212345678 또는 01012345678)"
        )
        private String phoneNumber;
        private PhoneType phoneType;

        public CustomerPhoneCommand.ModifyCustomerPhoneRequest toCommand(String loginId, Long customerId, Long phoneId) {
            return CustomerPhoneCommand.ModifyCustomerPhoneRequest.builder()
                    .loginId(loginId)
                    .customerId(customerId)
                    .customerPhoneId(phoneId)
                    .label(label)
                    .phoneNumber(phoneNumber)
                    .phoneType(phoneType)
                    .build();
        }

    }

    @Getter
    @ToString
    public static class ModifyCustomerPhoneResponse {
        private final Long id;
        private final String label;
        private final String phoneNumber;
        private final PhoneType phoneType;

        public ModifyCustomerPhoneResponse(CustomerPhoneInfo customerPhoneInfo) {
            this.id = customerPhoneInfo.getId();
            this.label = customerPhoneInfo.getLabel();
            this.phoneNumber = customerPhoneInfo.getPhoneNumber();
            this.phoneType = customerPhoneInfo.getPhoneType();
        }
    }
}
