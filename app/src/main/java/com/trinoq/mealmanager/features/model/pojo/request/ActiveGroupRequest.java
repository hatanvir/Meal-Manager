
package com.trinoq.mealmanager.features.model.pojo.request;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActiveGroupRequest {

    @SerializedName("Success")
    @Expose
    private String success;
    @SerializedName("Active_group")
    @Expose
    private List<ActiveGroup> activeGroup = null;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<ActiveGroup> getActiveGroup() {
        return activeGroup;
    }

    public void setActiveGroup(List<ActiveGroup> activeGroup) {
        this.activeGroup = activeGroup;
    }

}
