package com.hal.CoachesWeb.service;

import com.hal.CoachesWeb.entity.CoachGarage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CoachGarageService {
    List<CoachGarage> getAllCoachGarage();
    Optional<CoachGarage> getCoachGarageById(int id);
    boolean addCoachGarage(CoachGarage coachGarage);
    boolean updateCoachGarage(CoachGarage coachGarage);
    boolean deleteCoachGarage(int id);
    boolean existsById(int id);
}
