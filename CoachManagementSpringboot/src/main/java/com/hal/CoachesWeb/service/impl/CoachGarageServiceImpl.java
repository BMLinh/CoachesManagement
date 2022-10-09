package com.hal.CoachesWeb.service.impl;

import com.hal.CoachesWeb.entity.CoachGarage;
import com.hal.CoachesWeb.repositories.CoachGarageRepository;
import com.hal.CoachesWeb.service.CoachGarageService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoachGarageServiceImpl implements CoachGarageService {
    @Autowired
    private CoachGarageRepository coachGarageRepository;
    @Override
    public List<CoachGarage> getAllCoachGarage(){
        return coachGarageRepository.findAll();
    }
    @Override
    public Optional<CoachGarage> getCoachGarageById(int id){
        return coachGarageRepository.findById(id);
    }
    @Override
    public boolean addCoachGarage(CoachGarage coachGarage){
        CoachGarage newCoachGarage = new CoachGarage
                (coachGarage.getName(), coachGarage.getOwner(), coachGarage.getPhone(), coachGarage.getEmail()
                        , coachGarage.getAddress(), coachGarage.getDistrictId()
                        , coachGarage.getUserId(), coachGarage.getContract(), 2);
        try {
            coachGarageRepository.save(newCoachGarage);
            return true;
        } catch (HibernateException ex) {
            System.out.println(ex);
        }
        return false;
    }
    @Override
    public boolean updateCoachGarage(CoachGarage coachGarage){
        try {
            Optional<CoachGarage> newCoachGarage = coachGarageRepository.findById(coachGarage.getId())
                .map(c -> {
                    c.setAddress(coachGarage.getAddress());
                    c.setName(coachGarage.getName());
                    c.setPhone(coachGarage.getPhone());
                    c.setDistrictId(coachGarage.getDistrictId());
                    return coachGarageRepository.save(c);
                });
            return newCoachGarage.isPresent();
        } catch (HibernateException ex) {
            System.out.println(ex);
        }
        return false;
    }
    @Override
    public boolean deleteCoachGarage (int id){
        try {
            coachGarageRepository.deleteById(id);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
        }
        return false;
    }
}
