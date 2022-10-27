package com.hal.CoachesWeb.model.request;

import java.time.LocalDateTime;

public class GetCoachesReq {
    private String startDate;
    private String startTime;
    private String endTime;
    private int startPoint;
    private int endPoint;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer pickUp;
    private Integer dropOff;
    private Integer emptySeat;
    private Integer coachGarage;
    private int status;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(int startPoint) {
        this.startPoint = startPoint;
    }

    public int getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(int endPoint) {
        this.endPoint = endPoint;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getPickUp() {
        return pickUp;
    }

    public void setPickUp(Integer pickUp) {
        this.pickUp = pickUp;
    }

    public Integer getDropOff() {
        return dropOff;
    }

    public void setDropOff(Integer dropOff) {
        this.dropOff = dropOff;
    }

    public Integer getEmptySeat() {
        return emptySeat;
    }

    public void setEmptySeat(Integer emptySeat) {
        this.emptySeat = emptySeat;
    }

    public Integer getCoachGarage() {
        return coachGarage;
    }

    public void setCoachGarage(Integer coachGarage) {
        this.coachGarage = coachGarage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
