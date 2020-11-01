package com.trinoq.mealmanager.features.model.models;

public class GroupInformation {
    private String groupName,phoneNumber,adminName,totalmembers,mealpricing,mealtype,cookingtype,shoppingtype,groupcreateddate;

    public GroupInformation(String groupName, String phoneNumber, String adminName, String totalmembers, String mealpricing, String mealtype, String cookingtype, String shoppingtype, String groupcreateddate) {
        this.groupName = groupName;
        this.phoneNumber = phoneNumber;
        this.adminName = adminName;
        this.totalmembers = totalmembers;
        this.mealpricing = mealpricing;
        this.mealtype = mealtype;
        this.cookingtype = cookingtype;
        this.shoppingtype = shoppingtype;
        this.groupcreateddate = groupcreateddate;
    }

    /*public GroupInformation(String groupName, String phoneNumber, String adminName, String mealtype, String cookingtype, String shoppingtype, String groupcreateddate) {
        this.groupName = groupName;
        this.phoneNumber = phoneNumber;
        this.adminName = adminName;
        this.mealtype = mealtype;
        this.cookingtype = cookingtype;
        this.shoppingtype = shoppingtype;
        this.groupcreateddate = groupcreateddate;
    }*/

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getTotalmembers() {
        return totalmembers;
    }

    public void setTotalmembers(String totalmembers) {
        this.totalmembers = totalmembers;
    }

    public String getMealpricing() {
        return mealpricing;
    }

    public void setMealpricing(String mealpricing) {
        this.mealpricing = mealpricing;
    }

    public String getMealtype() {
        return mealtype;
    }

    public void setMealtype(String mealtype) {
        this.mealtype = mealtype;
    }

    public String getCookingtype() {
        return cookingtype;
    }

    public void setCookingtype(String cookingtype) {
        this.cookingtype = cookingtype;
    }

    public String getShoppingtype() {
        return shoppingtype;
    }

    public void setShoppingtype(String shoppingtype) {
        this.shoppingtype = shoppingtype;
    }

    public String getGroupcreateddate() {
        return groupcreateddate;
    }

    public void setGroupcreateddate(String groupcreateddate) {
        this.groupcreateddate = groupcreateddate;
    }
}
