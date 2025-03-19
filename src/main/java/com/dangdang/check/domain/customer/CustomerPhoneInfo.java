package com.dangdang.check.domain.customer;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CustomerPhoneInfo {

    private final Long id;
    private final String label;
    private final String phoneNumber;
    private final PhoneType phoneType;

    public CustomerPhoneInfo(CustomerPhone customerPhone) {
        this.id = customerPhone.getId();
        this.label = customerPhone.getLabel();
        this.phoneNumber = customerPhone.getPhoneNumber();
        this.phoneType = customerPhone.getPhoneType();
    }
}
