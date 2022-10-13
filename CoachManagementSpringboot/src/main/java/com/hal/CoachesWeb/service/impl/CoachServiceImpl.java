package com.hal.CoachesWeb.service.impl;

import com.hal.CoachesWeb.entity.Coach;
import com.hal.CoachesWeb.repositories.CategoryRepository;
import com.hal.CoachesWeb.repositories.CoachRepository;
import com.hal.CoachesWeb.service.CoachService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoachServiceImpl implements CoachService {
    @Autowired
    private CoachRepository coachRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Coach> getAllCoach(){
        return coachRepository.findAll();
    }
    @Override
    public List<Coach> getAllCoachByGarageId(int id){
        return coachRepository.findAllByCoachGarageId(id);
    }
    @Override
    public Optional<Coach> getCoachById(int id){
        return coachRepository.findById(id);
    }
    @Override
    public Coach addCoach(Coach coach){
        try {
            coachRepository.save(new Coach(coach.getLicensePlates(), coach.getDescription()
                    , coach.getCoachGarageId(), coach.getCategoryId(), coach.getStatus()));
            return coachRepository.findTopByCoachGarageIdOrderByIdDesc(coach.getCoachGarageId());
        } catch (HibernateException ex){
            System.out.println(ex);
        }
        return null;
    }
    @Override
    public Coach updateCoach(Coach coach){
        try {
            coachRepository.save(coach);
            return coach;
        } catch (HibernateException ex){
            System.out.println(ex);
        }
        return null;
    }
    @Override
    public boolean deleteCoach(int id){
        try {
            coachRepository.deleteById(id);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
        }
        return false;
    }
    @Override
    public boolean existsById(int id){
        return coachRepository.existsById(id);
    }
    @Override
    public int getSeatByCoachId(int id){
        return categoryRepository.getById(coachRepository.getById(id).getCategoryId()).getSeat();
    }
}
