package com.dangdang.check.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroomingRequestImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalFileName; // 업로드한 원본 파일 이름
    private String storedFileName; // 저장된 파일 이름
    private String url; // 이미지 접근을 위한 URL

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet; // 어떤 반려견을 위한 요청인지
}
