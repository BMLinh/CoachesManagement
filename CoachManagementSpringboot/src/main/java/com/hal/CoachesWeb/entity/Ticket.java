package com.hal.CoachesWeb.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Ticket {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "price")
    private Double price;
    @Basic
    @Column(name = "create_date")
    private Date createDate;
    @Basic
    @Column(name = "coaches_id")
    private Integer coachesId;
    @Basic
    @Column(name = "user_id")
    private Integer userId;
    @Basic
    @Column(name = "status")
    private Integer status;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "pick_up_id")
    private Integer pickUpId;
    @Basic
    @Column(name = "drop_off_id")
    private Integer dropOffId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCoachesId() {
        return coachesId;
    }

    public void setCoachesId(Integer coachesId) {
        this.coachesId = coachesId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getPickUpId() {
        return pickUpId;
    }

    public void setPickUpId(Integer pickUpId) {
        this.pickUpId = pickUpId;
    }

    public Integer getDropOffId() {
        return dropOffId;
    }

    public void setDropOffId(Integer dropOffId) {
        this.dropOffId = dropOffId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && Objects.equals(price, ticket.price) && Objects.equals(createDate, ticket.createDate) && Objects.equals(coachesId, ticket.coachesId) && Objects.equals(userId, ticket.userId) && Objects.equals(status, ticket.status) && Objects.equals(phone, ticket.phone) && Objects.equals(pickUpId, ticket.pickUpId) && Objects.equals(dropOffId, ticket.dropOffId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, createDate, coachesId, userId, status, phone, pickUpId, dropOffId);
    }
}
