package com.dangdang.check.domain.payment.entity;

import com.dangdang.check.domain.prepaidticket.entity.PrepaidTicket;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@DiscriminatorValue("PREPAIDTICKET")
public class PrepaidTicketPaymentDetail extends PaymentDetail {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prepaid_ticket_id")
    private PrepaidTicket prepaidTicket;
}
