package com.hal.CoachesWeb.service.impl;

import com.hal.CoachesWeb.entity.Picture;
import com.hal.CoachesWeb.repositories.PictureRepository;
import com.hal.CoachesWeb.service.PictureService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureRepository pictureRepository;

    @Override
    public List<Picture> getAllPicture(){
        return pictureRepository.findAll();
    }

    @Override
    public List<Picture> getPictureByCoachId(int id){
        return pictureRepository.findAllByCoachId(id);
    }

    @Override
    public  boolean addPicture(List<Picture> pictures){
        try {
            pictureRepository.saveAll(pictures);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
        }
        return false;
    }
    public boolean deletePicturesByCoachId(int id){
        try {
            pictureRepository.deleteAllByCoachId(id);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
        }
        return false;
    }
}
