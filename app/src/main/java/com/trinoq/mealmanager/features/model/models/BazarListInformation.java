package com.trinoq.mealmanager.features.model.models;

public class BazarListInformation {
    private Integer id,groupId,userId,totalAmount;
    private String extraBazar,createdAt,updatedAt;

    public BazarListInformation(Integer id, Integer groupId, Integer userId, Integer totalAmount, String extraBazar, String createdAt, String updatedAt) {
        this.id = id;
        this.groupId = groupId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.extraBazar = extraBazar;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getExtraBazar() {
        return extraBazar;
    }

    public void setExtraBazar(String extraBazar) {
        this.extraBazar = extraBazar;
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
