package com.dangdang.check.filter;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Map.Entry<String, String> credentials = extractCredentials(request);

        if (credentials == null) {
            throw new AuthenticationException("Invalid login request") {};
        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(credentials.getKey(), credentials.getValue(), null);
        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

    }

    private Map.Entry<String, String> extractCredentials(HttpServletRequest request) {
        try {
            JsonNode jsonNode = objectMapper.readTree(request.getInputStream());
            String loginId = jsonNode.has("loginId") ? jsonNode.get("loginId").asText() : null;
            String password = jsonNode.has("password") ? jsonNode.get("password").asText() : null;

            if (loginId == null || password == null) {
                return null;
            }

            return Map.entry(loginId, password);
        } catch (IOException e) {
            throw new AuthenticationException("Failed to parse JSON request") {};
        }
    }
}
