package com.hal.CoachesWeb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Ticket {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "price", nullable = true, precision = 0)
    private Integer price;
    @Basic
    @Column(name = "email", nullable = true, length = 45)
    private String email;
    @Basic
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = true)
    private Date createDate;
    @Basic
    @Column(name = "phone", nullable = true, length = 11)
    private String phone;
    @Basic
    @Column(name = "name", nullable = true, length = 45)
    private String name;
    @Basic
    @Column(name = "amount", nullable = false)
    private int amount;
    @Basic
    @Column(name = "coaches_id", nullable = false)
    private int coachesId;
    @Basic
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Basic
    @Column(name = "pick_up_id", nullable = false)
    private int pickUpId;
    @Basic
    @Column(name = "drop_off_id", nullable = false)
    private int dropOffId;
    @Basic
    @Column(name = "status", nullable = true)
    private Integer status;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "coaches_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Coaches coachesByCoachesId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private User userByUserId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pick_up_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private StopBy stopByByPickUpId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "drop_off_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private StopBy stopByByDropOffId;

    public Ticket() {
    }

    public Ticket(Integer price, String email, String phone,int amount, int coachesId, int userId, int pickUpId, int dropOffId, Integer status) {
        this.price = price;
        this.email = email;
        this.phone = phone;
        this.amount = amount;
        this.coachesId = coachesId;
        this.userId = userId;
        this.pickUpId = pickUpId;
        this.dropOffId = dropOffId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCoachesId() {
        return coachesId;
    }

    public void setCoachesId(int coachesId) {
        this.coachesId = coachesId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPickUpId() {
        return pickUpId;
    }

    public void setPickUpId(int pickUpId) {
        this.pickUpId = pickUpId;
    }

    public int getDropOffId() {
        return dropOffId;
    }

    public void setDropOffId(int dropOffId) {
        this.dropOffId = dropOffId;
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
        Ticket ticket = (Ticket) o;
        return id == ticket.id && coachesId == ticket.coachesId && userId == ticket.userId && pickUpId == ticket.pickUpId && dropOffId == ticket.dropOffId && Objects.equals(price, ticket.price) && Objects.equals(email, ticket.email) && Objects.equals(createDate, ticket.createDate) && Objects.equals(phone, ticket.phone) && Objects.equals(amount, ticket.amount) && Objects.equals(status, ticket.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, email, createDate, phone, amount, coachesId, userId, pickUpId, dropOffId, status);
    }

    public Coaches getCoachesByCoachesId() {
        return coachesByCoachesId;
    }

    public void setCoachesByCoachesId(Coaches coachesByCoachesId) {
        this.coachesByCoachesId = coachesByCoachesId;
    }

    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    public StopBy getStopByByPickUpId() {
        return stopByByPickUpId;
    }

    public void setStopByByPickUpId(StopBy stopByByPickUpId) {
        this.stopByByPickUpId = stopByByPickUpId;
    }

    public StopBy getStopByByDropOffId() {
        return stopByByDropOffId;
    }

    public void setStopByByDropOffId(StopBy stopByByDropOffId) {
        this.stopByByDropOffId = stopByByDropOffId;
    }
}
