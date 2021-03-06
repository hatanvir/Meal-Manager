package com.trinoq.mealmanager.features.model.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BazarInsertRequest {

    @SerializedName("group_id")
    @Expose
    private String groupId = null;
    @SerializedName("user_id")
    @Expose
    private String userId = null;
    @SerializedName("total_amount")
    @Expose
    private String totalAmount = null;
    @SerializedName("date")
    @Expose
    private String date = null;
   /*
    @SerializedName("group_id")
    @Expose
    private String groupId = null;
    @SerializedName("user_id")
    @Expose
    private String userId = null;
    @SerializedName("total_amount")
    @Expose
    private String totalAmount = null;
    @SerializedName("date")
    @Expose
    private String date = null;*/

    public BazarInsertRequest(String groupId, String userId, String totalAmount, String date) {
        this.groupId = groupId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.date = date;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
