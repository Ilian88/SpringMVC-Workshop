package com.example.judgev2_alone.model.entity;

import com.example.judgev2_alone.model.enums.RoleEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table
public class Role extends BaseEntity{

   private  RoleEnum roleEnum;

    public Role() {
    }

    public Role(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }

    @Enumerated(EnumType.STRING)
    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public Role setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
        return this;
    }
}
