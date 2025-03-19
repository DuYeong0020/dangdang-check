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
}
