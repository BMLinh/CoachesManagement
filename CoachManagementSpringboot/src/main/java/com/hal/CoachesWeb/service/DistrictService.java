package com.hal.CoachesWeb.service;

import com.hal.CoachesWeb.entity.District;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DistrictService {
    List<District> getAllDistrict();
    List<District> getDistrictByCountryId(int id);
    Optional<District> getDistrictById(int id);
    boolean addDistrict(District district);
    boolean updateDistrict (District district);
    boolean deleteDistrict(int id);
}
