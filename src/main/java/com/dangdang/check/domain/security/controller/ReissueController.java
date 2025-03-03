package com.dangdang.check.domain.security.controller;

import com.dangdang.check.domain.security.entity.RefreshToken;
import com.dangdang.check.domain.security.repository.RefreshTokenRepository;
import com.dangdang.check.common.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
public class ReissueController {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;


    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        // get refresh token
        String refreshToken = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("refresh")) {
                refreshToken = cookie.getValue();
            }
        }

        if (refreshToken == null) {
            // response status code
            return new ResponseEntity<>("refresh token null", HttpStatus.BAD_REQUEST);
        }
        // expired check
        try {
            jwtUtil.isExpired(refreshToken);
        } catch (ExpiredJwtException e) {
            // response status code
            return new ResponseEntity<>("refresh token expired", HttpStatus.BAD_REQUEST);
        }

        // 토큰이 refresh인지 확인(발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(refreshToken);

        if (!category.equals("refresh")) {
            // response status code
            return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
        }
        // DB에 저장되어 있는지 확인
        Boolean isExist = refreshTokenRepository.existsByRefreshToken(refreshToken);
        if (!isExist) {
            // response body
            return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
        }

        String loginId = jwtUtil.getLoginId(refreshToken);
        String role = jwtUtil.getRole(refreshToken);

        // make new JWT
        String newAccessToken = jwtUtil.createJwt("access", loginId, role, 600000L);
        String newRefreshToken = jwtUtil.createJwt("refresh", loginId, role, 86400000L);

        // Refresh 토큰 저장 DB에 기존의 Refresh 토큰 삭제 새 Refresh 토큰 저장
        refreshTokenRepository.deleteByRefreshToken(refreshToken);
        storeRefreshToken(loginId, refreshToken, 86400000L);


        // response
        response.setHeader("access", newAccessToken);
        response.addCookie(createCookie("refresh", newRefreshToken));

        return new ResponseEntity<>(HttpStatus.OK);

    }

    private void storeRefreshToken(String loginId, String refreshToken, Long expiredMs) {
        Date date = new Date(System.currentTimeMillis() + expiredMs);

        RefreshToken initRefreshToken = new RefreshToken(loginId, refreshToken, date.toString());
        refreshTokenRepository.save(initRefreshToken);
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setHttpOnly(true);
        return cookie;
    }
}
