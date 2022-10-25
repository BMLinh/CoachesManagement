package com.hal.CoachesWeb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Coaches {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;
    @Basic
    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;
    @Basic
    @Column(name = "description", nullable = true, length = 150)
    private String description;
    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    private int price;
    @Basic
    @Column(name = "empty_seat", nullable = false)
    private int emptySeat;
    @Basic
    @Column(name = "is_shipping", nullable = false)
    private boolean isShipping;
    @Basic
    @Column(name = "coach_id", nullable = false)
    private int coachId;
    @Basic
    @Column(name = "start_point", nullable = false)
    private int startPoint;
    @Basic
    @Column(name = "end_point", nullable = false)
    private int endPoint;
    @Basic
    @Column(name = "status", nullable = true)
    private int status;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "coach_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Coach coachByCoachId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "start_point", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Country countryByStartPoint;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "end_point", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Country countryByEndPoint;
    @JsonIgnore
    @OneToMany(mappedBy = "coachesByCoachesId")
    private Collection<CoachesStopBy> coachesStopBIESById;
    @JsonIgnore
    @OneToMany(mappedBy = "coachesByCoachesId")
    private Collection<Shipping> shippingsById;
    @JsonIgnore
    @OneToMany(mappedBy = "coachesByCoachesId")
    private Collection<Ticket> ticketsById;

    public Coaches() {
    }

    public Coaches(LocalDateTime startTime, LocalDateTime endTime, String description, int price, int emptySeat, boolean isShipping, int coachId, int startPoint, int endPoint, int status) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.price = price;
        this.emptySeat = emptySeat;
        this.isShipping = isShipping;
        this.coachId = coachId;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getEmptySeat() {
        return emptySeat;
    }

    public void setEmptySeat(int emptySeat) {
        this.emptySeat = emptySeat;
    }

    public boolean isShipping() {
        return isShipping;
    }

    public void setShipping(boolean shipping) {
        isShipping = shipping;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public int getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(int startPoint) {
        this.startPoint = startPoint;
    }

    public int getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(int endPoint) {
        this.endPoint = endPoint;
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
        Coaches coaches = (Coaches) o;
        return id == coaches.id && price == coaches.price && emptySeat == coaches.emptySeat && isShipping == coaches.isShipping && coachId == coaches.coachId && startPoint == coaches.startPoint && endPoint == coaches.endPoint && Objects.equals(startTime, coaches.startTime) && Objects.equals(endTime, coaches.endTime) && Objects.equals(description, coaches.description) && Objects.equals(status, coaches.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, endTime, description, price, emptySeat, isShipping, coachId, startPoint, endPoint, status);
    }

    public Coach getCoachByCoachId() {
        return coachByCoachId;
    }

    public void setCoachByCoachId(Coach coachByCoachId) {
        this.coachByCoachId = coachByCoachId;
    }

    public Country getCountryByStartPoint() {
        return countryByStartPoint;
    }

    public void setCountryByStartPoint(Country countryByStartPoint) {
        this.countryByStartPoint = countryByStartPoint;
    }

    public Country getCountryByEndPoint() {
        return countryByEndPoint;
    }

    public void setCountryByEndPoint(Country countryByEndPoint) {
        this.countryByEndPoint = countryByEndPoint;
    }

    public Collection<CoachesStopBy> getCoachesStopBIESById() {
        return coachesStopBIESById;
    }

    public void setCoachesStopBIESById(Collection<CoachesStopBy> coachesStopBIESById) {
        this.coachesStopBIESById = coachesStopBIESById;
    }

    public Collection<Shipping> getShippingsById() {
        return shippingsById;
    }

    public void setShippingsById(Collection<Shipping> shippingsById) {
        this.shippingsById = shippingsById;
    }

    public Collection<Ticket> getTicketsById() {
        return ticketsById;
    }

    public void setTicketsById(Collection<Ticket> ticketsById) {
        this.ticketsById = ticketsById;
    }
}
