package com.hal.CoachesWeb.model.response;

import java.time.LocalDateTime;
import java.util.List;

public class CoachesRes {
    private int id;
    private String name;
    private String category;
    private String phone;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
    private int price;
    private int emptySeat;
    private boolean isShipping;
    private int coachId;
    private int startPoint;
    private int endPoint;
    private int status;
    private List<StopByDetailRes> pickUp;
    private List<StopByDetailRes> dropOff;
    private List<String> pictures;

    public CoachesRes(int id, String name, String category, String phone, LocalDateTime startTime
            , LocalDateTime endTime, String description, int price, int emptySeat, boolean isShipping, int coachId
            , int startPoint, int endPoint, int status) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.phone = phone;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.price = price;
        this.emptySeat = emptySeat;
        this.isShipping = isShipping;
        this.coachId = coachId;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getEmptySeat() {
        return emptySeat;
    }

    public void setEmptySeat(int emptySeat) {
        this.emptySeat = emptySeat;
    }

    public boolean isShipping() {
        return isShipping;
    }

    public void setShipping(boolean shipping) {
        isShipping = shipping;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<StopByDetailRes> getPickUp() {
        return pickUp;
    }

    public void setPickUp(List<StopByDetailRes> pickUp) {
        this.pickUp = pickUp;
    }

    public List<StopByDetailRes> getDropOff() {
        return dropOff;
    }

    public void setDropOff(List<StopByDetailRes> dropOff) {
        this.dropOff = dropOff;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }
}
