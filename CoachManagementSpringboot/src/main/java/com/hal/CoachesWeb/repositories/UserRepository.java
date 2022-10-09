package com.hal.CoachesWeb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hal.CoachesWeb.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Boolean existsByPhone(String phone);
    Optional<User> getUserByPhone(String phone);
}
