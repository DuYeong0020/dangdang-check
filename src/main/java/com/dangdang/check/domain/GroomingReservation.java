package com.dangdang.check.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroomingReservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title; // 예약 제목 (예: "2시 코코 스포팅")
    @Lob
    private String groomingRequest; // 요청 사항
    @Column(nullable = false)
    private LocalDateTime startAt; // 시작 시간
    @Column(nullable = false)
    private LocalDateTime endAt; // 끝나는 시간
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus; // 예약 상태

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
