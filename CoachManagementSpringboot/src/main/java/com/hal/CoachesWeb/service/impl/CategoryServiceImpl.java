package com.hal.CoachesWeb.service.impl;

import com.hal.CoachesWeb.entity.Category;
import com.hal.CoachesWeb.repositories.CategoryRepository;
import com.hal.CoachesWeb.service.CategoryService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getAllActiveCategory() {
        return categoryRepository.findAllByStatusIs(1);
    }

    @Override
    public Optional<Category> getCategoryById(int id){
        return categoryRepository.findById(id);
    }
    @Override
    public boolean addCategory(Category category){
        try {
            categoryRepository.save(new Category(category.getName(), category.getStatus(), category.getSeat()));
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
            return false;
        }

    }
    @Override
    public boolean updateCategory(Category category){
        try {
            categoryRepository.save(category);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
            return false;
        }
    }
    @Override
    public boolean deleteCategory(int id){
        Category category = categoryRepository.getById(id);
        if (category.getCoachesById().isEmpty()){
            try {
                categoryRepository.deleteById(id);
                return true;
            } catch (HibernateException ex){
                System.out.println(ex);
                return false;
            }
        }
        category.setStatus(0);
        return updateCategory(category);
    }
    @Override
    public boolean existsById(int id){
        return categoryRepository.existsById(id);
    }
    @Override
    public boolean isActive(int id){
        return categoryRepository.existsByIdAndStatus(id, 1);
    }
}
