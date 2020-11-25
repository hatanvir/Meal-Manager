
package com.trinoq.mealmanager.features.model.pojo.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RegisterRequest implements Serializable {
    @SerializedName("phone_number")
    String phone_number;
    @SerializedName("full_name")
    String full_name;
    @SerializedName("notification_token")
    String notification_token;

    public RegisterRequest(String phone_number, String full_name, String notification_token) {
        this.phone_number = phone_number;
        this.full_name = full_name;
        this.notification_token = notification_token;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getNotification_token() {
        return notification_token;
    }
}