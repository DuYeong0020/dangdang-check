package com.dangdang.check.domain.customer;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class CustomerPhoneCommand {

    @Getter
    @ToString
    public static class RegisterPhoneRequest {
        private final String loginId;
        private final Long customerId;
        private final String label;
        private final String phoneNumber;
        private final PhoneType phoneType;

        @Builder
        public RegisterPhoneRequest(String loginId, Long customerId, String label, String phoneNumber, PhoneType phoneType) {
            this.loginId = loginId;
            this.customerId = customerId;
            this.label = label;
            this.phoneNumber = phoneNumber;
            this.phoneType = phoneType;
        }

        public String getPhoneLabelOrDefault() {
            return (label != null && !label.isBlank()) ? label : "보호자";
        }
    }

    @Getter
    @ToString
    public static class ModifyCustomerPhoneRequest {
        private final String loginId;
        private final Long customerId;
        private final Long customerPhoneId;
        private final String label;
        private final String phoneNumber;
        private final PhoneType phoneType;

        @Builder
        public ModifyCustomerPhoneRequest(String loginId, Long customerId, Long customerPhoneId, String label, String phoneNumber, PhoneType phoneType) {
            this.loginId = loginId;
            this.customerId = customerId;
            this.customerPhoneId = customerPhoneId;
            this.label = label;
            this.phoneNumber = phoneNumber;
            this.phoneType = phoneType;
        }
    }
}
