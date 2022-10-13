package com.hal.CoachesWeb.service.impl;

import com.hal.CoachesWeb.entity.Country;
import com.hal.CoachesWeb.repositories.CountryRespository;
import com.hal.CoachesWeb.service.CountryService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRespository countryRespository;

    @Override
    public List<Country> getAllCountry(){
        return countryRespository.findAll();
    }
    @Override
    public Optional<Country> getCountryById(int id){
        return countryRespository.findById(id);
    }
    @Override
    public boolean addCountry(Country country){
        try {
            countryRespository.save(new Country(country.getName(), country.getStatus()));
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
        }
        return false;
    }
    @Override
    public boolean updateCountry(Country country){
        try {
            countryRespository.save(country);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
        }
        return false;
    }
    @Override
    public boolean deleteCountry(int id){
        try {
            countryRespository.deleteById(id);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
        }
        return false;
    }
    @Override
    public boolean existsById(int id){
        return countryRespository.existsById(id);
    }
}
