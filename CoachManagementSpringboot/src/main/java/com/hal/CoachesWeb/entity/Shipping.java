package com.hal.CoachesWeb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
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
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sendTime", nullable = true)
    private Date sendTime;
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

    public Shipping() {
    }

    public Shipping(String name, String senderName, String senderPhone, String senderEmail, String receiverName
            , String receiverPhone, String receiverEmail, Date sendTime
            , Integer price, int userId, int coachesId, int status) {
        this.name = name;
        this.senderName = senderName;
        this.senderPhone = senderPhone;
        this.senderEmail = senderEmail;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiverEmail = receiverEmail;
        this.sendTime = sendTime;
        this.price = price;
        this.userId = userId;
        this.coachesId = coachesId;
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

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
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
