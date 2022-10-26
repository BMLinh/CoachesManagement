package com.hal.CoachesWeb.service.impl;

import com.hal.CoachesWeb.entity.Coaches;
import com.hal.CoachesWeb.repositories.*;
import com.hal.CoachesWeb.service.CoachService;
import com.hal.CoachesWeb.service.CoachesService;
import com.hal.CoachesWeb.service.CoachesStopByService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CoachesServiceImpl implements CoachesService {
    @Autowired
    private CoachesRepository coachesRepository;
    @Autowired
    private CoachRepository coachRepository;
    @Autowired
    private CoachesStopByRepository coachesStopByRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ShippingRepository shippingRepository;

    @Override
    public Page<Coaches> getAllCoaches(Pageable pageable){
        return coachesRepository.findAll(pageable);
    }

    @Override
    public Page<Coaches> getAllCoachesByCoachId(int id, Pageable pageable){
        return coachesRepository.findAllByCoachId(id, pageable);
    }

    @Override
    public List<Coaches> getAllCoaches() {
        return coachesRepository.findAll();
    }

    @Override
    public List<Coaches> getAllCoachesByCoachId(int id) {
//        List<Coaches> coaches = new ArrayList<>();
//        coachRepository.findAllByCoachGarageIdAndStatusIsNot(id, 0).forEach(coach -> {
//            if (coach.getStatus()!=0){
//                coaches.addAll(coachesRepository.findAllByCoachIdAndStatusNot(id, 0));
//            }
//        });
//        return coaches;
        return coachesRepository.findAllByCoachId(id);
    }

    @Override
    public Page<Coaches> getAllCoachesByStartDate(LocalDateTime startTime, LocalDateTime endTime, Pageable pageable){
        Page<Coaches> coaches= coachesRepository.findAllByStartTimeBetween(startTime, endTime, pageable);
        return coaches;
    }
    @Override
    public Optional<Coaches> getCoachesById(int id){
        return coachesRepository.findById(id);
    }

    @Override
    public List<Coaches> getAllCoachesInDay(LocalDateTime startTime) {
        LocalDateTime start = LocalDateTime.of(startTime.getYear(), startTime.getDayOfMonth(), startTime.getDayOfMonth(), 0, 0);
        LocalDateTime end = start.plusDays(1).minusSeconds(1);
        System.out.println(start);
        System.out.println(end);
        return coachesRepository.findAllByStartTimeBetween(start, end);
    }

    @Override
    public boolean addCoaches(Coaches coaches){
        Coaches newCoaches = new Coaches(coaches.getStartTime(), coaches.getEndTime()
                , coaches.getDescription(), coaches.getPrice()
                , coachRepository.getById(coaches.getCoachId()).getCategoryByCategoryId().getSeat(), coaches.isShipping()
                , coaches.getCoachId(), coaches.getStartPoint(), coaches.getEndPoint(), coaches.getStatus());
        try {
            coachesRepository.save(newCoaches);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
            return false;
        }
    }
    @Override
    public boolean updateCoaches(Coaches newCoaches){
        Coaches coaches = coachesRepository.getById(newCoaches.getId());
        newCoaches.setCreateDate(coaches.getCreateDate());
        newCoaches.setCoachByCoachId(coaches.getCoachByCoachId());
        newCoaches.setCountryByEndPoint(coaches.getCountryByEndPoint());
        newCoaches.setCountryByStartPoint(coaches.getCountryByStartPoint());
        try {
            coachesRepository.save(newCoaches);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
            return false;
        }
    }

//    @Override
//    public boolean updateStatus(Coaches coaches) {
//        try {
//            coaches.getTicketsById().stream().forEach(ticket -> {
//                if (ticket.getStatus()!=0){
//                    ticket.setStatus(0);
//                    ticketRepository.save(ticket);
//                }
//            });
//            coaches.getShippingsById().stream().forEach(shipping -> {
//                if (shipping.getStatus()!=0){
//                    shipping.setStatus(0);
//                    shippingRepository.save(shipping);
//                }
//            });
//            coachesStopByRepository.findAllByCoachesId(coaches.getId()).forEach(coachesStopBy -> {
//                if (coachesStopBy.getStatus()!=0){
//                    coachesStopBy.setStatus(0);
//                    coachesStopByRepository.save(coachesStopBy);
//                }
//            });
//            if (coaches.getStatus()!=0){
//                coaches.setStatus(0);
//                coachesRepository.save(coaches);
//            }
//            return true;
//        } catch (HibernateException ex){
//            System.out.println(ex.getMessage());
//            return false;
//        }
//    }
    @Override
    public boolean deleteCoaches(int id){
        Coaches coaches = coachesRepository.getById(id);
        if (coachesStopByRepository.existsByCoachesId(id)
                && shippingRepository.existsByUserId(id)
                && ticketRepository.existsByCoachesId(id)){
            try {
                coachesRepository.deleteById(id);
                return true;
            } catch (HibernateException ex) {
                System.out.println(ex);
                return false;
            }
        }
        coaches.setStatus(0);
        return updateCoaches(coaches);

    }
    @Override
    public boolean existsById(int id){
        return coachesRepository.existsById(id);
    }
    @Override
    public int getEmptySeatByCoachesId(int id){
        return coachesRepository.getById(id).getEmptySeat();
    }
}
