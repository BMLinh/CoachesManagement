package com.hal.CoachesWeb.service.impl;

import com.hal.CoachesWeb.entity.Coaches;
import com.hal.CoachesWeb.repositories.CoachesRepository;
import com.hal.CoachesWeb.service.CoachService;
import com.hal.CoachesWeb.service.CoachesService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CoachesServiceImpl implements CoachesService {
    @Autowired
    private CoachesRepository coachesRepository;
    @Autowired
    private CoachService coachService;

    @Override
    public Page<Coaches> getAllCoaches(Pageable pageable){
        return coachesRepository.findAll(pageable);
    }
    @Override
    public Page<Coaches> getAllCoachesByCoachId(int id, Pageable pageable){
        return coachesRepository.findAllByCoachId(id, pageable);
    }
    @Override
    public Page<Coaches> getAllCoachesByStartDate(LocalDateTime startTime, LocalDateTime endTime, Pageable pageable){
        Page<Coaches> coaches= coachesRepository.findAllByStartTimeBetween(startTime, endTime, pageable);
        return coaches;
    }
//    @Override
//    public Optional<Coaches> getCoachesById(int id){
//        return coachesRepository.findById(id);
//    }
    @Override
    public Coaches addCoaches(Coaches coaches){
        try {
            coachesRepository.save(new Coaches(coaches.getStartTime(), coaches.getEndTime()
                    , coaches.getDescription(), coaches.getPrice(), coachService.getSeatByCoachId(coaches.getCoachId()), coaches.isShipping()
                    , coaches.getCoachId(), coaches.getStartPoint(), coaches.getEndPoint(), 1));
            return coachesRepository.findTopByCoachIdOrderByIdDesc(coaches.getCoachId());
        } catch (HibernateException ex){
            System.out.println(ex);
        }
        return null;
    }
    @Override
    public Coaches updateCoaches(Coaches coaches){
        try {
            coachesRepository.save(coaches);
            return coachesRepository.getById(coaches.getId());
        } catch (HibernateException ex){
            System.out.println(ex);
        }
        return null;
    }
    @Override
    public boolean deleteCoaches(int id){
        try {
            coachesRepository.deleteById(id);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
        }
        return false;
    }
    @Override
    public boolean existsById(int id){
        return coachesRepository.existsById(id);
    }
    @Override
    public int getEmptySeatByCoachesId(int id){
        return coachesRepository.getById(id).getEmptySeat();
    }
}
