package com.hal.CoachesWeb.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Coaches {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Basic
    @Column(name = "end_time")
    private LocalDateTime endTime;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "price")
    private int price;
    @Basic
    @Column(name = "empty_seat")
    private int emptySeat;
    @Basic
    @Column(name = "is_shipping")
    private boolean isShipping;
    @Basic
    @Column(name = "coach_id")
    private int coachId;
    @Basic
    @Column(name = "start_point")
    private int startPoint;
    @Basic
    @Column(name = "end_point")
    private int endPoint;
    @Basic
    @Column(name = "status")
    private Integer status;

//    @ManyToMany
//    @JoinTable(
//            name = "coaches_stop_by",
//            joinColumns = {
//                    @JoinColumn(name = "coaches_id")
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(name = {})
//            }
//    )
    public Coaches() {
    }

    public Coaches(LocalDateTime startTime, LocalDateTime endTime, String description, int price, int emptySeat, boolean isShipping, int coachId, int startPoint, int endPoint, Integer status) {
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
