
package com.trinoq.mealmanager.features.model.pojo.request;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllBazarListSearchRequest {

    @SerializedName("Success")
    @Expose
    private String success;
    @SerializedName("Allbazarlist")
    @Expose
    private List<Allbazarlist> allbazarlist = null;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<Allbazarlist> getAllbazarlist() {
        return allbazarlist;
    }

    public void setAllbazarlist(List<Allbazarlist> allbazarlist) {
        this.allbazarlist = allbazarlist;
    }

}
