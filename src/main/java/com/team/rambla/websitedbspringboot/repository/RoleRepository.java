package com.team.rambla.websitedbspringboot.repository;

import com.team.rambla.websitedbspringboot.entity.ERoles;
import com.team.rambla.websitedbspringboot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERoles name);
    Boolean existsByName(ERoles name);
}
