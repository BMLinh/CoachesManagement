package com.hal.CoachesWeb.model.response;

import java.sql.Time;

public class StopByDetailRes {
    private int stopById;
    private String name;
    private Time time;

    public StopByDetailRes(int stopById, String name, Time time) {
        this.stopById = stopById;
        this.name = name;
        this.time = time;
    }

    public int getStopById() {
        return stopById;
    }

    public void setStopById(int stopById) {
        this.stopById = stopById;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
