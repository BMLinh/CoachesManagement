package com.hal.CoachesWeb.model.response;

import com.hal.CoachesWeb.entity.Picture;
import com.hal.CoachesWeb.entity.StopBy;
import com.hal.CoachesWeb.model.DTO.PictureDTO;
import com.hal.CoachesWeb.model.DTO.StopByDTO;

import java.util.List;

public class CoachesRes {
    private int id;
    private String coach_garage_name;
    private String category_name;
    private int price;
    private int empty_seat;
    private boolean isShip;
    private String image;
    private String start;
    private String end;
    private String License_plate;
    private String description;
    private List<PictureDTO> pictures;
    private List<StopByDTO> stopByDTOS;

    public CoachesRes() {
    }

    public CoachesRes(int id, String coach_garage_name, String category_name, int price, int empty_seat, boolean isShip, String image, String start, String end, String license_plate, String description, List<PictureDTO> pictures, List<StopByDTO> stopByDTOS) {
        this.id = id;
        this.coach_garage_name = coach_garage_name;
        this.category_name = category_name;
        this.price = price;
        this.empty_seat = empty_seat;
        this.isShip = isShip;
        this.image = image;
        this.start = start;
        this.end = end;
        License_plate = license_plate;
        this.description = description;
        this.pictures = pictures;
        this.stopByDTOS = stopByDTOS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoach_garage_name() {
        return coach_garage_name;
    }

    public void setCoach_garage_name(String coach_garage_name) {
        this.coach_garage_name = coach_garage_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getEmpty_seat() {
        return empty_seat;
    }

    public void setEmpty_seat(int empty_seat) {
        this.empty_seat = empty_seat;
    }

    public boolean isShip() {
        return isShip;
    }

    public void setShip(boolean ship) {
        isShip = ship;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getLicense_plate() {
        return License_plate;
    }

    public void setLicense_plate(String license_plate) {
        License_plate = license_plate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PictureDTO> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureDTO> pictures) {
        this.pictures = pictures;
    }

    public List<StopByDTO> getStopByDTOS() {
        return stopByDTOS;
    }

    public void setStopByDTOS(List<StopByDTO> stopByDTOS) {
        this.stopByDTOS = stopByDTOS;
    }
}
