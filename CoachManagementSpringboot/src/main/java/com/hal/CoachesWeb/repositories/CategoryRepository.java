package com.hal.CoachesWeb.repositories;

import com.hal.CoachesWeb.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
