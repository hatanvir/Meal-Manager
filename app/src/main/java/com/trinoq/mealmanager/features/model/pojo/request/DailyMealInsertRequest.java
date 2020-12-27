package com.trinoq.mealmanager.features.model.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DailyMealInsertRequest {

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
    private String breakfast = null;
    @SerializedName("is_lunch")
    @Expose
    private String lunch = null;
    @SerializedName("is_dinner")
    @Expose
    private String dinner = null;

    public DailyMealInsertRequest(String groupId, String userId, String phoneNumber, String mealDate, String breakfast, String lunch, String dinner) {
        this.groupId = groupId;
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.mealDate = mealDate;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public DailyMealInsertRequest(String groupId, String userId, String phoneNumber, String mealDate, String mealNumber,String mealName) {
        this.groupId = groupId;
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.mealDate = mealDate;
        if (mealName.equals("Breakfast")){
            this.breakfast = mealNumber;
        }
        else if (mealName.equals("Lunch")){
            this.lunch = mealNumber;
        }
        else if (mealName.equals("Dinner")){
            this.dinner = mealNumber;
        }

    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
