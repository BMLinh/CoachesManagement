package com.hal.CoachesWeb.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Coach {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "license_plates")
    private String licensePlates;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "coach_garage_id")
    private int coachGarageId;
    @Basic
    @Column(name = "category_id")
    private int categoryId;
    @Basic
    @Column(name = "status")
    private int status;

    public Coach() {
    }

    public Coach(String licensePlates, String description, int coachGarageId, int categoryId, int status) {
        this.licensePlates = licensePlates;
        this.description = description;
        this.coachGarageId = coachGarageId;
        this.categoryId = categoryId;
        this.status = status;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coach coach = (Coach) o;
        return id == coach.id && coachGarageId == coach.coachGarageId && categoryId == coach.categoryId && status == coach.status && Objects.equals(licensePlates, coach.licensePlates) && Objects.equals(description, coach.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, licensePlates, description, coachGarageId, categoryId, status);
    }
}
