
package com.trinoq.mealmanager.network;

import com.trinoq.mealmanager.features.model.fcmNotification.NotificationSender;
import com.trinoq.mealmanager.features.model.pojo.request.AllBazarListSearchRequest;
import com.trinoq.mealmanager.features.model.pojo.request.BazarInsertRequest;
import com.trinoq.mealmanager.features.model.pojo.request.BazarListRequest;
import com.trinoq.mealmanager.features.model.pojo.request.DailyMealInputEndTimeRequest;
import com.trinoq.mealmanager.features.model.pojo.request.GroupCreateRequest;
import com.trinoq.mealmanager.features.model.pojo.request.GroupMemberCreationRequest;
import com.trinoq.mealmanager.features.model.pojo.request.GroupSearchRequest;
import com.trinoq.mealmanager.features.model.pojo.request.MemberInvitation;
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
import com.trinoq.mealmanager.features.model.pojo.response.groupcreate.GroupCreatResponse;
import com.trinoq.mealmanager.features.model.pojo.response.invitation.Invitation;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Api {
    @POST("register")
    Call<ResponseBody> register(@Body RegisterRequest  registerRequest);

    @POST("groupCreate")
    Call<GroupCreatResponse> groupCreate(@Body GroupCreateRequest groupCreateRequest);

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

    //add group member
    @POST("GroupMembercreate")
    Call<ResponseBody> addGroupMember(@Body GroupMemberCreationRequest groupMemberCreationRequest);

    @Multipart
    @POST("UpdateUser/{id}")
    Call<ResponseBody> updatedUser(@Path("id") Integer id,@Part MultipartBody.Part file, @Part("email") ResponseBody email);

    @POST("BazarInsert")
    Call<ResponseBody> setBazar(@Body BazarInsertRequest bazarInsertRequest);

    @POST("usermealcreate")
    Call<ResponseBody> setUserMeall(@Body UserMealCreateRequest userMealCreateRequest);

    @GET("Group_search/{groupname}")
    Call<GroupSearchRequest> Group_search(@Path("groupname") String groupname);

    @GET("GroupMember/{group_id}")
    Call<GroupMemberSearchRequest> GroupMember(@Path("group_id") String group_id);

    @GET("GetPayables/{group_id}")
    Call<PayablesResponse> GetPayables(@Path("group_id") String group_id);
    @GET("UserInformation/{phone_number}")
    Call<UserInformationRequest> UserInformation(@Path("phone_number") String phone_number);

    @GET("Bazarlist/{group_id}/{from}/{to}")
    Call<BazarListRequest> getBazarList(@Path("group_id") String group_id, @Path("from") String from, @Path("to") String to);

    @GET("Member_search/{phone_number}")
    Call<MemberSearchRequest> getSearchMember(@Path("phone_number") String phone_number);

    @GET("GroupMember/{group_id}")
    Call<GroupMemberSearchRequest> getAllGroupMember(@Path("group_id") String group_id);

    @GET("GetAllBazarlist/{group_id}/{from}/{to}")
    Call<AllBazarListSearchRequest> getAllBazarList(@Path("group_id") String group_id, @Path("from") String from, @Path("to") String to);

    @POST("BazarUpdate/{id}")
    Call<ResponseBody> setBazarUpdated(@Path("id") String id,@Body BazarInsertRequest bazarInsertRequest);

    //member invitation
    @POST("Groupinvitation")
    Call<ResponseBody> invitation(@Body MemberInvitation memberInvitation);

    @GET("Invitationdataget/{user_id}")
    Call<Invitation> getInvitationData(@Path("user_id") String user_id);

    @POST("GroupInviationStatusUpdate/{id}")
    @FormUrlEncoded
    Call<ResponseBody> invitationUpdate(@Path("id") Integer id, @Field("status") int status);

   /* @POST("preeMonthsCreate")
    Call<ResponseBody> addPreeMonth()*/


    //firebase messaging
    @Headers({"Content-Type:application/json",
            "Authorization:key=AAAAPNmSOrk:APA91bFSf7n1w6mdMJtsqigZ4ncY_qq-qb0ZGGccsZlEYyjwE6vqDvPYw3JGXl2K9LOxd9B1NG6tFuozMYng2Nk6oasPKYt-gW9vOw0H3Oyu_C8AjBVerzGOhd5ZqftK2ZfzPjF_bMOn" // fcm server key
             }
    )

    @POST("fcm/send")
    Call<ResponseBody> sendNotifcation(@Body NotificationSender body);

}