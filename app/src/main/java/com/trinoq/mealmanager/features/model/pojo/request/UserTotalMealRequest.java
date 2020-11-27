
package com.trinoq.mealmanager.features.model.pojo.request;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserTotalMealRequest {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("Date_Meal")
    @Expose
    private List<DateMeal> dateMeal = null;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<DateMeal> getDateMeal() {
        return dateMeal;
    }

    public void setDateMeal(List<DateMeal> dateMeal) {
        this.dateMeal = dateMeal;
    }

}
