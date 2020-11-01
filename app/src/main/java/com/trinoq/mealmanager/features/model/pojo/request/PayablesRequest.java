
package com.trinoq.mealmanager.features.model.pojo.request;

import com.google.gson.annotations.SerializedName;

public class PayablesRequest {
    @SerializedName("group_id")
    String group_id;
    @SerializedName("electricity_gas_water")
    String electricity_gas_water;
    @SerializedName("others")
    String others;
    @SerializedName("meal_advanced")
    String meal_advanced;
    @SerializedName("meal_type")
    String meal_type;
    @SerializedName("house_rent")
    String house_rent;

    public PayablesRequest(String group_id, String electricity_gas_water,
                           String others, String meal_advanced,
                           String meal_type, String house_rent) {
        this.group_id = group_id;
        this.electricity_gas_water = electricity_gas_water;
        this.others = others;
        this.meal_advanced = meal_advanced;
        this.meal_type = meal_type;
        this.house_rent = house_rent;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getElectricity_gas_water() {
        return electricity_gas_water;
    }

    public void setElectricity_gas_water(String electricity_gas_water) {
        this.electricity_gas_water = electricity_gas_water;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getMeal_advanced() {
        return meal_advanced;
    }

    public void setMeal_advanced(String meal_advanced) {
        this.meal_advanced = meal_advanced;
    }

    public String getMeal_type() {
        return meal_type;
    }

    public void setMeal_type(String meal_type) {
        this.meal_type = meal_type;
    }

    public String getHouse_rent() {
        return house_rent;
    }

    public void setHouse_rent(String house_rent) {
        this.house_rent = house_rent;
    }
}
