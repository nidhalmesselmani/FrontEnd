package com.example.nidhal.frontend.mainclasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nidhal.frontend.FacebookManager;
import com.example.nidhal.frontend.R;
import com.example.nidhal.frontend.TokenManager;
import com.example.nidhal.frontend.entities.ApiResponse;
import com.example.nidhal.frontend.entities.PostResponse;
import com.example.nidhal.frontend.entities.UserResponse;
import com.example.nidhal.frontend.network.ApiService;
import com.example.nidhal.frontend.network.RetrofitBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nidhal on 13/07/2017.
 */





public class ProfileActivity extends AppCompatActivity {

    //
    // créer tag pour actitivity name
    private static final String TAG = "ProfileActivity";
    @BindView(R.id.post_title)
    TextView title;


    @BindView(R.id.email)
    TextView email;

    ApiService service;
    TokenManager tokenManager;
    Call<PostResponse> call;
    Call<ApiResponse> callLogout;
    Call<UserResponse> callUser;

    FacebookManager facebookManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        ButterKnife.bind(this);


        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

        //service pour accéder a des ressources privés
        service = RetrofitBuilder.createServiceWithAuth(ApiService.class, tokenManager);


        facebookManager = new FacebookManager(service, tokenManager);
        //si il n'ya pas de token
        if (tokenManager.getToken() == null) {
            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            finish();
        }

        callUser = service.getUser();
        callUser.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Log.w(TAG, "onResponseUser: " + response.body().getEmail());
                email.setText(response.body().getEmail());



            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.w(TAG, "onFailureUser: " + t.getMessage());
            }
        });

    }

/*
    @OnClick(R.id.btn_posts)
    void getPosts() {

        call = service.posts();
        call.enqueue(new Callback<PostResponse>() {


            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                Log.w(TAG, "onResponse: " + response.body().getData());

                if (response.isSuccessful()) {
                    title.setText(response.body().getData().get(0).getTitle());
                } else {

                    //delete token
                    tokenManager.deleteToken();
                    startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                    finish();


                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                Log.w(TAG, "onResponse: " + t.getMessage());

            }
        });
    }
*/


    @OnClick(R.id.modify)
    void modify(){
        startActivity(new Intent(ProfileActivity.this, FillDrivingLicence.class));
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (call != null) {
            call.cancel();
            call = null;
        }
    }


    @OnClick(R.id.btn_logout)
    void logout() {


        //delete token
        tokenManager.deleteToken();
        callLogout = service.logout();
        callLogout.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                facebookManager.clearSession();

                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });


    }


}