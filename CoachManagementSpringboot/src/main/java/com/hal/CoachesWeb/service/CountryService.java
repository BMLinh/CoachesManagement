package com.hal.CoachesWeb.service;

import com.hal.CoachesWeb.entity.Country;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CountryService {
    List<Country> getAllCountry();
    Optional<Country> getCountryById(int id);
    boolean addCountry(Country country);
    boolean updateCountry(Country country);
    boolean deleteCountry(int id);
    boolean existsById(int id);
    boolean isActive(int id);
}
