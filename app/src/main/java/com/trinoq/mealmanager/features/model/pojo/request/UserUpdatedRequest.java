package com.trinoq.mealmanager.features.model.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserUpdatedRequest {

    @SerializedName("email")
    @Expose
    private String email = null;
    @SerializedName("image")
    @Expose
    private String image = null;

    public UserUpdatedRequest(String email, String image) {
        this.email = email;
        this.image = image;
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
}
