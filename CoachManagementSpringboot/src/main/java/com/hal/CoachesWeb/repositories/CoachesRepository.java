package com.hal.CoachesWeb.repositories;

import com.hal.CoachesWeb.entity.Coaches;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.List;

public interface CoachesRepository extends JpaRepository<Coaches, Integer>, JpaSpecificationExecutor<Coaches> {
    Page<Coaches> findAllByCoachId (int id, Pageable pageable);
    List<Coaches> findAllByCoachId (int id);
//    List<Coaches> findAllByCoachIdAndStatusNot (int id, int status);
    Page<Coaches> findAllByStartTimeBetween (LocalDateTime startTime, LocalDateTime endTime, Pageable pageable);
    List<Coaches> findAllByStartTimeBetweenAndStatus (LocalDateTime startTime, LocalDateTime endTime, int status);

    List<Coaches> findAllByStartTimeBetweenAndEndPointAndStartPointAndStatus
            (LocalDateTime startTime, LocalDateTime endTime, int startPoint, int endPoint, int status);
    List<Coaches> findAllByStartTimeBetweenAndEmptySeatIsGreaterThanAndStartPointAndEndPointAndPriceBetweenAndStatus
            (LocalDateTime startTime, LocalDateTime endTime,int emptySeat, int startPoint, int endPoint,int minPrice, int maxPrice ,int status);
    Coaches findTopByCoachIdOrderByIdDesc (int id);
    boolean existsByCoachId (int id);
    boolean existsByIdAndStatusIs(int id, int status);
}
