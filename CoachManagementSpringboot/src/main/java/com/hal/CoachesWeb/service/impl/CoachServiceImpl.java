package com.hal.CoachesWeb.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hal.CoachesWeb.entity.Coach;
import com.hal.CoachesWeb.entity.Picture;
import com.hal.CoachesWeb.repositories.*;
import com.hal.CoachesWeb.service.CoachGarageService;
import com.hal.CoachesWeb.service.CoachService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CoachServiceImpl implements CoachService {
    private Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dd3tfkb7f",
            "api_key", "267679115459675",
            "api_secret", "oa4v8FNxe1LCovHI1wVLlhH4t9s"));
    @Autowired
    private CoachRepository coachRepository;
    @Autowired
    private CoachesRepository coachesRepository;
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private CoachGarageRepository coachGarageRepository;

    @Override
    public List<Coach> getAllCoach(){
        return coachRepository.findAll();
    }
    @Override
    public List<Coach> getAllCoachByGarageId(int id){
        return coachRepository.findAllByCoachGarageId(id);
    }
    @Override
    public List<Coach> getAllCoachByUserId(int id){
        List<Integer> coachGarageIds = new ArrayList<>();
        coachGarageRepository.findAllByUserId(id).forEach(coachGarage -> coachGarageIds.add(coachGarage.getId()));
        return coachRepository.findAllByCoachGarageIdIn(coachGarageIds);
    }

    @Override
    public List<Coach> getAllCoachByGarageIdNoDelete(int id) {
        return coachRepository.findAllByCoachGarageIdAndStatusIsNot(id, 0);
    }

    @Override
    public Optional<Coach> getCoachById(int id){
        return coachRepository.findById(id);
    }
    @Override
    @Transactional
    public boolean addCoach(Coach coach){
        try {
            coachRepository.save(new Coach(coach.getLicensePlates(), coach.getDescription()
                    , coach.getCoachGarageId(), coach.getCategoryId(), coach.getStatus()));
            if (coach.getPicture()!=null){
                int id = coachRepository.findTopByCoachGarageIdOrderByIdDesc(coach.getCoachGarageId()).getId();
                coach.getPicture().forEach(picture -> {
                    try {
                        pictureRepository.save(new Picture(cloudinary.uploader().upload(picture.getBytes()
                                , ObjectUtils.emptyMap()).get("secure_url").toString()
                                , id , 1));
                    } catch (Exception ex){
                        System.out.println(ex);
                    }
                });
            }
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
            return false;
        }
    }

//    @Override
//    public boolean updateStatus(Coach coach) {
//        try {
//            coach.setCoachesById(coachesRepository.findAllByCoachId(coach.getId()));
//            coach.getCoachesById().forEach(coaches -> {
//                if (coaches.getStatus()!=0){
//                    coachesService.updateStatus(coaches);
//                }
//            });
//            if (coach.getStatus()!=0){
//                coach.setStatus(0);
//                coachRepository.save(coach);
//            }
//            return true;
//        } catch (HibernateException ex){
//            System.out.println(ex.getMessage());
//            return false;
//        }
//    }

    @Override
    @Transactional
    public boolean updateCoach(Coach coach){
        try {
            coachRepository.save(coach);
            if (!coach.getPicture().isEmpty()){
                pictureRepository.deleteAllByCoachId(coach.getId());
                coach.getPicture().forEach(picture -> {
                    try {
                        pictureRepository.save(new Picture(cloudinary.uploader().upload(picture.getBytes()
                                , ObjectUtils.emptyMap()).get("secure_url").toString()
                                , coach.getId(), 1));
                    } catch (Exception ex){
                        System.out.println(ex);
                    }
                });
            }
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
            return false;
        }
    }
    @Override
    public boolean deleteCoach(int id){
        Coach coach = coachRepository.getById(id);
        if (coachesRepository.existsByCoachId(id) && pictureRepository.existsByCoachId(id)){
            try {
                coachRepository.deleteById(id);
                return true;
            } catch (HibernateException ex){
                System.out.println(ex);
                return false;
            }
        }
        coach.setStatus(0);
        return updateCoach(coach);
    }
    @Override
    public boolean existsById(int id){
        return coachRepository.existsById(id);
    }
}
