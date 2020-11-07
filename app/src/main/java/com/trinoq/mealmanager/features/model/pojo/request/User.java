
package com.trinoq.mealmanager.features.model.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("notification_token")
    @Expose
    private Object notificationToken;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("active_groupid")
    @Expose
    private Object activeGroupid;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Object getNotificationToken() {
        return notificationToken;
    }

    public void setNotificationToken(Object notificationToken) {
        this.notificationToken = notificationToken;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public Object getActiveGroupid() {
        return activeGroupid;
    }

    public void setActiveGroupid(Object activeGroupid) {
        this.activeGroupid = activeGroupid;
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
