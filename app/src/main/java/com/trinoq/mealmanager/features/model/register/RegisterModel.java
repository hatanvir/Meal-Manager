package com.trinoq.mealmanager.features.model.register;

import com.trinoq.mealmanager.common.RequestCompleteListener;
import com.trinoq.mealmanager.features.model.pojo.request.RegisterRequest;

import okhttp3.ResponseBody;

public interface RegisterModel {
    void register(RegisterRequest registerRequest, RequestCompleteListener<ResponseBody> requestCompleteListener);
}
