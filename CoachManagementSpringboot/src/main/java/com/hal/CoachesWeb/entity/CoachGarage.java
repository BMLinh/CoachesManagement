package com.hal.CoachesWeb.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "coach_garage", schema = "coachesmanagementdb", catalog = "")
public class CoachGarage {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "distric_id")
    private Integer districId;
    @Basic
    @Column(name = "user_id")
    private Integer userId;
    @Basic
    @Column(name = "contract")
    private String contract;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getDistricId() {
        return districId;
    }

    public void setDistricId(Integer districId) {
        this.districId = districId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public  CoachGarage(){

    }
    public CoachGarage(String name, String phone, String address, Integer districId, Integer userId, String contract, Integer status) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.districId = districId;
        this.userId = userId;
        this.contract = contract;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoachGarage that = (CoachGarage) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(phone, that.phone) && Objects.equals(address, that.address) && Objects.equals(districId, that.districId) && Objects.equals(userId, that.userId) && Objects.equals(contract, that.contract) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, address, districId, userId, contract, status);
    }
}
