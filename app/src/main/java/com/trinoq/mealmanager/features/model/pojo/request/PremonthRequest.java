package com.trinoq.mealmanager.features.model.pojo.request;

import com.google.gson.annotations.SerializedName;

public class PremonthRequest {
    @SerializedName("group_id")
    String group_id;
    @SerializedName("breakfast")
    String breakfast;
    @SerializedName("lunch")
    String lunch;
    @SerializedName("dinner")
    String dinner;

    public PremonthRequest(String group_id, String breakfast,
                           String lunch, String dinner) {
        this.group_id = group_id;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }
}
