package com.hal.CoachesWeb.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Shipping {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "senderName")
    private String senderName;
    @Basic
    @Column(name = "senderPhone")
    private String senderPhone;
    @Basic
    @Column(name = "senderEmail")
    private String senderEmail;
    @Basic
    @Column(name = "receiverName")
    private String receiverName;
    @Basic
    @Column(name = "receiverPhone")
    private String receiverPhone;
    @Basic
    @Column(name = "receiverEmail")
    private String receiverEmail;
    @Basic
    @Column(name = "sendTime")
    private Timestamp sendTime;
    @Basic
    @Column(name = "price")
    private Integer price;
    @Basic
    @Column(name = "coaches_id")
    private Integer coachesId;
    @Basic
    @Column(name = "status")
    private Integer status;

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

    public Integer getCoachesId() {
        return coachesId;
    }

    public void setCoachesId(Integer coachesId) {
        this.coachesId = coachesId;
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
        Shipping shipping = (Shipping) o;
        return id == shipping.id && Objects.equals(name, shipping.name) && Objects.equals(senderName, shipping.senderName) && Objects.equals(senderPhone, shipping.senderPhone) && Objects.equals(senderEmail, shipping.senderEmail) && Objects.equals(receiverName, shipping.receiverName) && Objects.equals(receiverPhone, shipping.receiverPhone) && Objects.equals(receiverEmail, shipping.receiverEmail) && Objects.equals(sendTime, shipping.sendTime) && Objects.equals(price, shipping.price) && Objects.equals(coachesId, shipping.coachesId) && Objects.equals(status, shipping.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, senderName, senderPhone, senderEmail, receiverName, receiverPhone, receiverEmail, sendTime, price, coachesId, status);
    }
}
