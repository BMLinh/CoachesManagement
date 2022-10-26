package com.hal.CoachesWeb.service;

import com.hal.CoachesWeb.entity.Shipping;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ShippingService {
    List<Shipping> getShippingByCoaches(int id);
    Optional<Shipping> getShippingById(int id);
    boolean addShipping(Shipping shipping);
    boolean updateShipping(Shipping shipping);
    boolean deleteShipping(int id);
    boolean existsById(int id);
}
