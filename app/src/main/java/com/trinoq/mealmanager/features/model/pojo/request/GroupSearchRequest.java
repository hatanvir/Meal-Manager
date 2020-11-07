
package com.trinoq.mealmanager.features.model.pojo.request;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupSearchRequest {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("groupsearch")
    @Expose
    private List<Groupsearch> groupsearch = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Groupsearch> getGroupsearch() {
        return groupsearch;
    }

    public void setGroupsearch(List<Groupsearch> groupsearch) {
        this.groupsearch = groupsearch;
    }

}
