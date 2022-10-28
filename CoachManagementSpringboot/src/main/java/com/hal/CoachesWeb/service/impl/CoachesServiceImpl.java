package com.hal.CoachesWeb.service.impl;

import com.hal.CoachesWeb.entity.CoachGarage;
import com.hal.CoachesWeb.entity.Coaches;
import com.hal.CoachesWeb.entity.Picture;
import com.hal.CoachesWeb.model.request.CoachesReq;
import com.hal.CoachesWeb.model.response.CoachesDetailRes;
import com.hal.CoachesWeb.model.response.CoachesGetRes;
import com.hal.CoachesWeb.model.response.PictureRes;
import com.hal.CoachesWeb.model.response.StopByDetailRes;
import com.hal.CoachesWeb.repositories.*;
import com.hal.CoachesWeb.service.CoachesService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Autowired
    private PictureRepository pictureRepository;

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
        return coachesRepository.findAllByCoachIdAndStatus(id, 1);
    }

    @Override
    public Page<Coaches> getAllCoachesByStartDate(LocalDateTime startTime, LocalDateTime endTime, Pageable pageable){
        return coachesRepository.findAllByStartTimeBetween(startTime, endTime, pageable);

    }

    @Override
    public List<CoachesGetRes> getAllCoachesByStartDate(String startTime, String endTime, LocalDate startDate
            , int startPoint, int endPoint, Integer minPrice, Integer maxPrice, Integer pickUp, Integer dropOff
            , Integer emptySeat, Integer coachGarage, int status) {
        List<CoachesGetRes> coachesGetRes = new ArrayList<>();
        if (startTime==null || endTime==null){
            LocalDateTime start = LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), 0, 0);
            LocalDateTime end = start.plusDays(1).minusSeconds(1);
            coachesRepository.findAllByStartTimeBetweenAndStartPointAndEndPointAndStatus
                    (start, end, startPoint, endPoint, status).forEach(coaches -> {
                coachesGetRes.add(convertFromCoaches(coaches));
            });
            return coachesGetRes;
        }
        List<Coaches> coaches;
        if (emptySeat!=null){
            coaches = coachesRepository.findAllByStartTimeBetweenAndEmptySeatIsGreaterThanAndStartPointAndEndPointAndPriceBetweenAndStatus(
                    LocalDateTime.parse(startTime), LocalDateTime.parse(endTime), emptySeat, startPoint, endPoint, minPrice, maxPrice, 1);
        } else {
            coaches = coachesRepository.findAllByStartTimeBetweenAndEmptySeatIsGreaterThanAndStartPointAndEndPointAndPriceBetweenAndStatus(
                    LocalDateTime.parse(startTime), LocalDateTime.parse(endTime), 0, startPoint, endPoint, minPrice, maxPrice, 1);
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (coachGarage!=null){
            coachRepository.findAllByCoachGarageIdAndStatus(coachGarage, 1).forEach(coach -> arrayList.add(coach.getId()));
            coaches.removeIf(c -> (!arrayList.contains(c.getCoachId())));
            arrayList.clear();
        }
        if (pickUp!=null){
            ArrayList<Integer> coachesId = new ArrayList<>();
            coaches.forEach(c -> {
                coachesId.add(c.getId());
            });
            coachesStopByRepository.findAllByCoachesIdInAndStatus(coachesId, 3).forEach(coachesStopBy -> {
                if (coachesStopBy.getStopById()==pickUp){
                    arrayList.add(coachesStopBy.getCoachesId());
                }
            });
            coaches.removeIf(c->(!arrayList.contains(c.getId())));
            arrayList.clear();
        }
        if (dropOff!=null){
            ArrayList<Integer> coachesId = new ArrayList<>();
            coaches.forEach(c -> {
                coachesId.add(c.getId());
            });
            coachesStopByRepository.findAllByCoachesIdInAndStatus(coachesId, 4).forEach(coachesStopBy -> {
                if (coachesStopBy.getStopById()==dropOff){
                    arrayList.add(coachesStopBy.getCoachesId());
                }
            });
            coaches.removeIf(c->(!arrayList.contains(c.getId())));
        }
        coaches.forEach(c ->{
            coachesGetRes.add(convertFromCoaches(c));
        });
        return coachesGetRes;

    }
    @Override
    public Optional<Coaches> getCoachesById(int id){
        return coachesRepository.findById(id);
    }

    @Override
    public CoachesDetailRes getCoachesDetailById(int id){
        Optional<Coaches> c = coachesRepository.findById(id);
        if (!c.isPresent()){
            return null;
        }
        Coaches coaches = c.get();
        CoachesDetailRes coachesDetailRes = new CoachesDetailRes(coaches.getId()
                , coaches.getCoachByCoachId().getCoachGarageByCoachGarageId().getName()
                , coaches.getCoachByCoachId().getCategoryByCategoryId().getName()
                , coaches.getCoachByCoachId().getCoachGarageByCoachGarageId().getPhone()
                , coaches.getStartTime(), coaches.getEndTime(), coaches.getDescription()
                , coaches.getPrice(), coaches.getEmptySeat(), coaches.isShipping(), coaches.getCoachId()
                , coaches.getStartPoint(), coaches.getEndPoint(), coaches.getStatus()
                , new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        coachesStopByRepository.findAllByCoachesIdAndStatusIs(coaches.getId(), 3).forEach(coachesStopBy -> {
            coachesDetailRes.getPickUp().add(new StopByDetailRes(coachesStopBy.getStopById()
                    , coachesStopBy.getStopByByStopById().getName(), coachesStopBy.getTime()));
        });
        coachesStopByRepository.findAllByCoachesIdAndStatusIs(coaches.getId(), 4).forEach(coachesStopBy -> {
            coachesDetailRes.getDropOff().add(new StopByDetailRes(coachesStopBy.getStopById()
                    , coachesStopBy.getStopByByStopById().getName(), coachesStopBy.getTime()));
        });
        List<PictureRes> pictures = new ArrayList<>();
        pictureRepository.findAllByCoachId(coaches.getCoachId()).forEach(picture -> {
            coachesDetailRes.getPictures().add(new PictureRes(picture.getId(), picture.getUrl()));
        });
        return coachesDetailRes;
    }

    @Override
    public List<Coaches> getAllCoachesInDay(LocalDate startTime) {
        LocalDateTime start = LocalDateTime.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth(), 0, 0);
        LocalDateTime end = start.plusDays(1).minusSeconds(1);
        return coachesRepository.findAllByStartTimeBetweenAndStatus(start, end, 1);
    }

    @Override
    @Transactional
    public boolean addCoaches(CoachesReq coachesReq){
        Coaches newCoaches = new Coaches(LocalDateTime.parse(coachesReq.getStartTime())
                , LocalDateTime.parse(coachesReq.getEndTime())
                , coachesReq.getDescription(), coachesReq.getPrice()
                , coachRepository.getById(coachesReq.getCoachId()).getCategoryByCategoryId().getSeat()
                , coachesReq.isShipping(), coachesReq.getCoachId(), coachesReq.getStartPoint()
                , coachesReq.getEndPoint(), coachesReq.getStatus());
        try {
            coachesRepository.save(newCoaches);
        } catch (HibernateException ex){
            System.out.println(ex.getMessage());
            return false;
        }
        int id = coachesRepository.findTopByCoachIdOrderByIdDesc(coachesReq.getCoachId()).getId();
        coachesReq.getPickUp().forEach(pickUp -> {
            pickUp.setCoachesId(id);
            pickUp.setStatus(3);
        });
        coachesReq.getDropOff().forEach(dropOff -> {
            dropOff.setCoachesId(id);
            dropOff.setStatus(4);
        });
        try {
            coachesStopByRepository.saveAll(coachesReq.getPickUp());
            coachesStopByRepository.saveAll(coachesReq.getDropOff());
        } catch (HibernateException ex){
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
    @Override
    @Transactional
    public boolean updateCoaches(CoachesReq coachesReq){
        Coaches coaches = coachesRepository.getById(coachesReq.getId());
        coaches.setStatus(coachesReq.getStatus());
        coaches.setPrice(coachesReq.getPrice());
        coaches.setCoachId(coachesReq.getCoachId());
        coaches.setDescription(coachesReq.getDescription());
        coaches.setEndTime(LocalDateTime.parse(coachesReq.getEndTime()));
        coaches.setStartTime(LocalDateTime.parse(coachesReq.getStartTime()));
        coaches.setShipping(coachesReq.isShipping());
        coaches.setStartPoint(coaches.getStartPoint());
        coaches.setEndPoint(coaches.getEndPoint());
        coachesReq.getPickUp().forEach(pickUp -> {
            pickUp.setCoachesId(coachesReq.getId());
            pickUp.setStatus(3);
        });
        coachesReq.getDropOff().forEach(dropOff -> {
            dropOff.setCoachesId(coachesReq.getId());
            dropOff.setStatus(4);
        });
        try {
            coachesRepository.save(coaches);
            coachesStopByRepository.deleteAll(coachesStopByRepository.findAllByCoachesId(coachesReq.getId()));
            coachesStopByRepository.saveAll(coachesReq.getPickUp());
            coachesStopByRepository.saveAll(coachesReq.getDropOff());
            return true;
        } catch (HibernateException ex){
            System.out.println(ex.getMessage());
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
                System.out.println(ex.getMessage());
                return false;
            }
        }
        coaches.setStatus(0);
        try {
            coachesRepository.save(coaches);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex.getMessage());
            return false;
        }

    }
    @Override
    public boolean existsById(int id){
        return coachesRepository.existsById(id);
    }

    @Override
    public boolean isActive(int id) {
        return coachesRepository.existsByIdAndStatusIs(id, 1);
    }

    @Override
    public int getEmptySeatByCoachesId(int id){
        return coachesRepository.getById(id).getEmptySeat();
    }

    public CoachesGetRes convertFromCoaches(Coaches coaches){
        CoachGarage c = coaches.getCoachByCoachId().getCoachGarageByCoachGarageId();
        Optional<Picture> picture = pictureRepository.findFirstByCoachId(coaches.getCoachId());
        if (picture.isPresent()){
            return new CoachesGetRes(coaches.getId(), c.getName()
                    , coaches.getCoachByCoachId().getCategoryByCategoryId().getName(), c.getPhone()
                    , coaches.getStartTime(), coaches.getEndTime(), coaches.getDescription(), coaches.getPrice()
                    , coaches.getEmptySeat(), coaches.isShipping(), coaches.getCoachId()
                    , coaches.getStartPoint(), coaches.getEndPoint(), coaches.getStatus(), picture.get().getUrl());
        }
        return new CoachesGetRes(coaches.getId(), c.getName()
                , coaches.getCoachByCoachId().getCategoryByCategoryId().getName(), c.getPhone()
                , coaches.getStartTime(), coaches.getEndTime(), coaches.getDescription(), coaches.getPrice()
                , coaches.getEmptySeat(), coaches.isShipping(), coaches.getCoachId()
                , coaches.getStartPoint(), coaches.getEndPoint(), coaches.getStatus(), null);
    }
}
