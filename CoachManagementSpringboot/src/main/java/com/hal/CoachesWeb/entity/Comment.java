package com.hal.CoachesWeb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Comment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "content", nullable = true, length = 200)
    private String content;
    @Basic
    @Column(name = "rating", nullable = false)
    private int rating;
    @Basic
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = true)
    private Date createDate;
    @Basic
    @Column(name = "coach_id", nullable = false)
    private int coachId;
    @Basic
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Basic
    @Column(name = "status", nullable = true)
    private Integer status;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "coach_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Coach coachByCoachId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private User userByUserId;

    public Comment() {
    }

    public Comment(String content, Integer rating,  int coachId, Integer userId, Integer status) {
        this.content = content;
        this.rating = rating;
        this.coachId = coachId;
        this.userId = userId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        Comment comment = (Comment) o;
        return id == comment.id && rating == comment.rating && coachId == comment.coachId && userId == comment.userId && Objects.equals(content, comment.content) && Objects.equals(createDate, comment.createDate) && Objects.equals(status, comment.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, rating, createDate, coachId, userId, status);
    }

    public Coach getCoachByCoachId() {
        return coachByCoachId;
    }

    public void setCoachByCoachId(Coach coachByCoachId) {
        this.coachByCoachId = coachByCoachId;
    }

    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
