package com.trinoq.mealmanager.features.model.MealInputEndTime;

import android.content.Context;

import com.trinoq.mealmanager.common.RequestCompleteListener;
import com.trinoq.mealmanager.features.model.pojo.request.DailyMealInputEndTimeRequest;
import com.trinoq.mealmanager.features.model.pojo.request.GroupCreateRequest;
import com.trinoq.mealmanager.network.Api;
import com.trinoq.mealmanager.network.RetrofitClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealInputEndTimeImpl implements MealInputEndTimeModel{
    Context context;

    public MealInputEndTimeImpl(Context context) {
        this.context = context;
    }

    @Override
    public void dailyMealInputRequest(DailyMealInputEndTimeRequest dailyMealInputEndTimeRequest, RequestCompleteListener<ResponseBody> requestCompleteListener) {
        Api api = RetrofitClient.getClient().create(Api.class);
        api.setDailyMealInput(dailyMealInputEndTimeRequest).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code() == 200) requestCompleteListener.OnSuccessListener(response.body());
                else requestCompleteListener.OnFailedListener(response.message());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                requestCompleteListener.OnFailedListener(t.getLocalizedMessage());
            }
        });
    }
}
