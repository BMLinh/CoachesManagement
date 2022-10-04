package com.hal.CoachesWeb.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CoachesStopByPK implements Serializable {
    @Column(name = "coaches_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int coachesId;
    @Column(name = "stopBy_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stopById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoachesStopByPK that = (CoachesStopByPK) o;
        return coachesId == that.coachesId && stopById == that.stopById;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coachesId, stopById);
    }
}
