package com.hal.CoachesWeb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "coach_garage", schema = "coachesmanagementdb", catalog = "")
public class CoachGarage {
    //status = 0: delete or deactive; =1: active; =2: waiting for access
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @Basic
    @Column(name = "owner", nullable = false, length = 45)
    private String owner;
    @Basic
    @Column(name = "phone", nullable = false, length = 11)
    private String phone;
    @Basic
    @Column(name = "email", nullable = false, length = 45)
    private String email;
    @Basic
    @Column(name = "address", nullable = true, length = 50)
    private String address;
    @Basic
    @Column(name = "district_id", nullable = false)
    private int districtId;
    @Basic
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Basic
    @Column(name = "contract", nullable = false, length = 150)
    private String contract;
    @Basic
    @Column(name = "status", nullable = false)
    private int status;
    @JsonIgnore
    @OneToMany(mappedBy = "coachGarageByCoachGarageId")
    private Collection<Coach> coachesById;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private District districtByDistrictId;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private User userByUserId;

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
        CoachGarage that = (CoachGarage) o;
        return id == that.id && districtId == that.districtId && userId == that.userId && status == that.status && Objects.equals(name, that.name) && Objects.equals(owner, that.owner) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email) && Objects.equals(address, that.address) && Objects.equals(contract, that.contract);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, owner, phone, email, address, districtId, userId, contract, status);
    }

    public Collection<Coach> getCoachesById() {
        return coachesById;
    }

    public void setCoachesById(Collection<Coach> coachesById) {
        this.coachesById = coachesById;
    }

    public District getDistrictByDistrictId() {
        return districtByDistrictId;
    }

    public void setDistrictByDistrictId(District districtByDistrictId) {
        this.districtByDistrictId = districtByDistrictId;
    }

    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
