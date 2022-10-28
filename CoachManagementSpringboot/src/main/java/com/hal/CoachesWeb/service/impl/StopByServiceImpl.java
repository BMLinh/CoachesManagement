package com.hal.CoachesWeb.service.impl;

import com.hal.CoachesWeb.entity.StopBy;
import com.hal.CoachesWeb.model.response.StopByRes;
import com.hal.CoachesWeb.repositories.CoachesStopByRepository;
import com.hal.CoachesWeb.repositories.DistrictRepository;
import com.hal.CoachesWeb.repositories.StopByRepository;
import com.hal.CoachesWeb.service.CoachesStopByService;
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
    @Autowired
    private CoachesStopByRepository coachesStopByRepository;

    @Override
    public List<StopBy> getAllStopBy(){
        return stopByRepository.findAll();
    }

    @Override
    public List<StopBy> getAllActiveStopBy() {
        return stopByRepository.findAllByStatus(1);
    }

    @Override
    public List<StopBy> getAllStopByCoachesId(int id, int status) {
        List<Integer> stopById = new ArrayList<>();
        coachesStopByRepository.findAllByCoachesIdAndStatusIs(id, status).forEach(coachesStopBy -> {
            stopById.add(coachesStopBy.getStopById());
        });
        return stopByRepository.findAllById(stopById);
    }

    @Override
    public List<StopBy> getAllStopByCoachesId(int id) {
        List<Integer> stopById = new ArrayList<>();
        coachesStopByRepository.findAllByCoachesId(id).forEach(coachesStopBy -> {
            stopById.add(coachesStopBy.getStopById());
        });
        return stopByRepository.findAllById(stopById);
    }

    @Override
    public List<StopBy> getAllStopByCountryId(int id){
        List<StopBy> stopBy = new ArrayList<>();
        districtRepository.findAllByCountryIdAndStatus(id, 1).forEach(district -> {
            stopBy.addAll(stopByRepository.findAllByDistrictIdAndStatus(district.getId(), 1));
        });
        return stopBy;
    }
    @Override
    public List<StopByRes> getAllStopByResCountryId(int id){
        List<StopByRes> stopByRes = new ArrayList<>();
        districtRepository.findAllByCountryIdAndStatus(id, 1).forEach(district -> {
            stopByRes.add(new StopByRes(district.getName(), stopByRepository.findAllByDistrictIdAndStatus(district.getId(), 1)));
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
