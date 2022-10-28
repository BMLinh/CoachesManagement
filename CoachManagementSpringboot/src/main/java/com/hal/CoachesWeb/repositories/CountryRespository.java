package com.hal.CoachesWeb.repositories;

import com.hal.CoachesWeb.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRespository extends JpaRepository<Country, Integer> {
    boolean existsByIdAndStatus (int id, int status);
}
