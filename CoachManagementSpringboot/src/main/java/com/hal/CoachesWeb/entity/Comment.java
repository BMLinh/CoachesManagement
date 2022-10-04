package com.hal.CoachesWeb.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Comment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "content")
    private String content;
    @Basic
    @Column(name = "rating")
    private Integer rating;
    @Basic
    @Column(name = "create_date")
    private Date createDate;
    @Basic
    @Column(name = "coach_id")
    private String coachId;
    @Basic
    @Column(name = "user_id")
    private Integer userId;
    @Basic
    @Column(name = "status")
    private Integer status;

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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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
        return id == comment.id && Objects.equals(content, comment.content) && Objects.equals(rating, comment.rating) && Objects.equals(createDate, comment.createDate) && Objects.equals(coachId, comment.coachId) && Objects.equals(userId, comment.userId) && Objects.equals(status, comment.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, rating, createDate, coachId, userId, status);
    }
}
