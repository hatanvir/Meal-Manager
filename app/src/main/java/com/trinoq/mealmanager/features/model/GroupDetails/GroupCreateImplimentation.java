package com.trinoq.mealmanager.features.model.GroupDetails;

import android.content.Context;

import com.trinoq.mealmanager.common.RequestCompleteListener;
import com.trinoq.mealmanager.features.model.pojo.request.GroupCreateRequest;
import com.trinoq.mealmanager.features.model.pojo.response.groupcreate.GroupCreatResponse;
import com.trinoq.mealmanager.network.Api;
import com.trinoq.mealmanager.network.RetrofitClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupCreateImplimentation implements GroupCreateModel {
    Context context;

    public GroupCreateImplimentation(Context context) {
        this.context = context;
    }

    @Override
    public void groupDetailsRequest(GroupCreateRequest groupCreateRequest,
                                    RequestCompleteListener<GroupCreatResponse> requestCompleteListener) {
        Api api = RetrofitClient.getClient().create(Api.class);

        api.groupCreate(groupCreateRequest).enqueue(new Callback<GroupCreatResponse>() {
            @Override
            public void onResponse(retrofit2.Call<GroupCreatResponse> call, Response<GroupCreatResponse> response) {
                if(response.code() == 200) requestCompleteListener.OnSuccessListener(response.body());
                else  requestCompleteListener.OnFailedListener(response.message());
            }

            @Override
            public void onFailure(Call<GroupCreatResponse> call, Throwable t) {
                requestCompleteListener.OnFailedListener(t.getLocalizedMessage());
            }
        });
    }
}
