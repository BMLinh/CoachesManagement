package com.hal.CoachesWeb.service;

import com.hal.CoachesWeb.entity.Coaches;
import com.hal.CoachesWeb.model.request.CoachesReq;
import com.hal.CoachesWeb.model.response.CoachesDetailRes;
import com.hal.CoachesWeb.model.response.CoachesGetRes;
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
    List<Coaches> getCoachesByUserId(int id);
    Page<Coaches> getAllCoachesByStartDate(LocalDateTime startTime, LocalDateTime endTime, Pageable pageable);
    List<CoachesDetailRes> getAllCoachesByStartDate(String startTime, String endTime, LocalDate startDate, int startPoint, int endPoint
            , Integer minPrice, Integer maxPrice, Integer pickUp, Integer dropOff, Integer emptySeat
            , Integer coachGarage, int status);
    List<Coaches> getAllCoachesInDay(LocalDate startTime);
    Optional<Coaches> getCoachesById(int id);
    CoachesDetailRes getCoachesDetailById(int id);
    boolean addCoaches(CoachesReq coachesReq);
    boolean updateCoaches(CoachesReq coachesReq);
//    boolean updateStatus(Coaches coaches);
    boolean deleteCoaches(int id);
    boolean existsById(int id);
    boolean isActive(int id);
    int getEmptySeatByCoachesId(int id);
}
