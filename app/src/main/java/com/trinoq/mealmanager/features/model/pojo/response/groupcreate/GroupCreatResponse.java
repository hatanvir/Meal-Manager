
package com.trinoq.mealmanager.features.model.pojo.response.groupcreate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupCreatResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("group")
    @Expose
    private Group group;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
