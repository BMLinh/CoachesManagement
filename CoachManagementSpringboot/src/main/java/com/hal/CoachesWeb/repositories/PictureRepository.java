package com.hal.CoachesWeb.repositories;

import com.hal.CoachesWeb.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PictureRepository extends JpaRepository<Picture, Integer> {
    List<Picture> findAllByCoachId(int id);
    Optional<Picture> findFirstByCoachId (int id);
    Integer deleteAllByCoachId(int id);
    boolean existsByCoachId (int id);
}
