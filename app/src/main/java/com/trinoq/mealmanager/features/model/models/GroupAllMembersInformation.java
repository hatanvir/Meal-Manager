package com.trinoq.mealmanager.features.model.models;

public class GroupAllMembersInformation {

    Integer userId;
    String phoneNumber,userName,email;

    public GroupAllMembersInformation(Integer userId, String phoneNumber, String userName, String email) {
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.email = email;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
