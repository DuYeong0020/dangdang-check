package com.dangdang.check.domain.store;

import com.dangdang.check.domain.BaseEntity;
import com.dangdang.check.domain.employee.Employee;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Embedded
    private Address address;
    private String email;
    @Column(nullable = false)
    private String mainPhone; // 매장 대표 전화번호

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Employee owner; // 점주 정보

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "business_info_id", nullable = false)
    private BusinessInfo businessInfo;

    @Builder
    public Store(String name, Address address, String email, String mainPhone, Employee owner, BusinessInfo businessInfo) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.mainPhone = mainPhone;
        this.owner = owner;
        this.businessInfo = businessInfo;
    }
}
