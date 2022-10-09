package com.hal.CoachesWeb.service;

import com.hal.CoachesWeb.entity.Picture;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PictureService {
    List<Picture> getAllPicture();
    List<Picture> getPictureByCoachId(int id);
    boolean addPicture(List<Picture> pictures);
    boolean deletePicturesByCoachId(int id);
//    boolean updatePicturesByCoachId()
}
