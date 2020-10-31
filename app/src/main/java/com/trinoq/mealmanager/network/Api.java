package com.trinoq.mealmanager.network;

import com.trinoq.mealmanager.features.model.pojo.request.GroupDetailsRequest;
import com.trinoq.mealmanager.features.model.pojo.request.GroupMember;
import com.trinoq.mealmanager.features.model.pojo.request.PayablesRequest;
import com.trinoq.mealmanager.features.model.pojo.request.PayablesUpdateRequest;
import com.trinoq.mealmanager.features.model.pojo.request.RegisterRequest;
import com.trinoq.mealmanager.features.model.pojo.request.SearchGroupRequest;
import com.trinoq.mealmanager.features.model.pojo.request1.GroupMemberSearchRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Api {
    @POST("register")
    Call<ResponseBody> register(@Body RegisterRequest registerRequest);
    @POST("groupCreate")
    Call<ResponseBody> groupDetails(@Body GroupDetailsRequest groupDetailsRequest);

    @POST("UpdatePayables/{id}")
    Call<ResponseBody> updatePayable(@Path("id") String id,@Body PayablesUpdateRequest payablesUpdateRequest);

    @GET("Group_search/{groupname}")
    Call<SearchGroupRequest> Group_search(@Path("groupname") String groupname);

    @GET("GroupMember/{group_id}")
    Call<GroupMemberSearchRequest> GroupMember(@Path("group_id") String group_id);

    @GET("GetPayables/{group_id}")
    Call<PayablesRequest> GetPayables(@Path("group_id") String group_id);

}
