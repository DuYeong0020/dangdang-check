package com.dangdang.check.domain.store;

import com.dangdang.check.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BusinessInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String businessRegistrationNumber; // 사업자등록번호
    private String businessName; // 사업자 상호명
    private String representativeName; // 대표자 이름
    private String businessType; // 업종
    @Embedded
    private Address businessAddress;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RegistrationStatus registrationStatus; // 승인 상태

    @Builder
    public BusinessInfo(String businessRegistrationNumber, String businessName, String representativeName, String businessType, Address businessAddress, RegistrationStatus registrationStatus) {
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.businessName = businessName;
        this.representativeName = representativeName;
        this.businessType = businessType;
        this.businessAddress = businessAddress;
        this.registrationStatus = registrationStatus;
    }
}
