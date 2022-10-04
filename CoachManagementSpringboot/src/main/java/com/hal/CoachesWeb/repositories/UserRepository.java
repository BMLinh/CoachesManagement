package com.hal.CoachesWeb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hal.CoachesWeb.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByPhone (String phone);
    Boolean existsByPhone(String phone);
    User findUserByPhone(String phone);
}
