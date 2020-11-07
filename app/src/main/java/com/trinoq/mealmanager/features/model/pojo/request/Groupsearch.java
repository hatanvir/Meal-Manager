
package com.trinoq.mealmanager.features.model.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Groupsearch {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("group_name")
    @Expose
    private String groupName;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("cooks_name")
    @Expose
    private Object cooksName;
    @SerializedName("shopping_type")
    @Expose
    private String shoppingType;
    @SerializedName("meal_type")
    @Expose
    private String mealType;
    @SerializedName("is_admin")
    @Expose
    private Object isAdmin;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("groupmember_count")
    @Expose
    private Integer groupmemberCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getCooksName() {
        return cooksName;
    }

    public void setCooksName(Object cooksName) {
        this.cooksName = cooksName;
    }

    public String getShoppingType() {
        return shoppingType;
    }

    public void setShoppingType(String shoppingType) {
        this.shoppingType = shoppingType;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public Object getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Object isAdmin) {
        this.isAdmin = isAdmin;
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

    public Integer getGroupmemberCount() {
        return groupmemberCount;
    }

    public void setGroupmemberCount(Integer groupmemberCount) {
        this.groupmemberCount = groupmemberCount;
    }

}
