package com.hal.CoachesWeb.service.impl;

import com.hal.CoachesWeb.entity.Shipping;
import com.hal.CoachesWeb.repositories.ShippingRepository;
import com.hal.CoachesWeb.service.ShippingService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShippingServiceImpl implements ShippingService {
    @Autowired
    private ShippingRepository shippingRepository;

    @Override
    public List<Shipping> getShippingByCoaches(int id) {
        return shippingRepository.findAllByCoachesId(id);
    }

    @Override
    public Optional<Shipping> getShippingById(int id) {
        return shippingRepository.findById(id);
    }

    @Override
    public boolean addShipping(Shipping shipping) {
        try {
            shippingRepository.save(new Shipping(shipping.getName()
                    , shipping.getSenderName(), shipping.getSenderPhone(), shipping.getSenderEmail()
                    , shipping.getReceiverName(), shipping.getReceiverPhone(), shipping.getReceiverEmail()
                    , shipping.getSendTime(), shipping.getPrice(), shipping.getUserId(), shipping.getCoachesId()
                    , shipping.getStatus()));
            return true;
        } catch (HibernateException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateShipping(Shipping shipping) {
        Shipping old = shippingRepository.getById(shipping.getId());
        shipping.setUserId(old.getUserId());
        shipping.setSendTime(old.getSendTime());
        try {
            shippingRepository.save(shipping);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteShipping(int id) {
        try {
            shippingRepository.deleteById(id);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean existsById(int id) {
        return shippingRepository.existsById(id);
    }
}
