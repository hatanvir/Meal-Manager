package com.trinoq.mealmanager.features.model.pojo.request;

import com.google.gson.annotations.SerializedName;

public class GroupMemberCreationRequest {
    @SerializedName("group_id")
    String group_id;
    @SerializedName("phone_number")
    String phone_number;

    public GroupMemberCreationRequest(String group_id, String phone_number) {
        this.group_id = group_id;
        this.phone_number = phone_number;
    }

    public String getGroup_id() {
        return group_id;
    }

    public String getPhone_number() {
        return phone_number;
    }
}
