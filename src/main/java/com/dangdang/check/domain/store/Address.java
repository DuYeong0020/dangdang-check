package com.dangdang.check.domain.store;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Address {
    private String zipCode; // 우편번호
    private String state; // 시/도
    private String city; // 시/군/구
    private String street; // 도로명 또는 지번 주소
    private String detail; // 상세 주소 (건물명, 동/호수 등)
}
