package com.hal.CoachesWeb.service;

import com.hal.CoachesWeb.entity.CoachGarage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CoachGarageService {
    List<CoachGarage> getCoachGarage();
    List<CoachGarage> getCoachGarageByStatus(int status);
    List<CoachGarage> getCoachGarageByUserId(int id);
    Optional<CoachGarage> getCoachGarageById(int id);
    boolean acceptCoachGarage(CoachGarage coachGarage);
    boolean rejectCoachGarage(CoachGarage coachGarage);
    boolean addCoachGarage(CoachGarage coachGarage);
    boolean updateCoachGarage(CoachGarage coachGarage);
//    boolean updateStatus(CoachGarage coachGarage);
    boolean deleteCoachGarage(int id);
    boolean existsById(int id);
    boolean isActive(int id);
}
