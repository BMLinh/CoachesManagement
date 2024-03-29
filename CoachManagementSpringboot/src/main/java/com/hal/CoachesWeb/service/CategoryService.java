package com.hal.CoachesWeb.service;

import com.hal.CoachesWeb.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {
    List<Category> getAllCategory();
    List<Category> getAllActiveCategory();
    Optional<Category> getCategoryById(int id);
    boolean addCategory(Category category);
    boolean updateCategory(Category category);
    boolean deleteCategory(int id);
    boolean existsById(int id);
    boolean isActive(int id);
}
