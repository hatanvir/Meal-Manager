
package com.trinoq.mealmanager.features.model.pojo.request;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DailyMealInputTimeRequest {

    @SerializedName("Success")
    @Expose
    private String success;
    @SerializedName("Dailymealinputdata")
    @Expose
    private List<Dailymealinputdatum> dailymealinputdata = null;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<Dailymealinputdatum> getDailymealinputdata() {
        return dailymealinputdata;
    }

    public void setDailymealinputdata(List<Dailymealinputdatum> dailymealinputdata) {
        this.dailymealinputdata = dailymealinputdata;
    }

}
