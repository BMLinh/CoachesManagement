package com.hal.CoachesWeb.repositories;

import com.hal.CoachesWeb.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Integer> {
    List<District> findAllByCountryId(int id);
}
