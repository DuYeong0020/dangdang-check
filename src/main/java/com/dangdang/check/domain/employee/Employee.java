package com.dangdang.check.domain.employee;

import com.dangdang.check.domain.BaseEntity;
import com.dangdang.check.domain.store.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.security.InvalidParameterException;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
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
        if (!StringUtils.hasText(loginId)) throw new InvalidParameterException();
        if (role == null) throw new InvalidParameterException();

        this.loginId = loginId;
        this.role = role;
    }

    @Builder
    public Employee(String nickname, String loginId, String password, String email, String mobilePhone) {
        if (!StringUtils.hasText(nickname)) throw new InvalidParameterException();
        if (!StringUtils.hasText(loginId)) throw new InvalidParameterException();
        if (!StringUtils.hasText(password)) throw new InvalidParameterException();
        if (!StringUtils.hasText(email)) throw new InvalidParameterException();
        if (!StringUtils.hasText(mobilePhone)) throw new InvalidParameterException();

        this.nickname = nickname;
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.mobilePhone = mobilePhone;
    }

    public void modifyStore(Store store) {
        if (store != null) {
            this.store = store;
        }
    }
}
