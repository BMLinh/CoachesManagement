package com.hal.CoachesWeb.service;

import com.hal.CoachesWeb.entity.CoachGarage;
import com.hal.CoachesWeb.entity.Coaches;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public interface CoachesService {
    Page<Coaches> getAllCoaches(Pageable pageable);
    Page<Coaches> getAllCoachesByCoachId(int id, Pageable pageable);
    List<Coaches> getAllCoachesByCoachId(int id);
    Page<Coaches> getAllCoachesByStartDate(LocalDateTime startTime, LocalDateTime endTime, Pageable pageable);
    Optional<Coaches> getCoachesById(int id);
    boolean addCoaches(Coaches coaches);
    boolean updateCoaches(Coaches coaches);
//    boolean updateStatus(Coaches coaches);
    boolean deleteCoaches(int id);
    boolean existsById(int id);
    int getEmptySeatByCoachesId(int id);
}
