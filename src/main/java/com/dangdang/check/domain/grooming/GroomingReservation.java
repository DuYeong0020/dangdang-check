package com.dangdang.check.domain.grooming;

import com.dangdang.check.domain.BaseEntity;
import com.dangdang.check.domain.customer.Customer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "groomingReservation")
    private List<GroomingReservationPet> groomingReservationPets = new ArrayList<>();

    public GroomingReservation(String title, String groomingRequest, LocalDateTime startAt, LocalDateTime endAt, ReservationStatus reservationStatus, Customer customer) {
        if (!StringUtils.hasText(title)) throw new InvalidParameterException();
        if (startAt == null) throw new InvalidParameterException();
        if (endAt == null) throw new InvalidParameterException();
        if (reservationStatus == null) throw new InvalidParameterException();
        if (customer == null) throw new InvalidParameterException();

        this.title = title;
        this.groomingRequest = groomingRequest;
        this.startAt = startAt;
        this.endAt = endAt;
        this.reservationStatus = reservationStatus;
        this.customer = customer;
    }

    public void addGroomingReservationPet(GroomingReservationPet groomingReservationPet) {
        groomingReservationPets.add(groomingReservationPet);
        groomingReservationPet.addGroomingReservation(this);
    }
}
