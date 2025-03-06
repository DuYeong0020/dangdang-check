package com.dangdang.check.domain.employee;

import lombok.Getter;

@Getter
public class EmployeeInfo {

    private final Long id;
    private final String nickname;
    private final String loginId;
    private final String email;
    private final Role role;
    private final String mobilePhone;


    public EmployeeInfo(Employee employee) {
        this.id = employee.getId();
        this.nickname = employee.getNickname();
        this.loginId = employee.getLoginId();
        this.email = employee.getEmail();
        this.role = employee.getRole();
        this.mobilePhone = employee.getMobilePhone();
    }
}
