package com.trinoq.mealmanager.features.model.pojo.request;

import com.google.gson.annotations.SerializedName;

public class PostMonthRequest {
    @SerializedName("group_id")
    String group_id;
    @SerializedName("is_breakfast_half")
    String is_breakfast_half;
    @SerializedName("is_lunch_full")
    String is_lunch_full;
    @SerializedName("is_dinner_full")
    String is_dinner_full;

    public PostMonthRequest(String group_id, String is_breakfast_half,
                            String is_lunch_full, String is_dinner_full) {
        this.group_id = group_id;
        this.is_breakfast_half = is_breakfast_half;
        this.is_lunch_full = is_lunch_full;
        this.is_dinner_full = is_dinner_full;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getIs_breakfast_half() {
        return is_breakfast_half;
    }

    public void setIs_breakfast_half(String is_breakfast_half) {
        this.is_breakfast_half = is_breakfast_half;
    }

    public String getIs_lunch_full() {
        return is_lunch_full;
    }

    public void setIs_lunch_full(String is_lunch_full) {
        this.is_lunch_full = is_lunch_full;
    }

    public String getIs_dinner_full() {
        return is_dinner_full;
    }

    public void setIs_dinner_full(String is_dinner_full) {
        this.is_dinner_full = is_dinner_full;
    }
}
