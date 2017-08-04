package com.example.nidhal.frontend.network;

import android.support.annotation.Nullable;

import com.example.nidhal.frontend.entities.AccessToken;
import com.example.nidhal.frontend.entities.AgencyResponse;
import com.example.nidhal.frontend.entities.ApiResponse;
import com.example.nidhal.frontend.entities.Constat;
import com.example.nidhal.frontend.entities.ConstatResponse;
import com.example.nidhal.frontend.entities.Insurance;
import com.example.nidhal.frontend.entities.InsuranceResponse;
import com.example.nidhal.frontend.entities.Police;
import com.example.nidhal.frontend.entities.PoliceResponse;
import com.example.nidhal.frontend.entities.PostResponse;
import com.example.nidhal.frontend.entities.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Nidhal on 13/07/2017.
 */

public interface ApiService {

    @POST("register")
    @FormUrlEncoded()
    Call<AccessToken> register(@Field("name") String name, @Field("email") String email, @Field("password") String password);

    @POST("login")
    @FormUrlEncoded
    Call<AccessToken> login(@Field("username") String username, @Field("password") String password);


    @GET("posts")
    Call<PostResponse> posts();


    @POST("social_auth")
    @FormUrlEncoded
    Call<AccessToken> socialAuth(@Field("name") String name,
                                 @Nullable @Field("email") String email,
                                 @Field("provider") String provider,
                                 @Field("provider_user_id") String providerUserId);


    @POST("refresh")
    @FormUrlEncoded
    Call<AccessToken> refresh(@Field("refresh_token") String refreshToken);


    @POST("logout")
    Call<ApiResponse> logout();


    @GET("user")
    Call<UserResponse> getUser();


    @POST("forgot_password")
    @FormUrlEncoded
    Call<ApiResponse> forgotPassword(@Field("email") String email);


    @POST("modify_user")
    @FormUrlEncoded
    Call<UserResponse> modifyUser(@Nullable @Field("first_name") String first_name,
                                  @Nullable @Field("last_name") String last_name,
                                  @Nullable @Field("address") String address,
                                  @Nullable @Field("postal_code") String postal_code,
                                  @Nullable @Field("province") String province,
                                  @Nullable @Field("city") String city,
                                  @Nullable @Field("license_number") String license_number
    );

    @POST("modify_user_first_login")
    @FormUrlEncoded
    Call<Void> setFirstLoginTrue(@Nullable @Field("first_login") int first_login
    );


    @GET("get_user_first_login")
    Call<UserResponse> getFirstLogin();


    @GET("get_assurances")
    Call<InsuranceResponse> getInsurances();


    @POST("get_agences")
    @FormUrlEncoded
    Call<AgencyResponse> get_agences(@Nullable @Field("id_insurance") int id_insurance);


    @POST("create_police")
    @FormUrlEncoded
    Call<Void> create_police(@Field("num_police") String num_police,
                             @Field("id_user") String id_user,
                             @Field("id_insurance") String id_insurance,
                             @Field("id_agency") String id_agency);


    @POST("insertVh")
    @FormUrlEncoded
    Call<Void> insertVh(@Field("num_police") String num_police,
                        @Field("vehicle_brand") String vehicle_brand,
                        @Field("vehicle_type") String vehicle_type,
                        @Field("serial_number_vh") String serial_number_vh);


    @POST("get_police_info")
    @FormUrlEncoded
    Call<Police> get_police_info(@Field("qrCode") String qrCode);


    @POST("post_constat")
    @FormUrlEncoded
    Call<Constat> post_constat(@Field("id_user_1") int id_user_1,
                               @Nullable @Field("id_user_2") int id_user_2,
                               @Field("id_insurance_1") int id_insurance_1,
                               @Nullable @Field("id_insurance_2") int id_insurance_2

    );
}
