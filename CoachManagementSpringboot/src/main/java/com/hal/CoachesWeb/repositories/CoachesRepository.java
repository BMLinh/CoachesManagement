package com.hal.CoachesWeb.repositories;

import com.hal.CoachesWeb.entity.Coaches;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface CoachesRepository extends JpaRepository<Coaches, Integer>, JpaSpecificationExecutor<Coaches> {
    Page<Coaches> findAllByCoachId (int id, Pageable pageable);
    List<Coaches> findAllByCoachId (int id);
    List<Coaches> findAllByCoachIdAndStatusNot (int id, int status);
    Page<Coaches> findAllByStartTimeBetween (LocalDateTime startTime, LocalDateTime endTime, Pageable pageable);
    List<Coaches> findAllByStartTimeBetween (LocalDateTime startTime, LocalDateTime endTime);
    boolean existsByCoachId (int id);
}
