package com.hal.CoachesWeb.repositories;

import com.hal.CoachesWeb.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach, String> {
}
