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
    private int price;
    @Basic
    @Column(name = "emptySeat")
    private int emptySeat;
    @Basic
    @Column(name = "isShipping")
    private boolean isShipping;
    @Basic
    @Column(name = "coach_id")
    private int coachId;
    @Basic
    @Column(name = "startPoint")
    private int startPoint;
    @Basic
    @Column(name = "endPoint")
    private int endPoint;
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
        return id == coaches.id && price == coaches.price && emptySeat == coaches.emptySeat && isShipping == coaches.isShipping && coachId == coaches.coachId && startPoint == coaches.startPoint && endPoint == coaches.endPoint && Objects.equals(startTime, coaches.startTime) && Objects.equals(endTime, coaches.endTime) && Objects.equals(description, coaches.description) && Objects.equals(status, coaches.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, endTime, description, price, emptySeat, isShipping, coachId, startPoint, endPoint, status);
    }
}
