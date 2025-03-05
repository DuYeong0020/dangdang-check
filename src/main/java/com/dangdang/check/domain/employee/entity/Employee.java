package com.dangdang.check.domain.employee.entity;

import com.dangdang.check.common.entity.BaseEntity;
import com.dangdang.check.domain.employee.enums.Role;
import com.dangdang.check.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true, nullable = false)
    private String loginId;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String mobilePhone; // 휴대전화
    @Enumerated(EnumType.STRING)
    private Role role = Role.DEFAULT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    public Employee(String loginId, Role role) {
        this.loginId = loginId;
        this.role = role;
    }

    public void modifyPassword(String password) {
       if (StringUtils.hasText(password)) {
           this.password = password;
       }
    }
}
