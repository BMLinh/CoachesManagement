package com.hal.CoachesWeb.service;

import com.hal.CoachesWeb.entity.Coach;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public interface CoachService {
    List<Coach> getAllCoach();
    List<Coach> getAllCoachByGarageId(int id);
    List<Coach> getAllActiveCoachByGarageId(int id);
    Optional<Coach> getCoachById(int id);
    Optional<Coach> getActiveCoachById(int id);
    boolean addCoach(Coach coach);
    List<Coach> getAllCoachByUserId(int id);
//    boolean updateStatus(Coach coach);
    boolean updateCoach(Coach coach);
    boolean deleteCoach(int id);
    boolean existsById(int id);
    boolean isActive(int id);
}
