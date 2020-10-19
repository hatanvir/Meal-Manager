package com.trinoq.mealmanager.features.model.GroupDetails;

import com.trinoq.mealmanager.common.RequestCompleteListener;
import com.trinoq.mealmanager.features.model.pojo.request.GroupCreateRequest;

import okhttp3.ResponseBody;

public interface GroupCreateModel {
    void groupDetailsRequest(GroupCreateRequest groupCreateRequest, RequestCompleteListener<ResponseBody> requestCompleteListener);
}
