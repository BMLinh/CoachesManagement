package com.hal.CoachesWeb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class District {
    //status = 0: delete; =1: active
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = true, length = 45)
    private String name;
    @Basic
    @Column(name = "country_id", nullable = false)
    private int countryId;
    @Basic
    @Column(name = "status", nullable = false)
    private int status;
    @JsonIgnore
    @OneToMany(mappedBy = "districtByDistrictId")
    private Collection<CoachGarage> coachGaragesById;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Country countryByCountryId;
    @JsonIgnore
    @OneToMany(mappedBy = "districtByDistrictId")
    private Collection<StopBy> stopBIESById;

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

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
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
        District district = (District) o;
        return id == district.id && countryId == district.countryId && status == district.status && Objects.equals(name, district.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, countryId, status);
    }

    public Collection<CoachGarage> getCoachGaragesById() {
        return coachGaragesById;
    }

    public void setCoachGaragesById(Collection<CoachGarage> coachGaragesById) {
        this.coachGaragesById = coachGaragesById;
    }

    public Country getCountryByCountryId() {
        return countryByCountryId;
    }

    public void setCountryByCountryId(Country countryByCountryId) {
        this.countryByCountryId = countryByCountryId;
    }

    public Collection<StopBy> getStopBIESById() {
        return stopBIESById;
    }

    public void setStopBIESById(Collection<StopBy> stopBIESById) {
        this.stopBIESById = stopBIESById;
    }
}
