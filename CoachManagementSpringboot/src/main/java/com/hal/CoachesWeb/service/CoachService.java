package com.hal.CoachesWeb.service;

import com.hal.CoachesWeb.entity.Coach;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CoachService {
    List<Coach> getAllCoach();
    List<Coach> getAllCoachByGarageId(int id);
    Optional<Coach> getCoachById(int id);
    Coach addCoach(Coach coach);
    Coach updateCoach(Coach coach);
    boolean deleteCoach(int id);
    boolean existsById(int id);
    int getSeatByCoachId(int id);
}
