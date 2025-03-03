package com.dangdang.check.domain.customer.entity;

import com.dangdang.check.common.entity.BaseEntity;
import com.dangdang.check.common.enums.PhoneType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
