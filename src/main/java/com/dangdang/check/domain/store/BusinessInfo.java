package com.dangdang.check.domain.store;

import com.dangdang.check.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.security.InvalidParameterException;

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
    private String rejectedReason; // 거절 이유

    @Builder
    public BusinessInfo(String businessRegistrationNumber, String businessName, String representativeName, String businessType, Address businessAddress, RegistrationStatus registrationStatus) {
        if (!StringUtils.hasText(businessRegistrationNumber)) throw new InvalidParameterException();
        if (!StringUtils.hasText(businessName)) throw new InvalidParameterException();
        if (!StringUtils.hasText(representativeName)) throw new InvalidParameterException();
        if (!StringUtils.hasText(businessType)) throw new InvalidParameterException();
        if (businessAddress == null) throw new InvalidParameterException();
        if (registrationStatus == null) throw new InvalidParameterException();


        this.businessRegistrationNumber = businessRegistrationNumber;
        this.businessName = businessName;
        this.representativeName = representativeName;
        this.businessType = businessType;
        this.businessAddress = businessAddress;
        this.registrationStatus = registrationStatus;
    }

    public void modifyRegistrationStatus(RegistrationStatus registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public void approve() {
        this.registrationStatus = RegistrationStatus.APPROVED;
    }

    public void reject(String reason) {
        this.registrationStatus = RegistrationStatus.REJECTED;
        this.rejectedReason = reason;
    }
}
