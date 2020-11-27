package com.trinoq.mealmanager.features.model.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActiveGroupUpdatedRequest {

    //active_groupid
    @SerializedName("active_groupid")
    @Expose
    private String activeGroupid;

    public ActiveGroupUpdatedRequest(String activeGroupid) {
        this.activeGroupid = activeGroupid;
    }

    public String getActiveGroupid() {
        return activeGroupid;
    }

    public void setActiveGroupid(String activeGroupid) {
        this.activeGroupid = activeGroupid;
    }
}
