package com.hal.CoachesWeb.service.impl;

import com.hal.CoachesWeb.entity.StopBy;
import com.hal.CoachesWeb.model.response.StopByRes;
import com.hal.CoachesWeb.repositories.DistrictRepository;
import com.hal.CoachesWeb.repositories.StopByRepository;
import com.hal.CoachesWeb.service.StopByService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StopByServiceImpl implements StopByService {
    @Autowired
    private StopByRepository stopByRepository;
    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public List<StopBy> getAllStopBy(){
        return stopByRepository.findAll();
    }
    @Override
    public List<StopBy> getAllStopByDistrictId(int id){
        return stopByRepository.findAllByDistrictId(id);
    }
    @Override
    public List<StopByRes> getAllStopByCountryId(int id){
        List<StopByRes> stopByRes = new ArrayList<>();
        districtRepository.findAllByCountryId(id).forEach(district -> {
            stopByRes.add(new StopByRes(district.getName(), stopByRepository.findAllByDistrictId(district.getId())));
        });
        return stopByRes;
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
    @Override
    public boolean existsById(int id){
        return stopByRepository.existsById(id);
    }
}
