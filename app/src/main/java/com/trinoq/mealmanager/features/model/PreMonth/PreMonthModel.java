package com.trinoq.mealmanager.features.model.PreMonth;

import com.trinoq.mealmanager.common.RequestCompleteListener;
import com.trinoq.mealmanager.features.model.pojo.request.PostMonthRequest;
import com.trinoq.mealmanager.features.model.pojo.request.PremonthRequest;

import okhttp3.ResponseBody;

public interface PreMonthModel {
    void postMostMonthRequest(PremonthRequest premonthRequest, RequestCompleteListener<ResponseBody> requestCompleteListener);
}
