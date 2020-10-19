package com.trinoq.mealmanager.network;

import com.trinoq.mealmanager.features.model.pojo.request.GroupCreateRequest;
import com.trinoq.mealmanager.features.model.pojo.request.RegisterRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {
    @POST("register")
    Call<ResponseBody> register(@Body RegisterRequest registerRequest);

    @POST("groupCreate")
    Call<ResponseBody> groupCreate(@Body GroupCreateRequest groupCreateRequest);
   /* @POST("preeMonthsCreate")
    Call<ResponseBody> addPreeMonth()*/

}
