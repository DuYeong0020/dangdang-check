package com.dangdang.check.domain.security;

import com.dangdang.check.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.security.InvalidParameterException;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loginId;
    private String refreshToken;
    private String expiration;

    public RefreshToken(String loginId, String refreshToken, String expiration) {
        if (!StringUtils.hasText(loginId)) throw new InvalidParameterException();
        if (!StringUtils.hasText(refreshToken)) throw new InvalidParameterException();
        if (!StringUtils.hasText(expiration)) throw new InvalidParameterException();

        this.loginId = loginId;
        this.refreshToken = refreshToken;
        this.expiration = expiration;
    }
}
