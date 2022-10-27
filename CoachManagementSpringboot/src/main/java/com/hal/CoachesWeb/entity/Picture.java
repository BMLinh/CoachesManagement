package com.hal.CoachesWeb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Picture {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "url", nullable = true, length = 150)
    private String url;
    @Basic
    @Column(name = "coach_id", nullable = false)
    private int coachId;
    @Basic
    @Column(name = "status", nullable = false)
    private int status;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coach_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Coach coachByCoachId;

    public Picture() {
    }

    public Picture(String url, int coachId, int status) {
        this.url = url;
        this.coachId = coachId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
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
        Picture picture = (Picture) o;
        return id == picture.id && coachId == picture.coachId && status == picture.status && Objects.equals(url, picture.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, coachId, status);
    }

    public Coach getCoachByCoachId() {
        return coachByCoachId;
    }

    public void setCoachByCoachId(Coach coachByCoachId) {
        this.coachByCoachId = coachByCoachId;
    }
}
