package com.trinoq.mealmanager.features.model.pojo.request;

import com.google.gson.annotations.SerializedName;

public class DailyMealInputEndTimeRequest {
    @SerializedName("group_id")
    String group_id;
    @SerializedName("breakfast_date_time")
    String breakfast_date_time;
    @SerializedName("lunch_date_time")
    String lunch_date_time;
    @SerializedName("dinner_date_time")
    String dinner_date_time;

    public DailyMealInputEndTimeRequest(String group_id, String breakfast_date_time,
                                        String lunch_date_time, String dinner_date_time) {
        this.group_id = group_id;
        this.breakfast_date_time = breakfast_date_time;
        this.lunch_date_time = lunch_date_time;
        this.dinner_date_time = dinner_date_time;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getBreakfast_date_time() {
        return breakfast_date_time;
    }

    public void setBreakfast_date_time(String breakfast_date_time) {
        this.breakfast_date_time = breakfast_date_time;
    }

    public String getLunch_date_time() {
        return lunch_date_time;
    }

    public void setLunch_date_time(String lunch_date_time) {
        this.lunch_date_time = lunch_date_time;
    }

    public String getDinner_date_time() {
        return dinner_date_time;
    }

    public void setDinner_date_time(String dinner_date_time) {
        this.dinner_date_time = dinner_date_time;
    }
}
