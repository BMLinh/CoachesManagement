package com.hal.CoachesWeb.service.impl;

import com.hal.CoachesWeb.entity.Coach;
import com.hal.CoachesWeb.repositories.*;
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
    private CoachesServiceImpl coachesService;
    @Autowired
    private CoachesRepository coachesRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PictureRepository pictureRepository;

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
            return null;
        }
    }

//    @Override
//    public boolean updateStatus(Coach coach) {
//        try {
//            coach.setCoachesById(coachesRepository.findAllByCoachId(coach.getId()));
//            coach.getCoachesById().forEach(coaches -> {
//                if (coaches.getStatus()!=0){
//                    coachesService.updateStatus(coaches);
//                }
//            });
//            if (coach.getStatus()!=0){
//                coach.setStatus(0);
//                coachRepository.save(coach);
//            }
//            return true;
//        } catch (HibernateException ex){
//            System.out.println(ex.getMessage());
//            return false;
//        }
//    }

    @Override
    public boolean updateCoach(Coach coach){
        try {
            coachRepository.save(coach);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
            return false;
        }
    }
    @Override
    public boolean deleteCoach(int id){
        Coach coach = coachRepository.getById(id);
        if (coachesRepository.existsByCoachId(id) && pictureRepository.existsByCoachId(id)){
            try {
                coachRepository.deleteById(id);
                return true;
            } catch (HibernateException ex){
                System.out.println(ex);
                return false;
            }
        }
        coach.setStatus(0);
        return updateCoach(coach);
    }
    @Override
    public boolean existsById(int id){
        return coachRepository.existsById(id);
    }
}
