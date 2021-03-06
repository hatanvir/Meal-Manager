package com.trinoq.mealmanager.features.model.Payables;

import android.content.Context;

import com.trinoq.mealmanager.common.RequestCompleteListener;
import com.trinoq.mealmanager.features.model.pojo.request.PayablesRequest;
import com.trinoq.mealmanager.network.Api;
import com.trinoq.mealmanager.network.RetrofitClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayablesModelImplementation implements PayablesModel{
    Context context;

    public PayablesModelImplementation(Context context) {
        this.context = context;
    }

    @Override
    public void payablesRequest(PayablesRequest payablesRequest, RequestCompleteListener<ResponseBody> requestCompleteListener) {
        Api api = RetrofitClient.getClient().create(Api.class);
        api.setPayables(payablesRequest)
                .enqueue(new Callback<ResponseBody>() {
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
