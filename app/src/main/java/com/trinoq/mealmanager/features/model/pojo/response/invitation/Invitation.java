
package com.trinoq.mealmanager.features.model.pojo.response.invitation;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Invitation {

    @SerializedName("Success")
    @Expose
    private String success;
    @SerializedName("Inviationdata")
    @Expose
    private List<Inviationdatum> inviationdata = null;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<Inviationdatum> getInviationdata() {
        return inviationdata;
    }

    public void setInviationdata(List<Inviationdatum> inviationdata) {
        this.inviationdata = inviationdata;
    }

}
