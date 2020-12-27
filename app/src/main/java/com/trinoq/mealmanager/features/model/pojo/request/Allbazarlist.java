
package com.trinoq.mealmanager.features.model.pojo.request;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Allbazarlist {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("group_id")
    @Expose
    private Integer groupId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("total_amount")
    @Expose
    private Integer totalAmount;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("allpayables")
    @Expose
    private List<Allpayable> allpayables = null;
    @SerializedName("user_meal")
    @Expose
    private List<UserMeal> userMeal = null;
    @SerializedName("daily_meal_input")
    @Expose
    private List<DailyMealInput> dailyMealInput = null;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public List<Allpayable> getAllpayables() {
        return allpayables;
    }

    public void setAllpayables(List<Allpayable> allpayables) {
        this.allpayables = allpayables;
    }

    public List<UserMeal> getUserMeal() {
        return userMeal;
    }

    public void setUserMeal(List<UserMeal> userMeal) {
        this.userMeal = userMeal;
    }

    public List<DailyMealInput> getDailyMealInput() {
        return dailyMealInput;
    }

    public void setDailyMealInput(List<DailyMealInput> dailyMealInput) {
        this.dailyMealInput = dailyMealInput;
    }

}
