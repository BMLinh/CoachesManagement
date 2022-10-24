package com.hal.CoachesWeb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Shipping {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = true, length = 45)
    private String name;
    @Basic
    @Column(name = "senderName", nullable = true, length = 45)
    private String senderName;
    @Basic
    @Column(name = "senderPhone", nullable = false, length = 11)
    private String senderPhone;
    @Basic
    @Column(name = "senderEmail", nullable = false, length = 45)
    private String senderEmail;
    @Basic
    @Column(name = "receiverName", nullable = true, length = 45)
    private String receiverName;
    @Basic
    @Column(name = "receiverPhone", nullable = false, length = 11)
    private String receiverPhone;
    @Basic
    @Column(name = "receiverEmail", nullable = false, length = 45)
    private String receiverEmail;
    @Basic
    @Column(name = "sendTime", nullable = true)
    private Timestamp sendTime;
    @Basic
    @Column(name = "price", nullable = true, precision = 0)
    private Integer price;
    @Basic
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Basic
    @Column(name = "coaches_id", nullable = false)
    private int coachesId;
    @Basic
    @Column(name = "status", nullable = false)
    private int status;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private User userByUserId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "coaches_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Coaches coachesByCoachesId;

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

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCoachesId() {
        return coachesId;
    }

    public void setCoachesId(int coachesId) {
        this.coachesId = coachesId;
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
        Shipping shipping = (Shipping) o;
        return id == shipping.id && userId == shipping.userId && coachesId == shipping.coachesId && status == shipping.status && Objects.equals(name, shipping.name) && Objects.equals(senderName, shipping.senderName) && Objects.equals(senderPhone, shipping.senderPhone) && Objects.equals(senderEmail, shipping.senderEmail) && Objects.equals(receiverName, shipping.receiverName) && Objects.equals(receiverPhone, shipping.receiverPhone) && Objects.equals(receiverEmail, shipping.receiverEmail) && Objects.equals(sendTime, shipping.sendTime) && Objects.equals(price, shipping.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, senderName, senderPhone, senderEmail, receiverName, receiverPhone, receiverEmail, sendTime, price, userId, coachesId, status);
    }

    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    public Coaches getCoachesByCoachesId() {
        return coachesByCoachesId;
    }

    public void setCoachesByCoachesId(Coaches coachesByCoachesId) {
        this.coachesByCoachesId = coachesByCoachesId;
    }
}
