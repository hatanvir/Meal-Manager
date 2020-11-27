package com.trinoq.mealmanager.features.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.trinoq.mealmanager.common.RequestCompleteListener;
import com.trinoq.mealmanager.features.model.GroupDetails.GroupCreateModel;
import com.trinoq.mealmanager.features.model.pojo.request.GroupCreateRequest;
import com.trinoq.mealmanager.features.model.pojo.response.groupcreate.GroupCreatResponse;

import okhttp3.ResponseBody;

public class GroupDetailsViewmodel extends ViewModel {
    public MutableLiveData<GroupCreatResponse> groupDetailsRequestSuccess = new MutableLiveData<>();
    public MutableLiveData<String> groupDetailsRequestFailed = new MutableLiveData<>();

    public void groupDetailsRequest(GroupCreateRequest groupCreateRequest, GroupCreateModel model){
        model.groupDetailsRequest(groupCreateRequest, new RequestCompleteListener<GroupCreatResponse>() {
            @Override
            public void OnSuccessListener(GroupCreatResponse data) {
                groupDetailsRequestSuccess.postValue(data);
            }

            @Override
            public void OnFailedListener(String error) {
                groupDetailsRequestFailed.postValue(error);
            }
        });
    }
}
