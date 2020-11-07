
package com.trinoq.mealmanager.features.model.pojo.request;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BazarListRequest {

    @SerializedName("Success")
    @Expose
    private String success;
    @SerializedName("bazarlist")
    @Expose
    private List<Bazarlist> bazarlist = null;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<Bazarlist> getBazarlist() {
        return bazarlist;
    }

    public void setBazarlist(List<Bazarlist> bazarlist) {
        this.bazarlist = bazarlist;
    }

}
