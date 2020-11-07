package com.trinoq.mealmanager.features.model.models;

public class GroupInformation {
    //private String groupName,phoneNumber,adminName,totalmembers,mealpricing,mealtype,cookingtype,shoppingtype,groupcreateddate;

    private String gorupId,groupName,cooksName,shoppingType,mealType,adminName,groupCreatedDate,upDate_at,totalMembers;

    public GroupInformation(String gorupId, String groupName, String cooksName, String shoppingType, String mealType, String adminName, String groupCreatedDate, String upDate_at, String totalMembers) {
        this.gorupId = gorupId;
        this.groupName = groupName;
        this.cooksName = cooksName;
        this.shoppingType = shoppingType;
        this.mealType = mealType;
        this.adminName = adminName;
        this.groupCreatedDate = groupCreatedDate;
        this.upDate_at = upDate_at;
        this.totalMembers = totalMembers;
    }

    public String getGorupId() {
        return gorupId;
    }

    public void setGorupId(String gorupId) {
        this.gorupId = gorupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCooksName() {
        return cooksName;
    }

    public void setCooksName(String cooksName) {
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

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getGroupCreatedDate() {
        return groupCreatedDate;
    }

    public void setGroupCreatedDate(String groupCreatedDate) {
        this.groupCreatedDate = groupCreatedDate;
    }

    public String getUpDate_at() {
        return upDate_at;
    }

    public void setUpDate_at(String upDate_at) {
        this.upDate_at = upDate_at;
    }

    public String getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(String totalMembers) {
        this.totalMembers = totalMembers;
    }
}
