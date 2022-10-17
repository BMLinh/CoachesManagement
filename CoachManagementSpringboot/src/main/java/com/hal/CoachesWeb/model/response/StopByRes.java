package com.hal.CoachesWeb.model.response;

import com.hal.CoachesWeb.entity.StopBy;

import java.util.List;

public class StopByRes {
    private int id;
    private String districtName;
    private List<StopBy> stopBy;

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public List<StopBy> getStopBy() {
        return stopBy;
    }

    public void setStopBy(List<StopBy> stopBy) {
        this.stopBy = stopBy;
    }

    public StopByRes(String districtName, List<StopBy> stopBy) {
        this.districtName = districtName;
        this.stopBy = stopBy;
    }
}
