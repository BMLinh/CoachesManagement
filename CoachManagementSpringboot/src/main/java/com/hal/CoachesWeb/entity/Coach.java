package com.hal.CoachesWeb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Coach {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @NotBlank(message = "Biến số xe không được để trống")
    @Size(max = 20, message = "Độ dài biển số vượt quá quy định")
    @Column(name = "license_plates", nullable = false, length = 20)
    private String licensePlates;
    @Basic
    @Size(max = 100)
    @Column(name = "description", nullable = true, length = 100)
    private String description;
    @Basic
    @NotBlank(message = "Nhà xe id không được để trống")
    @Column(name = "coach_garage_id", nullable = false)
    private int coachGarageId;
    @Basic
    @NotBlank(message = "Loại xe không được để trống")
    @Column(name = "category_id", nullable = false)
    private int categoryId;
    @Basic
    @Column(name = "status", nullable = false)
    private int status;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "coach_garage_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private CoachGarage coachGarageByCoachGarageId;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Category categoryByCategoryId;
    @JsonBackReference
    @OneToMany(mappedBy = "coachByCoachId")
    private Collection<Coaches> coachesById;
    @JsonBackReference
    @OneToMany(mappedBy = "coachByCoachId")
    private Collection<Comment> commentsById;
    @JsonBackReference
    @OneToMany(mappedBy = "coachByCoachId")
    private Collection<Picture> picturesById;

    public Coach() {
    }

    public Coach(String licensePlates, String description, int coachGarageId, int categoryId, int status) {
        this.licensePlates = licensePlates;
        this.description = description;
        this.coachGarageId = coachGarageId;
        this.categoryId = categoryId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicensePlates() {
        return licensePlates;
    }

    public void setLicensePlates(String licensePlates) {
        this.licensePlates = licensePlates;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCoachGarageId() {
        return coachGarageId;
    }

    public void setCoachGarageId(int coachGarageId) {
        this.coachGarageId = coachGarageId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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
        Coach coach = (Coach) o;
        return id == coach.id && coachGarageId == coach.coachGarageId && categoryId == coach.categoryId && status == coach.status && Objects.equals(licensePlates, coach.licensePlates) && Objects.equals(description, coach.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, licensePlates, description, coachGarageId, categoryId, status);
    }

    public CoachGarage getCoachGarageByCoachGarageId() {
        return coachGarageByCoachGarageId;
    }

    public void setCoachGarageByCoachGarageId(CoachGarage coachGarageByCoachGarageId) {
        this.coachGarageByCoachGarageId = coachGarageByCoachGarageId;
    }

    public Category getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

    public void setCategoryByCategoryId(Category categoryByCategoryId) {
        this.categoryByCategoryId = categoryByCategoryId;
    }

    public Collection<Coaches> getCoachesById() {
        return coachesById;
    }

    public void setCoachesById(Collection<Coaches> coachesById) {
        this.coachesById = coachesById;
    }

    public Collection<Comment> getCommentsById() {
        return commentsById;
    }

    public void setCommentsById(Collection<Comment> commentsById) {
        this.commentsById = commentsById;
    }

    public Collection<Picture> getPicturesById() {
        return picturesById;
    }

    public void setPicturesById(Collection<Picture> picturesById) {
        this.picturesById = picturesById;
    }
}
