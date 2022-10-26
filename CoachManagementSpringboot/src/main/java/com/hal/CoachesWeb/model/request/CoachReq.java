package com.hal.CoachesWeb.model.request;

import com.hal.CoachesWeb.entity.Coach;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class CoachReq {
    private Coach coach;
    private List<MultipartFile> pictures;

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public List<MultipartFile> getPictures() {
        return pictures;
    }

    public void setPictures(List<MultipartFile> pictures) {
        this.pictures = pictures;
    }
}
