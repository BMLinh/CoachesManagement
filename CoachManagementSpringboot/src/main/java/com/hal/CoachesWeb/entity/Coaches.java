package com.hal.CoachesWeb.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Coaches {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "startTime")
    private Timestamp startTime;
    @Basic
    @Column(name = "endTime")
    private Timestamp endTime;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "price")
    private Integer price;
    @Basic
    @Column(name = "emptySeat")
    private Integer emptySeat;
    @Basic
    @Column(name = "isShipping")
    private Boolean isShipping;
    @Basic
    @Column(name = "driver_id")
    private Integer driverId;
    @Basic
    @Column(name = "coach_id")
    private String coachId;
    @Basic
    @Column(name = "startPoint")
    private Integer startPoint;
    @Basic
    @Column(name = "endPoint")
    private Integer endPoint;
    @Basic
    @Column(name = "status")
    private Integer status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getEmptySeat() {
        return emptySeat;
    }

    public void setEmptySeat(Integer emptySeat) {
        this.emptySeat = emptySeat;
    }

    public Boolean getShipping() {
        return isShipping;
    }

    public void setShipping(Boolean shipping) {
        isShipping = shipping;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public Integer getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Integer startPoint) {
        this.startPoint = startPoint;
    }

    public Integer getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Integer endPoint) {
        this.endPoint = endPoint;
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
        Coaches coaches = (Coaches) o;
        return id == coaches.id && Objects.equals(startTime, coaches.startTime) && Objects.equals(endTime, coaches.endTime) && Objects.equals(description, coaches.description) && Objects.equals(price, coaches.price) && Objects.equals(emptySeat, coaches.emptySeat) && Objects.equals(isShipping, coaches.isShipping) && Objects.equals(driverId, coaches.driverId) && Objects.equals(coachId, coaches.coachId) && Objects.equals(startPoint, coaches.startPoint) && Objects.equals(endPoint, coaches.endPoint) && Objects.equals(status, coaches.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, endTime, description, price, emptySeat, isShipping, driverId, coachId, startPoint, endPoint, status);
    }
}
