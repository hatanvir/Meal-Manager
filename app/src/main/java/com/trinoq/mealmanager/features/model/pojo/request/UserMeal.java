
package com.trinoq.mealmanager.features.model.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserMeal {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("group_id")
    @Expose
    private Integer groupId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("meal_date")
    @Expose
    private String mealDate;
    @SerializedName("is_breakfast")
    @Expose
    private Integer isBreakfast;
    @SerializedName("is_lunch")
    @Expose
    private Integer isLunch;
    @SerializedName("is_dinner")
    @Expose
    private Integer isDinner;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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
