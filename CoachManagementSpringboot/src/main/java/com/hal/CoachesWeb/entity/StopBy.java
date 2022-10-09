package com.hal.CoachesWeb.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "stop_by", schema = "coachesmanagementdb", catalog = "")
public class StopBy {
    //status = 0: delete or deactive; =1: active
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "district_id")
    private Integer districtId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public StopBy() {
    }
    public StopBy(String name, String address, Integer districtId, Integer status) {
        this.name = name;
        this.address = address;
        this.districtId = districtId;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StopBy stopBy = (StopBy) o;
        return id == stopBy.id && Objects.equals(name, stopBy.name) && Objects.equals(address, stopBy.address) && Objects.equals(districtId, stopBy.districtId) && Objects.equals(status, stopBy.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, districtId, status);
    }
}
