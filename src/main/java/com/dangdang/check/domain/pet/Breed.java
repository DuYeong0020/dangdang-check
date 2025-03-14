package com.dangdang.check.domain.pet;

import com.dangdang.check.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Breed extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;  // 품종명 (예: 리트리버, 닥스훈트)
    @Enumerated(EnumType.STRING)
    private Species species; // 종 (예: 개, 고양이)

}
