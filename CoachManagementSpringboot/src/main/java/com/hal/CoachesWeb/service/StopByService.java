package com.hal.CoachesWeb.service;

import com.hal.CoachesWeb.entity.StopBy;
import com.hal.CoachesWeb.model.response.StopByRes;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StopByService {
    List<StopBy> getAllStopBy();
    List<StopBy> getAllStopByCoachesId(int id, int status);
    List<StopBy> getAllStopByCountryId(int id);
    List<StopByRes> getAllStopByResCountryId(int id);
    Optional<StopBy> getStopByById(int id);
    boolean addStopBy(StopBy stopBy);
    boolean updateStopBy(StopBy stopBy);
    boolean deleteStopBy(int id);
    boolean existsById(int id);
}
