
package com.trinoq.mealmanager.features.model.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dailymealinputdatum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("group_id")
    @Expose
    private Integer groupId;
    @SerializedName("breakfast_date_time")
    @Expose
    private String breakfastDateTime;
    @SerializedName("lunch_date_time")
    @Expose
    private String lunchDateTime;
    @SerializedName("dinner_date_time")
    @Expose
    private String dinnerDateTime;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getBreakfastDateTime() {
        return breakfastDateTime;
    }

    public void setBreakfastDateTime(String breakfastDateTime) {
        this.breakfastDateTime = breakfastDateTime;
    }

    public String getLunchDateTime() {
        return lunchDateTime;
    }

    public void setLunchDateTime(String lunchDateTime) {
        this.lunchDateTime = lunchDateTime;
    }

    public String getDinnerDateTime() {
        return dinnerDateTime;
    }

    public void setDinnerDateTime(String dinnerDateTime) {
        this.dinnerDateTime = dinnerDateTime;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
