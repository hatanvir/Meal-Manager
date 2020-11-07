package com.trinoq.mealmanager.features.model.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserMealCreateRequest {

    @SerializedName("group_id")
    @Expose
    private String groupId = null;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber = null;
    @SerializedName("meal_date")
    @Expose
    private String mealDate = null;
    @SerializedName("is_breakfast")
    @Expose
    private String isBreakfast = null;
    @SerializedName("is_lunch")
    @Expose
    private String isLunch = null;
    @SerializedName("is_dinner")
    @Expose
    private String isDinner = null;

    public UserMealCreateRequest(String groupId, String phoneNumber, String mealDate, String isBreakfast, String isLunch, String isDinner) {
        this.groupId = groupId;
        this.phoneNumber = phoneNumber;
        this.mealDate = mealDate;
        this.isBreakfast = isBreakfast;
        this.isLunch = isLunch;
        this.isDinner = isDinner;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMealDate() {
        return mealDate;
    }

    public void setMealDate(String mealDate) {
        this.mealDate = mealDate;
    }

    public String getIsBreakfast() {
        return isBreakfast;
    }

    public void setIsBreakfast(String isBreakfast) {
        this.isBreakfast = isBreakfast;
    }

    public String getIsLunch() {
        return isLunch;
    }

    public void setIsLunch(String isLunch) {
        this.isLunch = isLunch;
    }

    public String getIsDinner() {
        return isDinner;
    }

    public void setIsDinner(String isDinner) {
        this.isDinner = isDinner;
    }
}
