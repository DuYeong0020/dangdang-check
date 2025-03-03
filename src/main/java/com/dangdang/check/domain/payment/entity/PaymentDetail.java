package com.dangdang.check.domain.payment.entity;

import com.dangdang.check.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Entity
@DiscriminatorColumn(name = "payment_type")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class PaymentDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount; // 해당 결제 금액

    @ManyToOne(fetch = FetchType.LAZY)
    private Payment payment; // 결제 정보

}
