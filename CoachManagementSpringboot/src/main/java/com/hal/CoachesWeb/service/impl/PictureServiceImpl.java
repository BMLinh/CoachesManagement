package com.hal.CoachesWeb.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hal.CoachesWeb.entity.Picture;
import com.hal.CoachesWeb.repositories.PictureRepository;
import com.hal.CoachesWeb.service.PictureService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class PictureServiceImpl implements PictureService {
    private Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dd3tfkb7f",
            "api_key", "267679115459675",
            "api_secret", "oa4v8FNxe1LCovHI1wVLlhH4t9s"));
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
    public boolean addPicture(int coachId, int status, List<MultipartFile> multipartFiles){
        multipartFiles.forEach(multipartFile -> {
            try {
                pictureRepository.save(new Picture(cloudinary.uploader().upload(multipartFile.getBytes()
                        , ObjectUtils.emptyMap()).get("secure_url").toString(), coachId, status));
            } catch (Exception ex){
                System.out.println(ex);
            }
        });
        return true;
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
