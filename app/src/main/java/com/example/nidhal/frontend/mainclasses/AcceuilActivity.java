package com.example.nidhal.frontend.mainclasses;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.nidhal.frontend.R;
import com.example.nidhal.frontend.TokenManager;
import com.example.nidhal.frontend.entities.UserResponse;
import com.example.nidhal.frontend.fragments.AcceuilFragment;
import com.example.nidhal.frontend.fragments.HistoriqueFragment;
import com.example.nidhal.frontend.fragments.ProfileFragement;
import com.example.nidhal.frontend.network.ApiService;
import com.example.nidhal.frontend.network.RetrofitBuilder;
import com.example.nidhal.frontend.subclasses.MyApp;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AcceuilActivity extends AppCompatActivity {


    ApiService service;

    TokenManager tokenManager;
    Call<UserResponse> callUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get an instance of the token manager
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

        //service pour accéder a des ressources privés
        service = RetrofitBuilder.createServiceWithAuth(ApiService.class, tokenManager);


        callUser = service.getUser();



        callUser.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    if (Integer.parseInt(response.body().getFirst_login()) == 0) {
                        startActivity(new Intent(AcceuilActivity.this, FillDrivingLicence.class));
                    }
                    }

                else
                {
                    //delete token
                    tokenManager.deleteToken();
                    startActivity(new Intent(AcceuilActivity.this, LoginActivity.class));

                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
        setContentView(R.layout.activity_acceuil);
        MyApp.mBottomBar = (BottomBar) findViewById(R.id.bottomBar);

        MyApp.mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {

               switch (tabId){
                   case R.id.tab_historique:
                       HistoriqueFragment fH = new HistoriqueFragment();
                       getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer,fH).commit();
                       break;
                   case R.id.tab_acceuil:
                       AcceuilFragment fA = new AcceuilFragment();
                       getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer,fA).commit();
                       break;
                   case R.id.tab_profile:
                       ProfileFragement fP = new ProfileFragement();
                       Toast.makeText(getApplicationContext(),"profile",Toast.LENGTH_SHORT).show();
                       getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer,fP).commit();
                       break;
               }


            }
        });




    }



}
