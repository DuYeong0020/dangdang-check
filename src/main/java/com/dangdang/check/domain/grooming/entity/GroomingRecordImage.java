package com.dangdang.check.domain.grooming.entity;

import com.dangdang.check.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroomingRecordImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalFileName; // 원본 파일 이름
    private String storedFileName; // 저장된 파일 이름
    private String url; // 이미지 접근을 위한 URL

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grooming_record_id", nullable = false)
    private GroomingRecord groomingRecord; // 어떤 미용 기록에 속하는 사진인지
}
