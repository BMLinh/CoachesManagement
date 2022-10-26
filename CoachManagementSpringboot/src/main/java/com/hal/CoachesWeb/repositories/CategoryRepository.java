package com.hal.CoachesWeb.repositories;

import com.hal.CoachesWeb.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAllByStatusIs (int status);
}
