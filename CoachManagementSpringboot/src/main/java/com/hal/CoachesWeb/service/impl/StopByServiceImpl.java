package com.hal.CoachesWeb.service.impl;

import com.hal.CoachesWeb.entity.StopBy;
import com.hal.CoachesWeb.repositories.StopByRepository;
import com.hal.CoachesWeb.service.StopByService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StopByServiceImpl implements StopByService {
    @Autowired
    private StopByRepository stopByRepository;

    @Override
    public List<StopBy> getAllStopBy(){
        return stopByRepository.findAll();
    }
    @Override
    public List<StopBy> getAllStopByDistrictId(int id){
        return stopByRepository.findAllByDistrictId(id);
    }
    @Override
    public Optional<StopBy> getStopByById(int id){
        return stopByRepository.findById(id);
    }
    @Override
    public boolean addStopBy(StopBy stopBy){
        try {
            stopByRepository.save(new StopBy(stopBy.getName(),stopBy.getAddress()
                    ,stopBy.getDistrictId(),stopBy.getStatus()));
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
        }
        return false;
    }
    @Override
    public boolean updateStopBy(StopBy stopBy){
        try{
            stopByRepository.save(stopBy);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
        }
        return false;
    }
    @Override
    public boolean deleteStopBy(int id){
        try {
            stopByRepository.deleteById(id);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
        }
        return false;
    }
}
