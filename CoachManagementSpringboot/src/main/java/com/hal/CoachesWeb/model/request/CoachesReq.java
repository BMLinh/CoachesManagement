package com.hal.CoachesWeb.model.request;

import com.hal.CoachesWeb.entity.CoachesStopBy;
import com.hal.CoachesWeb.model.response.StopByDetailRes;

import java.util.List;

public class CoachesReq {
    private int id;
    private String startTime;
    private String endTime;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
