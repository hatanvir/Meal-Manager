package com.trinoq.mealmanager.features.model.PostMonth;

import com.trinoq.mealmanager.common.RequestCompleteListener;
import com.trinoq.mealmanager.features.model.pojo.request.PostMonthRequest;

import okhttp3.ResponseBody;

public interface PostMonthModel {
    void postMostMonthRequest(PostMonthRequest postMonthRequest, RequestCompleteListener<ResponseBody> requestCompleteListener);
}
