
package com.trinoq.mealmanager.network;

import com.trinoq.mealmanager.features.model.pojo.request.AllBazarListSearchRequest;
import com.trinoq.mealmanager.features.model.pojo.request.BazarInsertRequest;
import com.trinoq.mealmanager.features.model.pojo.request.BazarListRequest;
import com.trinoq.mealmanager.features.model.pojo.request.DailyMealInputEndTimeRequest;
import com.trinoq.mealmanager.features.model.pojo.request.GroupCreateRequest;
import com.trinoq.mealmanager.features.model.pojo.request.GroupSearchRequest;
import com.trinoq.mealmanager.features.model.pojo.request.MemberSearchRequest;
import com.trinoq.mealmanager.features.model.pojo.request.PayablesRequest;
import com.trinoq.mealmanager.features.model.pojo.request.PayablesUpdateRequest;
import com.trinoq.mealmanager.features.model.pojo.request.PostMonthRequest;
import com.trinoq.mealmanager.features.model.pojo.request.PremonthRequest;
import com.trinoq.mealmanager.features.model.pojo.request.RegisterRequest;
import com.trinoq.mealmanager.features.model.pojo.request.UserInformationRequest;
import com.trinoq.mealmanager.features.model.pojo.request.UserMealCreateRequest;
import com.trinoq.mealmanager.features.model.pojo.request1.GroupMemberSearchRequest;
import com.trinoq.mealmanager.features.model.pojo.response.PayablesResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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

    @Multipart
    @POST("UpdateUser/{id}")
    Call<ResponseBody> updatedUser(@Path("id") Integer id,@Part MultipartBody.Part file, @Part("email") ResponseBody email);

    @POST("BazarInsert")
    Call<ResponseBody> setBazar(@Body BazarInsertRequest bazarInsertRequest);

    @POST("BazarUpdate/{id}")
    Call<ResponseBody> setBazarUpdated(@Path("id") String id,@Body BazarInsertRequest bazarInsertRequest);

    @POST("usermealcreate")
    Call<ResponseBody> setUserMeall(@Body UserMealCreateRequest userMealCreateRequest);

    @GET("Group_search/{groupname}")
    Call<GroupSearchRequest> Group_search(@Path("groupname") String groupname);

    @GET("GroupMember/{group_id}")
    Call<GroupMemberSearchRequest> getAllGroupMember(@Path("group_id") String group_id);

    @GET("GetPayables/{group_id}")
    Call<PayablesResponse> GetPayables(@Path("group_id") String group_id);
    @GET("UserInformation/{phone_number}")
    Call<UserInformationRequest> UserInformation(@Path("phone_number") String phone_number);

    @GET("Bazarlist/{group_id}/{from}/{to}")
    Call<BazarListRequest> getBazarList(@Path("group_id") String group_id, @Path("from") String from, @Path("to") String to);

    @GET("Member_search/{phone_number}")
    Call<MemberSearchRequest> getSearchMember(@Path("phone_number") String phone_number);

    @GET("GetAllBazarlist/{group_id}/{from}/{to}")
    Call<AllBazarListSearchRequest> getAllBazarList(@Path("group_id") String group_id, @Path("from") String from, @Path("to") String to);


   /* @POST("preeMonthsCreate")
    Call<ResponseBody> addPreeMonth()*/

}