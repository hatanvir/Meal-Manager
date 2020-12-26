package com.trinoq.mealmanager.features.model.PreMonth;

import android.content.Context;
import android.util.Log;

import com.trinoq.mealmanager.common.RequestCompleteListener;
import com.trinoq.mealmanager.features.model.pojo.request.PremonthRequest;
import com.trinoq.mealmanager.network.Api;
import com.trinoq.mealmanager.network.RetrofitClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreMonthModelImplementation implements PreMonthModel{
    Context context;

    public PreMonthModelImplementation(Context context) {
        this.context = context;
    }
    @Override
    public void preeMonthRequest(PremonthRequest premonthRequest, RequestCompleteListener<ResponseBody> requestCompleteListener) {
        Api api = RetrofitClient.getClient().create(Api.class);
        api.setPreMonth(premonthRequest).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("tttPostMoth",""+response.code());
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
