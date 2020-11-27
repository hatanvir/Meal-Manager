
package com.trinoq.mealmanager.features.model.pojo.response.invitation;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Inviationdatum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("group_id")
    @Expose
    private Integer groupId;
    @SerializedName("sender_id")
    @Expose
    private Integer senderId;
    @SerializedName("receiver_id")
    @Expose
    private Integer receiverId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("groupinfo")
    @Expose
    private List<Groupinfo> groupinfo = null;
    @SerializedName("sender_info")
    @Expose
    private List<SenderInfo> senderInfo = null;
    @SerializedName("receiver_info")
    @Expose
    private List<ReceiverInfo> receiverInfo = null;

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

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public List<Groupinfo> getGroupinfo() {
        return groupinfo;
    }

    public void setGroupinfo(List<Groupinfo> groupinfo) {
        this.groupinfo = groupinfo;
    }

    public List<SenderInfo> getSenderInfo() {
        return senderInfo;
    }

    public void setSenderInfo(List<SenderInfo> senderInfo) {
        this.senderInfo = senderInfo;
    }

    public List<ReceiverInfo> getReceiverInfo() {
        return receiverInfo;
    }

    public void setReceiverInfo(List<ReceiverInfo> receiverInfo) {
        this.receiverInfo = receiverInfo;
    }

}
