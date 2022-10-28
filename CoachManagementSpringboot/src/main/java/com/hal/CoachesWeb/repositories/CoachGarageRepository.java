package com.hal.CoachesWeb.repositories;

import com.hal.CoachesWeb.entity.CoachGarage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoachGarageRepository extends JpaRepository<CoachGarage, Integer> {
    //    CoachGarage getTopByOrderByIdDesc (int id);
    List<CoachGarage> findAllByUserId (int id);
    List<CoachGarage> findAllByUserIdAndStatus(int id, int status);
    List<CoachGarage> findAllByStatus (int status);
    List<CoachGarage> findAllByStatusIsNot (int status);
    boolean existsByIdAndStatus (int id, int status);
}
