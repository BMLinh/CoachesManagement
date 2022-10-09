package com.hal.CoachesWeb.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "coach_garage", schema = "coachesmanagementdb", catalog = "")
public class CoachGarage {
    //status = 0: delete or deactive; =1: active; =2: waiting for access
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "owner")
    private String owner;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "district_id")
    private int districtId;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "contract")
    private String contract;
    @Basic
    @Column(name = "status")
    private Integer status;

    public CoachGarage() {
    }

    public CoachGarage(String name, String owner, String phone, String email, String address, int districtId, int userId, String contract, Integer status) {
        this.name = name;
        this.owner = owner;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.districtId = districtId;
        this.userId = userId;
        this.contract = contract;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoachGarage that = (CoachGarage) o;
        return id == that.id && districtId == that.districtId && userId == that.userId && Objects.equals(name, that.name) && Objects.equals(owner, that.owner) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email) && Objects.equals(address, that.address) && Objects.equals(contract, that.contract) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, owner, phone, email, address, districtId, userId, contract, status);
    }
}
