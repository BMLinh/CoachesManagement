package com.hal.CoachesWeb.repositories;


import com.hal.CoachesWeb.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoachRepository extends JpaRepository<Coach, Integer> {
    List<Coach> findAllByCoachGarageId(int id);
    List<Coach> findAllByCoachGarageIdAndStatusIsNot (int id, int status);
    Coach findTopByCoachGarageIdOrderByIdDesc(int id);
}
