package com.hal.CoachesWeb.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "coaches_stop_by", schema = "coachesmanagementdb", catalog = "")
@IdClass(CoachesStopByPK.class)
public class CoachesStopBy {
    @Id
    @Column(name = "coaches_id")
    private int coachesId;
    @Id
    @Column(name = "stop_by_id")
    private int stopById;
    @Basic
    @Column(name = "time")
    private Integer time;
    @Basic
    @Column(name = "status")
    private Integer status;

    public CoachesStopBy() {
    }

    public CoachesStopBy(int coachesId, int stopById, Integer time, Integer status) {
        this.coachesId = coachesId;
        this.stopById = stopById;
        this.time = time;
        this.status = status;
    }

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

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
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
        CoachesStopBy that = (CoachesStopBy) o;
        return coachesId == that.coachesId && stopById == that.stopById && Objects.equals(time, that.time) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coachesId, stopById, time, status);
    }
}
