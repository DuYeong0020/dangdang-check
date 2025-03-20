package com.dangdang.check.domain.customer;

import com.dangdang.check.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.security.InvalidParameterException;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomerPhone extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String label; // 전화번호 별칭 (예: "엄마", "아빠", "회사")
    @Column(nullable = false)
    private String phoneNumber; // 전화번호
    @Enumerated(EnumType.STRING)
    private PhoneType phoneType; // 전화번호 유형 (예: MOBILE, HOME, ...)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public CustomerPhone(String label, String phoneNumber, PhoneType phoneType) {
        if (!StringUtils.hasText(label)) throw new InvalidParameterException();
        if (!StringUtils.hasText(phoneNumber)) throw new InvalidParameterException();
        if (phoneType == null) throw new InvalidParameterException();

        this.label = label;
        this.phoneNumber = phoneNumber;
        this.phoneType = phoneType;
    }

    public void modifyLabel(String label) {
        if (StringUtils.hasText(label)) {
            this.label = label;
        }
    }

    public void modifyPhoneNumber(String phoneNumber) {
        if (StringUtils.hasText(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        }

    }

    public void modifyPhoneType(PhoneType phoneType) {
        if (phoneType != null) {
            this.phoneType = phoneType;
        }
    }

    public void modifyCustomer(Customer customer) {
        this.customer = customer;
    }
}
