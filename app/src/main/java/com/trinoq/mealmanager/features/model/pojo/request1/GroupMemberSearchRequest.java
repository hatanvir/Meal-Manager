
package com.trinoq.mealmanager.features.model.pojo.request1;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupMemberSearchRequest {

    @SerializedName("GroupMembers")
    @Expose
    private List<GroupMember> groupMembers = null;

    public List<GroupMember> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<GroupMember> groupMembers) {
        this.groupMembers = groupMembers;
    }

}
