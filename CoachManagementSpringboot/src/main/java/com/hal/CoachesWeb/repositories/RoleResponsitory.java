package com.hal.CoachesWeb.repositories;

import com.hal.CoachesWeb.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleResponsitory extends JpaRepository<Role, Integer> {
    Optional<Role> findByName (String name);
}
