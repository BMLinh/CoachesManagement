package com.hal.CoachesWeb.repositories;

import com.hal.CoachesWeb.entity.StopBy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StopByRepository extends JpaRepository<StopBy, Integer> {
    List<StopBy> findAllByStatus(int status);
    List<StopBy> findAllByDistrictIdAndStatus(int id, int status);
}
