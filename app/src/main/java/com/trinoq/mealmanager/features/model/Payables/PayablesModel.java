package com.trinoq.mealmanager.features.model.Payables;

import com.trinoq.mealmanager.common.RequestCompleteListener;
import com.trinoq.mealmanager.features.model.pojo.request.PayablesRequest;

import okhttp3.ResponseBody;

public interface PayablesModel {
    void payablesRequest(PayablesRequest payablesRequest, RequestCompleteListener<ResponseBody> requestCompleteListener);
}
