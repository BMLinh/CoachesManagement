package com.hal.CoachesWeb.service;

import com.hal.CoachesWeb.entity.CoachesStopBy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CoachesStopByService {
    List<CoachesStopBy> getAllByCoachesId(int id);
    boolean upsertCoachesStopBy(List<CoachesStopBy> coachesStopByList);
    boolean deleteCoachesStopBy(List<CoachesStopBy> coachesStopByList);
}
