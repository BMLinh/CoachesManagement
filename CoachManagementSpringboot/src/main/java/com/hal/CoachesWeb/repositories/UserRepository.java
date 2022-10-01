package com.hal.CoachesWeb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hal.CoachesWeb.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
