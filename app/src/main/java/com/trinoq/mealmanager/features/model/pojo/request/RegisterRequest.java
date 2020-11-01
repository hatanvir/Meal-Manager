
package com.trinoq.mealmanager.features.model.pojo.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RegisterRequest implements Serializable {
    @SerializedName("phone_number")
    String phone_number;
    @SerializedName("full_name")
    String full_name;

    public RegisterRequest(String phone_number, String full_name) {
        this.phone_number = phone_number;
        this.full_name = full_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
}