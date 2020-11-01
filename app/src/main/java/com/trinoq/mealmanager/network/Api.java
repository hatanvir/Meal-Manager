
package com.trinoq.mealmanager.network;

import com.trinoq.mealmanager.features.model.pojo.request.DailyMealInputEndTimeRequest;
import com.trinoq.mealmanager.features.model.pojo.request.GroupCreateRequest;
import com.trinoq.mealmanager.features.model.pojo.request.PayablesRequest;
import com.trinoq.mealmanager.features.model.pojo.request.PayablesUpdateRequest;
import com.trinoq.mealmanager.features.model.pojo.request.PostMonthRequest;
import com.trinoq.mealmanager.features.model.pojo.request.PremonthRequest;
import com.trinoq.mealmanager.features.model.pojo.request.RegisterRequest;
import com.trinoq.mealmanager.features.model.pojo.request.SearchGroupRequest;
import com.trinoq.mealmanager.features.model.pojo.request1.GroupMemberSearchRequest;
import com.trinoq.mealmanager.features.model.pojo.response.PayablesResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    @POST("register")
    Call<ResponseBody> register(@Body RegisterRequest  registerRequest);

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

    @POST("UpdatePayables/{id}")
    Call<ResponseBody> updatePayable(@Path("id") String id, @Body PayablesUpdateRequest payablesUpdateRequest);

    @GET("Group_search/{groupname}")
    Call<SearchGroupRequest> Group_search(@Path("groupname") String groupname);

    @GET("GroupMember/{group_id}")
    Call<GroupMemberSearchRequest> GroupMember(@Path("group_id") String group_id);

    @GET("GetPayables/{group_id}")
    Call<PayablesResponse> GetPayables(@Path("group_id") String group_id);
   /* @POST("preeMonthsCreate")
    Call<ResponseBody> addPreeMonth()*/

}