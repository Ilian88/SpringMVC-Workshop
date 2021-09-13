package com.example.judgev2_alone.service;

import com.example.judgev2_alone.model.entity.Role;
import com.example.judgev2_alone.model.enums.RoleEnum;

public interface RoleService {
    void initRoles();

    Role findRole(RoleEnum roleEnum);
}
