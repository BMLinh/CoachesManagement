package com.hal.CoachesWeb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "coaches_stop_by", schema = "coachesmanagementdb", catalog = "")
@IdClass(CoachesStopByPK.class)
public class CoachesStopBy {
    @Id
    @Column(name = "coaches_id", nullable = false)
    private int coachesId;
    @Id
    @Column(name = "stop_by_id", nullable = false)
    private int stopById;
    @Basic
    @Column(name = "time", nullable = false)
    private Time time;
    @Basic
    @Column(name = "status", nullable = false)
    private int status;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "coaches_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Coaches coachesByCoachesId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "stop_by_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private StopBy stopByByStopById;

    public int getCoachesId() {
        return coachesId;
    }

    public void setCoachesId(int coachesId) {
        this.coachesId = coachesId;
    }

    public int getStopById() {
        return stopById;
    }

    public void setStopById(int stopById) {
        this.stopById = stopById;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
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
        CoachesStopBy that = (CoachesStopBy) o;
        return coachesId == that.coachesId && stopById == that.stopById && time == that.time && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coachesId, stopById, time, status);
    }

    public Coaches getCoachesByCoachesId() {
        return coachesByCoachesId;
    }

    public void setCoachesByCoachesId(Coaches coachesByCoachesId) {
        this.coachesByCoachesId = coachesByCoachesId;
    }

    public StopBy getStopByByStopById() {
        return stopByByStopById;
    }

    public void setStopByByStopById(StopBy stopByByStopById) {
        this.stopByByStopById = stopByByStopById;
    }
}
