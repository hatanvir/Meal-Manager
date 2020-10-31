
package com.trinoq.mealmanager.features.model.pojo.request;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PayablesUpdateRequest {

    @SerializedName("group_id")
    @Expose
    private String groupId = null;
    @SerializedName("electricity_gas_water")
    @Expose
    private String electricityGasWater = null;
    @SerializedName("others")
    @Expose
    private String others = null;
    @SerializedName("meal_advanced")
    @Expose
    private String mealAdvanced = null;
    @SerializedName("house_rent")
    @Expose
    private String houseRent = null;

    public PayablesUpdateRequest(String groupId, String electricityGasWater, String others, String mealAdvanced, String houseRent) {
        this.groupId = groupId;
        this.electricityGasWater = electricityGasWater;
        this.others = others;
        this.mealAdvanced = mealAdvanced;
        this.houseRent = houseRent;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getElectricityGasWater() {
        return electricityGasWater;
    }

    public void setElectricityGasWater(String electricityGasWater) {
        this.electricityGasWater = electricityGasWater;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getMealAdvanced() {
        return mealAdvanced;
    }

    public void setMealAdvanced(String mealAdvanced) {
        this.mealAdvanced = mealAdvanced;
    }

    public String getHouseRent() {
        return houseRent;
    }

    public void setHouseRent(String houseRent) {
        this.houseRent = houseRent;
    }
}
