package com.dangdang.check.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductPayment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal purchasePrice;  // 결제 당시 가겨
    private int quantity; // 구매 수량

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_payment_detail_id")
    private ProductPaymentDetail productPaymentDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
