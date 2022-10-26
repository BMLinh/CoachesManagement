package com.hal.CoachesWeb.service;

import com.hal.CoachesWeb.entity.Picture;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface PictureService {
    List<Picture> getAllPicture();
    List<Picture> getPictureByCoachId(int id);
    boolean addPicture(int coachId, int status, List<MultipartFile> multipartFiles);
    boolean deletePicturesByCoachId(int id);
//    boolean updatePicturesByCoachId()
}
