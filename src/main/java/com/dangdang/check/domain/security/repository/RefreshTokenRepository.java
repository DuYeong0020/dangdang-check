package com.dangdang.check.domain.security.repository;

import com.dangdang.check.domain.security.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Boolean existsByRefreshToken(String refreshToken);

    @Transactional
    void deleteByRefreshToken(String refreshToken);
}
