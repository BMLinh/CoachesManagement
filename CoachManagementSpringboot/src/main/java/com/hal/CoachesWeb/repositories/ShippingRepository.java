package com.hal.CoachesWeb.repositories;

import com.hal.CoachesWeb.entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShippingRepository extends JpaRepository<Shipping, Integer> {
    List<Shipping> findAllByUserId (int id);
    List<Shipping> findAllByCoachesId (int id);
    List<Shipping> findAllByCoachesIdAndStatus (int id, int status);
    boolean existsByUserId (int id);
}
