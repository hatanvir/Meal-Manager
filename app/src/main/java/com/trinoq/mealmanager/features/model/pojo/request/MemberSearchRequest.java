
package com.trinoq.mealmanager.features.model.pojo.request;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberSearchRequest {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Member")
    @Expose
    private List<Member> member = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Member> getMember() {
        return member;
    }

    public void setMember(List<Member> member) {
        this.member = member;
    }

}
