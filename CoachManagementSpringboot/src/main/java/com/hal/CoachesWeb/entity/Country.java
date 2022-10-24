package com.hal.CoachesWeb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Country {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = true, length = 45)
    private String name;
    @Basic
    @Column(name = "status", nullable = false)
    private int status;
    @JsonIgnore
    @OneToMany(mappedBy = "countryByStartPoint")
    private Collection<Coaches> coachesById;
    @JsonIgnore
    @OneToMany(mappedBy = "countryByEndPoint")
    private Collection<Coaches> coachesById_0;
    @JsonIgnore
    @OneToMany(mappedBy = "countryByCountryId")
    private Collection<District> districtsById;

    public Country() {
    }

    public Country(String name, Integer status) {
        this.name = name;
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
        Country country = (Country) o;
        return id == country.id && status == country.status && Objects.equals(name, country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status);
    }

    public Collection<Coaches> getCoachesById() {
        return coachesById;
    }

    public void setCoachesById(Collection<Coaches> coachesById) {
        this.coachesById = coachesById;
    }

    public Collection<Coaches> getCoachesById_0() {
        return coachesById_0;
    }

    public void setCoachesById_0(Collection<Coaches> coachesById_0) {
        this.coachesById_0 = coachesById_0;
    }

    public Collection<District> getDistrictsById() {
        return districtsById;
    }

    public void setDistrictsById(Collection<District> districtsById) {
        this.districtsById = districtsById;
    }
}
