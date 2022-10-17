package com.hal.CoachesWeb.service;

import com.hal.CoachesWeb.entity.Coaches;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public interface CoachesService {
    Page<Coaches> getAllCoaches(Pageable pageable);
    Page<Coaches> getAllCoachesByCoachId(int id, Pageable pageable);
    Page<Coaches> getAllCoachesByStartDate(LocalDateTime startTime, LocalDateTime endTime, Pageable pageable);
    Optional<Coaches> getCoachesById(int id);
    Coaches addCoaches(Coaches coaches);
    Coaches updateCoaches(Coaches coaches);
    boolean deleteCoaches(int id);
    boolean existsById(int id);
    int getEmptySeatByCoachesId(int id);
}
