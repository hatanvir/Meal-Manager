
package com.trinoq.mealmanager.features.model.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("group_id")
    @Expose
    private Integer groupId;
    @SerializedName("electricity_gas_water")
    @Expose
    private Integer electricityGasWater;
    @SerializedName("others")
    @Expose
    private Integer others;
    @SerializedName("meal_advanced")
    @Expose
    private Integer mealAdvanced;
    @SerializedName("house_rent")
    @Expose
    private Integer houseRent;
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

    public Integer getElectricityGasWater() {
        return electricityGasWater;
    }

    public void setElectricityGasWater(Integer electricityGasWater) {
        this.electricityGasWater = electricityGasWater;
    }

    public Integer getOthers() {
        return others;
    }

    public void setOthers(Integer others) {
        this.others = others;
    }

    public Integer getMealAdvanced() {
        return mealAdvanced;
    }

    public void setMealAdvanced(Integer mealAdvanced) {
        this.mealAdvanced = mealAdvanced;
    }

    public Integer getHouseRent() {
        return houseRent;
    }

    public void setHouseRent(Integer houseRent) {
        this.houseRent = houseRent;
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
