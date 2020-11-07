package com.trinoq.mealmanager.features.model.models;

public class UserInformation {

    private String userId,phoneNumber,notificationToken,userName,email,image,activeGroupId,created_at,updated_at;

    public UserInformation(String userId, String phoneNumber, String notificationToken, String userName, String email, String image, String activeGroupId, String created_at, String updated_at) {
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.notificationToken = notificationToken;
        this.userName = userName;
        this.email = email;
        this.image = image;
        this.activeGroupId = activeGroupId;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotificationToken() {
        return notificationToken;
    }

    public void setNotificationToken(String notificationToken) {
        this.notificationToken = notificationToken;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getActiveGroupId() {
        return activeGroupId;
    }

    public void setActiveGroupId(String activeGroupId) {
        this.activeGroupId = activeGroupId;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
