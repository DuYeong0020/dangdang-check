package com.dangdang.check.domain.payment;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
@DiscriminatorValue("PRODUCT")
public class ProductPaymentDetail extends PaymentDetail {
}
