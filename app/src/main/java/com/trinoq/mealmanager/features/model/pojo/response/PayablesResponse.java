package com.trinoq.mealmanager.features.model.pojo.response;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.trinoq.mealmanager.features.model.pojo.request.Payable;

public class PayablesResponse {
        @SerializedName("success")
        @Expose
        private String success;
        @SerializedName("payables")
        @Expose
        private List<Payable> payables = null;
        @SerializedName("totalPayables")
        @Expose
        private Integer totalPayables;

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }

        public List<Payable> getPayables() {
            return payables;
        }

        public void setPayables(List<Payable> payables) {
            this.payables = payables;
        }

        public Integer getTotalPayables() {
            return totalPayables;
        }

        public void setTotalPayables(Integer totalPayables) {
            this.totalPayables = totalPayables;
        }
}
