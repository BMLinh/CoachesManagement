package com.hal.CoachesWeb.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CoachesStopByPK implements Serializable {
    @Column(name = "coaches_id")
    @Id
    private int coachesId;
    @Column(name = "stop_by_id")
    @Id
    private int stopById;

    public CoachesStopByPK() {
    }

    public CoachesStopByPK(int coachesId, int stopById) {
        this.coachesId = coachesId;
        this.stopById = stopById;
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
