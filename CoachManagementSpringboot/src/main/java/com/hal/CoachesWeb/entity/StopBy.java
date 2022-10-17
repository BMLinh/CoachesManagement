package com.hal.CoachesWeb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "stop_by", schema = "coachesmanagementdb", catalog = "")
public class StopBy {
    //status = 0: delete; =1: active
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = true, length = 45)
    private String name;
    @Basic
    @Column(name = "address", nullable = true, length = 45)
    private String address;
    @Basic
    @Column(name = "district_id", nullable = false)
    private int districtId;
    @Basic
    @Column(name = "status", nullable = false)
    private int status;
    @JsonBackReference
    @OneToMany(mappedBy = "stopByByStopById")
    private Collection<CoachesStopBy> coachesStopBIESById;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "district_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private District districtByDistrictId;
    @OneToMany(mappedBy = "stopByByPickUpId")
    @JsonBackReference
    private Collection<Ticket> ticketsById;
    @JsonBackReference
    @OneToMany(mappedBy = "stopByByDropOffId")
    private Collection<Ticket> ticketsById_0;

    public StopBy() {
    }
    
    public StopBy(String name, String address, Integer districtId, Integer status) {
        this.name = name;
        this.address = address;
        this.districtId = districtId;
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
        StopBy stopBy = (StopBy) o;
        return id == stopBy.id && districtId == stopBy.districtId && status == stopBy.status && Objects.equals(name, stopBy.name) && Objects.equals(address, stopBy.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, districtId, status);
    }

    public Collection<CoachesStopBy> getCoachesStopBIESById() {
        return coachesStopBIESById;
    }

    public void setCoachesStopBIESById(Collection<CoachesStopBy> coachesStopBIESById) {
        this.coachesStopBIESById = coachesStopBIESById;
    }

    public District getDistrictByDistrictId() {
        return districtByDistrictId;
    }

    public void setDistrictByDistrictId(District districtByDistrictId) {
        this.districtByDistrictId = districtByDistrictId;
    }

    public Collection<Ticket> getTicketsById() {
        return ticketsById;
    }

    public void setTicketsById(Collection<Ticket> ticketsById) {
        this.ticketsById = ticketsById;
    }

    public Collection<Ticket> getTicketsById_0() {
        return ticketsById_0;
    }

    public void setTicketsById_0(Collection<Ticket> ticketsById_0) {
        this.ticketsById_0 = ticketsById_0;
    }
}
