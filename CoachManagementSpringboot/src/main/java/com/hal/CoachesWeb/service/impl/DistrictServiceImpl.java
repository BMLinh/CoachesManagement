package com.hal.CoachesWeb.service.impl;

import com.hal.CoachesWeb.entity.District;
import com.hal.CoachesWeb.repositories.DistrictRepository;
import com.hal.CoachesWeb.service.DistrictService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public List<District> getAllDistrict(){
        return districtRepository.findAll();
    }
    @Override
    public List<District> getDistrictByCountryId(int id){
        return districtRepository.findAllByCountryId(id);
    }
    @Override
    public Optional<District> getDistrictById(int id){
        return districtRepository.findById(id);
    }
    @Override
    public boolean addDistrict(District district){
        District newDistrict = new District();
        newDistrict.setCountryId(district.getCountryId());
        newDistrict.setName(district.getName());
        newDistrict.setStatus(district.getStatus());
        try {
            districtRepository.save(newDistrict);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
        }
        return false;
    }
    @Override
    public boolean updateDistrict (District district){
        try {
            districtRepository.save(district);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
        }
        return false;
    }
    @Override
    public boolean deleteDistrict(int id){
        try {
            districtRepository.deleteById(id);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
        }
        return false;
    }
}
