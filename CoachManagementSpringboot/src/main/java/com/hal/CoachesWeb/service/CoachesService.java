package com.hal.CoachesWeb.service;

import com.hal.CoachesWeb.entity.Coaches;
import com.hal.CoachesWeb.model.request.CoachesReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public interface CoachesService {
    Page<Coaches> getAllCoaches(Pageable pageable);
    Page<Coaches> getAllCoachesByCoachId(int id, Pageable pageable);
    List<Coaches> getAllCoaches();
    List<Coaches> getAllCoachesByCoachId(int id);
    Page<Coaches> getAllCoachesByStartDate(LocalDateTime startTime, LocalDateTime endTime, Pageable pageable);
    List<Coaches> getAllCoachesInDay(LocalDate startTime);
    Optional<Coaches> getCoachesById(int id);
    boolean addCoaches(CoachesReq coachesReq);
    boolean updateCoaches(CoachesReq coachesReq);
//    boolean updateStatus(Coaches coaches);
    boolean deleteCoaches(int id);
    boolean existsById(int id);
    int getEmptySeatByCoachesId(int id);
}
