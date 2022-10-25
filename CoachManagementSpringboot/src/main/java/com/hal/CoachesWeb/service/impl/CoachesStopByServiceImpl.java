package com.hal.CoachesWeb.service.impl;

import com.hal.CoachesWeb.entity.Coaches;
import com.hal.CoachesWeb.entity.CoachesStopBy;
import com.hal.CoachesWeb.entity.CoachesStopByPK;
import com.hal.CoachesWeb.repositories.CoachesStopByRepository;
import com.hal.CoachesWeb.service.CoachesStopByService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachesStopByServiceImpl implements CoachesStopByService {
    @Autowired
    private CoachesStopByRepository coachesStopByRepository;

    @Override
    public List<CoachesStopBy> getAllByCoachesId(int id){
        return coachesStopByRepository.findAllByCoachesId(id);
    }
    @Override
    public boolean upsertCoachesStopBy(List<CoachesStopBy> coachesStopByList){
        try {
            coachesStopByRepository.saveAll(coachesStopByList);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    @Override
    public boolean deleteCoachesStopBy(List<CoachesStopBy> coachesStopByList){
        try {
            coachesStopByList.forEach(coachesStopBy -> {
                coachesStopByRepository.delete(coachesStopBy);
            });
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
        }
        return false;
    }

    @Override
    public boolean existsByCoachesAndStopBy(int coachesId, int stopById) {
        return coachesStopByRepository.existsByCoachesIdAndStopById(coachesId, stopById);
    }
}
