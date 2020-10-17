package com.trinoq.mealmanager.network;

import com.trinoq.mealmanager.features.model.pojo.request.GroupDetailsRequest;
import com.trinoq.mealmanager.features.model.pojo.request.RegisterRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {
    @POST("register")
    Call<ResponseBody> register(@Body RegisterRequest registerRequest);
    @POST("groupCreate")
    Call<ResponseBody> groupDetails(@Body GroupDetailsRequest groupDetailsRequest);

}
