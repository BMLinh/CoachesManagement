package com.hal.CoachesWeb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "password", nullable = false, length = 60)
    private String password;
    @Basic
    @Column(name = "fullname", nullable = false, length = 45)
    private String fullname;
    @Basic
    @Column(name = "email", nullable = false, length = 45)
    private String email;
    @Basic
    @Column(name = "phone", nullable = false, length = 11)
    private String phone;
    @Basic
    @Column(name = "gender", nullable = true)
    private Boolean gender;
    @Basic
    @Column(name = "avatar", nullable = true, length = 100)
    private String avatar;
    @Basic
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = true)
    private Date createdDate;
    @Basic
    @Column(name = "role_id", nullable = false)
    private int roleId;
    @Basic
    @Column(name = "status", nullable = false)
    private int status;
    @JsonBackReference
    @OneToMany(mappedBy = "userByUserId")
    private Collection<CoachGarage> coachGaragesById;
    @JsonBackReference
    @OneToMany(mappedBy = "userByUserId")
    private Collection<Comment> commentsById;
    @JsonBackReference
    @OneToMany(mappedBy = "userByUserId")
    private Collection<Shipping> shippingsById;
    @JsonBackReference
    @OneToMany(mappedBy = "userByUserId")
    private Collection<Ticket> ticketsById;

    public User() {
    }

    public User(String password, String fullname, String email, String phone, Boolean gender, String avatar, Integer roleId, Integer status) {
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.avatar = avatar;
        this.roleId = roleId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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
        User user = (User) o;
        return id == user.id && roleId == user.roleId && status == user.status && Objects.equals(password, user.password) && Objects.equals(fullname, user.fullname) && Objects.equals(email, user.email) && Objects.equals(phone, user.phone) && Objects.equals(gender, user.gender) && Objects.equals(avatar, user.avatar) && Objects.equals(createdDate, user.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, fullname, email, phone, gender, avatar, createdDate, roleId, status);
    }

    public Collection<CoachGarage> getCoachGaragesById() {
        return coachGaragesById;
    }

    public void setCoachGaragesById(Collection<CoachGarage> coachGaragesById) {
        this.coachGaragesById = coachGaragesById;
    }

    public Collection<Comment> getCommentsById() {
        return commentsById;
    }

    public void setCommentsById(Collection<Comment> commentsById) {
        this.commentsById = commentsById;
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
