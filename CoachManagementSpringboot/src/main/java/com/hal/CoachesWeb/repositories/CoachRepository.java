package com.hal.CoachesWeb.repositories;


import com.hal.CoachesWeb.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoachRepository extends JpaRepository<Coach, Integer> {
    List<Coach> findAllByCoachGarageId(int id);
    List<Coach> findAllByCoachGarageIdAndStatus(int id, int status);
    List<Coach> findAllByCoachGarageIdIn(List<Integer> ids);
    Optional<Coach> findByIdAndStatus (int id, int status);
    Coach findTopByCoachGarageIdOrderByIdDesc(int id);
    boolean existsByIdAndStatus (int id, int status);
}
