package com.trinoq.mealmanager.network;

import com.trinoq.mealmanager.features.model.pojo.request.DailyMealInputEndTimeRequest;
import com.trinoq.mealmanager.features.model.pojo.request.GroupCreateRequest;
import com.trinoq.mealmanager.features.model.pojo.request.PayablesRequest;
import com.trinoq.mealmanager.features.model.pojo.request.PostMonthRequest;
import com.trinoq.mealmanager.features.model.pojo.request.PremonthRequest;
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

    @POST("payables")
    Call<ResponseBody> setPayables(@Body PayablesRequest payablesRequest);

    @POST("preeMonthsCreate")
    Call<ResponseBody> setPreMonth(@Body PremonthRequest premonthRequest);

    @POST("postMonthsCreate")
    Call<ResponseBody> setPostMonth(@Body PostMonthRequest postMonthRequest);

    @POST("dailyMealInput")
    Call<ResponseBody> setDailyMealInput(@Body DailyMealInputEndTimeRequest dailyMealInputEndTimeRequest);
   /* @POST("preeMonthsCreate")
    Call<ResponseBody> addPreeMonth()*/

}
