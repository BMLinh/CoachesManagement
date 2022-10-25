package com.hal.CoachesWeb.repositories;

import com.hal.CoachesWeb.entity.CoachesStopBy;
import com.hal.CoachesWeb.entity.CoachesStopByPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoachesStopByRepository extends JpaRepository<CoachesStopBy, CoachesStopByPK> {
    List<CoachesStopBy> findAllByCoachesId(int id);
    boolean existsByCoachesIdAndStopById (int coachesId, int stopById);

    boolean existsByCoachesId (int id);
}
