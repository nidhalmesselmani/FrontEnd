package com.example.nidhal.frontend.middleClassses;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.example.nidhal.frontend.TokenManager;
import com.example.nidhal.frontend.entities.UserResponse;
import com.example.nidhal.frontend.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nidhal on 27/07/2017.
 */

public class path extends AppCompatActivity {


    ApiService service;
    TokenManager tokenManager;
    Call<UserResponse> callUser;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //get the aithenticated user
        callUser = service.getUser();
        callUser.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {


                         /*   if(Integer.parseInt(response.body().getEmail())== 0) {
                                startActivity(new Intent(LoginActivity.this, AcceuilActivity.class));
                                finish();
                            }*/
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });


    }
}
