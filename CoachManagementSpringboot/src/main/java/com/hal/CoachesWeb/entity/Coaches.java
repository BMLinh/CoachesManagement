package com.hal.CoachesWeb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    @NotBlank(message = "Giờ bắt đầu không được để trống")
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;
    @Basic
    @NotBlank(message = "Giờ kết thúc không được để trống")
    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;
    @Basic
    @Size(max = 150)
    @Column(name = "description", nullable = true, length = 150)
    private String description;
    @Basic
    @NotBlank(message = "Giá không được để trống")
    @Column(name = "price", nullable = false, precision = 0)
    private int price;
    @Basic
    @NotBlank(message = "Ghế trống không được để trống")
    @Column(name = "empty_seat", nullable = false)
    private int emptySeat;
    @Basic
    @NotBlank(message = "Giao hàng không được để trống")
    @Column(name = "is_shipping", nullable = false)
    private boolean isShipping;
    @Basic
    @NotBlank(message = "Mã xe không được để trống")
    @Column(name = "coach_id", nullable = false)
    private int coachId;
    @Basic
    @NotBlank(message = "Điểm xuất phát không được để trống")
    @Column(name = "start_point", nullable = false)
    private int startPoint;
    @Basic
    @NotBlank(message = "Điểm kết thúc không được để trống")
    @Column(name = "end_point", nullable = false)
    private int endPoint;
    @Basic
    @Column(name = "status", nullable = true)
    private Integer status;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "coach_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Coach coachByCoachId;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "start_point", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Country countryByStartPoint;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "end_point", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Country countryByEndPoint;
    @JsonBackReference
    @OneToMany(mappedBy = "coachesByCoachesId")
    private Collection<CoachesStopBy> coachesStopBIESById;
    @JsonBackReference
    @OneToMany(mappedBy = "coachesByCoachesId")
    private Collection<Shipping> shippingsById;
    @JsonBackReference
    @OneToMany(mappedBy = "coachesByCoachesId")
    private Collection<Ticket> ticketsById;

    public Coaches() {
    }

    public Coaches(LocalDateTime startTime, LocalDateTime endTime, String description, int price, int emptySeat, boolean isShipping, int coachId, int startPoint, int endPoint, Integer status) {
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
