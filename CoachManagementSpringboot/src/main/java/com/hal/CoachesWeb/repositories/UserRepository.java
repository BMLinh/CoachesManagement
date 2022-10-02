package com.hal.CoachesWeb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hal.CoachesWeb.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByPhone (String phone);
}
