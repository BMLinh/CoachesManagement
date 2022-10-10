package com.hal.CoachesWeb.repositories;

import com.hal.CoachesWeb.entity.CoachGarage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachGarageRepository extends JpaRepository<CoachGarage, Integer> {
    //    CoachGarage getTopByOrderByIdDesc (int id);
}
