package com.hal.CoachesWeb.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Coach {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "License_plates")
    private String licensePlates;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "seat")
    private Integer seat;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "coachGarage_id")
    private Integer coachGarageId;
    @Basic
    @Column(name = "category_id")
    private Integer categoryId;
    @Basic
    @Column(name = "status")
    private Integer status;

    public String getLicensePlates() {
        return licensePlates;
    }

    public void setLicensePlates(String licensePlates) {
        this.licensePlates = licensePlates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCoachGarageId() {
        return coachGarageId;
    }

    public void setCoachGarageId(Integer coachGarageId) {
        this.coachGarageId = coachGarageId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coach coach = (Coach) o;
        return Objects.equals(licensePlates, coach.licensePlates) && Objects.equals(name, coach.name) && Objects.equals(phone, coach.phone) && Objects.equals(seat, coach.seat) && Objects.equals(description, coach.description) && Objects.equals(coachGarageId, coach.coachGarageId) && Objects.equals(categoryId, coach.categoryId) && Objects.equals(status, coach.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licensePlates, name, phone, seat, description, coachGarageId, categoryId, status);
    }
}
