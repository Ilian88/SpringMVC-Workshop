package com.example.judgev2_alone.security;

import com.example.judgev2_alone.model.enums.RoleEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

    private Long id;

    private String username;

    private RoleEnum roleEnum;

    public CurrentUser() {
    }

    public Long getId() {
        return id;
    }

    public CurrentUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CurrentUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public Boolean isAnonymous() {
        return this.username == null;
    }


    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public CurrentUser setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
        return this;
    }

    public boolean isAdmin() {
        return this.roleEnum == RoleEnum.ADMIN;
    }
}
