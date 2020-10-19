package com.trinoq.mealmanager.features.model.pojo.request;

import com.google.gson.annotations.SerializedName;

public class GroupCreateRequest {
    @SerializedName("group_name")
    String group_name;
    @SerializedName("address")
    String address;
    @SerializedName("cooks_name")
    String cooks_name;
    @SerializedName("shopping_type")
    String shopping_type;
    @SerializedName("meal_type")
    String meal_type;
    @SerializedName("is_admin")
    String is_admin;

    public GroupCreateRequest(String group_name,
                              String address, String cooks_name,
                              String shopping_type, String meal_type, String is_admin) {
        this.group_name = group_name;
        this.address = address;
        this.cooks_name = cooks_name;
        this.shopping_type = shopping_type;
        this.meal_type = meal_type;
        this.is_admin = is_admin;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCooks_name() {
        return cooks_name;
    }

    public void setCooks_name(String cooks_name) {
        this.cooks_name = cooks_name;
    }

    public String getShopping_type() {
        return shopping_type;
    }

    public void setShopping_type(String shopping_type) {
        this.shopping_type = shopping_type;
    }

    public String getMeal_type() {
        return meal_type;
    }

    public void setMeal_type(String meal_type) {
        this.meal_type = meal_type;
    }

    public String getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(String is_admin) {
        this.is_admin = is_admin;
    }
}
