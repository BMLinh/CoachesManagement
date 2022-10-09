package com.hal.CoachesWeb.repositories;

import com.hal.CoachesWeb.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Integer> {
    List<Picture> findAllByCoachId(int id);
    Void deleteAllByCoachId(int id);
}
