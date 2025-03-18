package com.dangdang.check.domain.store;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.security.InvalidParameterException;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {
    private String zipCode; // 우편번호
    private String state; // 시/도
    private String city; // 시/군/구
    private String street; // 도로명 또는 지번 주소
    private String detail; // 상세 주소 (건물명, 동/호수 등)

    @Builder
    public Address(String zipCode, String state, String city, String street, String detail) {
        if (!StringUtils.hasText(zipCode)) throw new InvalidParameterException();
        if (!StringUtils.hasText(state)) throw new InvalidParameterException();
        if (!StringUtils.hasText(city)) throw new InvalidParameterException();
        if (!StringUtils.hasText(street)) throw new InvalidParameterException();

        this.zipCode = zipCode;
        this.state = state;
        this.city = city;
        this.street = street;
        this.detail = detail;
    }
}
