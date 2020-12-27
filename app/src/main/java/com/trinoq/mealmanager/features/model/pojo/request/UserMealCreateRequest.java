package com.trinoq.mealmanager.features.model.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserMealCreateRequest {


    @SerializedName("group_id")
    @Expose
    private String groupId = null;
    @SerializedName("user_id")
    @Expose
    private String userId = null;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber = null;
    @SerializedName("meal_date")
    @Expose
    private String mealDate = null;
    @SerializedName("is_breakfast")
    @Expose
    private Integer isBreakfast = 0;
    @SerializedName("is_lunch")
    @Expose
    private Integer isLunch = 0;
    @SerializedName("is_dinner")
    @Expose
    private Integer isDinner = 0;

    public UserMealCreateRequest(String groupId, String userId, String phoneNumber, String mealDate, Integer isBreakfast, Integer isLunch, Integer isDinner) {
        this.groupId = groupId;
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.mealDate = mealDate;
        this.isBreakfast = isBreakfast;
        this.isLunch = isLunch;
        this.isDinner = isDinner;
    }

    public UserMealCreateRequest(String groupId, String userId, String phoneNumber, String mealDate, Integer mealNumber, String mealName) {
        this.groupId = groupId;
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.mealDate = mealDate;
        if (mealName.equals("Breakfast")){
            this.isDinner = mealNumber;
        }
        else if (mealName.equals("Lunch")){
            this.isLunch = mealNumber;
        }
        else if (mealName.equals("Dinner")){
            this.isDinner = mealNumber;
        }
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getIsBreakfast() {
        return isBreakfast;
    }

    public void setIsBreakfast(Integer isBreakfast) {
        this.isBreakfast = isBreakfast;
    }

    public Integer getIsLunch() {
        return isLunch;
    }

    public void setIsLunch(Integer isLunch) {
        this.isLunch = isLunch;
    }

    public Integer getIsDinner() {
        return isDinner;
    }

    public void setIsDinner(Integer isDinner) {
        this.isDinner = isDinner;
    }
}
