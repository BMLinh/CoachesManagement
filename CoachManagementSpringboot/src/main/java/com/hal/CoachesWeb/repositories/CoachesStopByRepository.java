package com.hal.CoachesWeb.repositories;

import com.hal.CoachesWeb.entity.CoachesStopBy;
import com.hal.CoachesWeb.entity.CoachesStopByPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface CoachesStopByRepository extends JpaRepository<CoachesStopBy, CoachesStopByPK> {
    List<CoachesStopBy> findAllByCoachesId(int id);
    List<CoachesStopBy> findAllByCoachesIdAndStatusIs (int id, int status);
    List<CoachesStopBy> findAllByCoachesIdInAndStatus (List<Integer> ids, int status);
    boolean existsByCoachesIdAndStopByIdIs (int coachesId, int stopById);
    boolean existsByCoachesId (int id);
}
