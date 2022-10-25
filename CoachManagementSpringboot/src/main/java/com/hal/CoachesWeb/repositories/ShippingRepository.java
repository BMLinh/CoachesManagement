package com.hal.CoachesWeb.repositories;

import com.hal.CoachesWeb.entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShippingRepository extends JpaRepository<Shipping, Integer> {
    List<Shipping> findAllByUserId (int id);
    boolean existsByUserId (int id);
}
