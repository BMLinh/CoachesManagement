package com.hal.CoachesWeb.model.response;

import com.hal.CoachesWeb.entity.Coach;

import java.util.Objects;

public class CoachRes {
    private int id;
    private String licensePlates;
    private String description;
    private int coachGarageId;
    private int categoryId;
    private String categoryName;
    private String coachGarageName;
    private int status;

    public CoachRes() {
    }

    public CoachRes(Coach coach) {
        this.id = coach.getId();
        this.licensePlates = coach.getLicensePlates();
        this.description = coach.getDescription();
        this.coachGarageId = coach.getCoachGarageId();
        this.categoryId = coach.getCategoryId();
        this.categoryName = coach.getCategoryByCategoryId().getName();
        this.coachGarageName = coach.getCoachGarageByCoachGarageId().getName();
        this.status = coach.getStatus();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicensePlates() {
        return licensePlates;
    }

    public void setLicensePlates(String licensePlates) {
        this.licensePlates = licensePlates;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCoachGarageId() {
        return coachGarageId;
    }

    public void setCoachGarageId(int coachGarageId) {
        this.coachGarageId = coachGarageId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCoachGarageName() {
        return coachGarageName;
    }

    public void setCoachGarageName(String coachGarageName) {
        this.coachGarageName = coachGarageName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
