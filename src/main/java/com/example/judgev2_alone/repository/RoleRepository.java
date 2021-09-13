package com.example.judgev2_alone.repository;

import com.example.judgev2_alone.model.entity.Role;
import com.example.judgev2_alone.model.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByRoleEnum(RoleEnum roleEnum);
}
