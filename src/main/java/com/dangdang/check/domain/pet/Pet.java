package com.dangdang.check.domain.pet;

import com.dangdang.check.domain.BaseEntity;
import com.dangdang.check.domain.customer.Customer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pet extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // 이름
    @Enumerated(EnumType.STRING)
    private Gender gender; // 성별
    private Boolean neutered; // 중성화 여부
    private Boolean vaccinated; // 예방접종 여부
    @Lob
    private String specialNotes; // 특이사항
    private LocalDate birthday; // 생년월일
    private Double weight; // 체중

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "breed_id")
    private Breed breed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
