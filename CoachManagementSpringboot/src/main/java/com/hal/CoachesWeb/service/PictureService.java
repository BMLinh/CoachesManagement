package com.hal.CoachesWeb.service;

import com.hal.CoachesWeb.entity.Picture;
import com.hal.CoachesWeb.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureService {
    @Autowired
    private PictureRepository pictureRepository;

    public List<Picture> getAllPicture(){
        return pictureRepository.findAll();
    }

    public List<Picture> getPictureByCoachesId(int id){
        return pictureRepository.findAllByCoachId(id);
    }
}
