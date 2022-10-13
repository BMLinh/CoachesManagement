package com.hal.CoachesWeb.repositories;

import com.hal.CoachesWeb.entity.Coaches;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Date;

public interface CoachesRepository extends JpaRepository<Coaches, Integer> {
    Page<Coaches> findAllByCoachId (int id, Pageable pageable);
    Page<Coaches> findAllByStartTimeBetween (LocalDateTime startTime, LocalDateTime endTime, Pageable pageable);
    Coaches findTopByCoachIdOrderByIdDesc (int id);
}
