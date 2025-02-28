package com.dangdang.check.filter;

import com.dangdang.check.domain.Employee;
import com.dangdang.check.domain.Role;
import com.dangdang.check.dto.CustomEmployeeDetails;
import com.dangdang.check.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.startsWith("/login") || path.startsWith("/v1/register");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = authorization.substring(7);

        if (jwtUtil.isExpired(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        String loginId = jwtUtil.getLoginId(token);
        Role role = Role.valueOf(jwtUtil.getRole(token));

        Employee employee = new Employee(loginId, role);
        CustomEmployeeDetails employeeDetails = new CustomEmployeeDetails(employee);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(employeeDetails, null, employeeDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
