package com.example.judgev2_alone.service.impl;

import com.example.judgev2_alone.model.entity.Role;
import com.example.judgev2_alone.model.enums.RoleEnum;
import com.example.judgev2_alone.repository.RoleRepository;
import com.example.judgev2_alone.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initRoles() {
        if (this.roleRepository.count() == 0) {
            Role admin = new Role(RoleEnum.ADMIN);
            Role user = new Role(RoleEnum.USER);

            roleRepository.save(admin);
            roleRepository.save(user);
        }
    }

    @Override
    public Role findRole(RoleEnum roleEnum) {
        return this.roleRepository.findByRoleEnum(roleEnum).orElse(null);
    }
}
