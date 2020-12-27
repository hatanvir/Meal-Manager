package com.trinoq.mealmanager.features.model.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberInvitation {
    @SerializedName("group_id")
    @Expose
    private Integer group_id;

    @SerializedName("sender_id")
    @Expose
    private Integer sender_id;

    @SerializedName("receiver_id")
    @Expose
    private Integer receiver_id;

    @SerializedName("date")
    String date;

    public MemberInvitation(Integer group_id, Integer sender_id, Integer receiver_id, String date) {
        this.group_id = group_id;
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.date = date;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public Integer getSender_id() {
        return sender_id;
    }

    public Integer getReceiver_id() {
        return receiver_id;
    }

    public String getDate() {
        return date;
    }
}
