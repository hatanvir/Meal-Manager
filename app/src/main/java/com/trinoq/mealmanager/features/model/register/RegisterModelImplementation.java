package com.trinoq.mealmanager.features.model.register;

import android.content.Context;
import android.util.Log;

import com.trinoq.mealmanager.common.RequestCompleteListener;
import com.trinoq.mealmanager.features.model.pojo.request.RegisterRequest;
import com.trinoq.mealmanager.features.view.fragments.SignUpFragment;
import com.trinoq.mealmanager.network.Api;
import com.trinoq.mealmanager.network.RetrofitClient;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterModelImplementation implements RegisterModel {
    Context context;
    public RegisterModelImplementation(Context context) {
        this.context = context;
    }

    @Override
    public void register(RegisterRequest registerRequest, RequestCompleteListener<ResponseBody> requestCompleteListener) {
        Log.d("tttt", "called");
        Api api = RetrofitClient.getClient().create(Api.class);
        api.register(registerRequest)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d("tttt", String.valueOf(response.code()));
                        if(response.code() == 200) requestCompleteListener.OnSuccessListener(response.body());
                        requestCompleteListener.OnFailedListener("Phone number already taken");
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        requestCompleteListener.OnFailedListener(t.getLocalizedMessage());
                    }
                });
    }
}
