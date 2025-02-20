package com.dangdang.check.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@DiscriminatorValue("GROOMING")
public class GroomingPaymentDetail extends PaymentDetail {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grooming_reservation_id")
    private GroomingReservation groomingReservation; // 미용 예약 정보
}