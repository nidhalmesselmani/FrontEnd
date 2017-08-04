package com.example.nidhal.frontend.mainclasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.nidhal.frontend.FacebookManager;
import com.example.nidhal.frontend.R;
import com.example.nidhal.frontend.TokenManager;
import com.example.nidhal.frontend.Utils;
import com.example.nidhal.frontend.entities.AccessToken;
import com.example.nidhal.frontend.entities.ApiError;
import com.example.nidhal.frontend.entities.UserResponse;
import com.example.nidhal.frontend.network.ApiService;
import com.example.nidhal.frontend.network.RetrofitBuilder;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nidhal on 12/07/2017.
 */

public class LoginActivity extends AppCompatActivity {

    //crée le tag activity
    private static final String TAG = "LoginActivity";

    Intent intent;


    @BindView(R.id.email_input)
    EditText email_input;
    @BindView(R.id.mot_passe_input)
    EditText mot_passe_input;


    ApiService service;
    ApiService serviceAuth;
    TokenManager tokenManager;

    Call<AccessToken> call;

    FacebookManager facebookManager;


    @BindView(R.id.container)
    RelativeLayout container;
    @BindView(R.id.form_container)
    LinearLayout formContainer;
    @BindView(R.id.loader)
    ProgressBar loader;


    Call<UserResponse> callUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentifier);

        //initialiser butterknife, si dans un fragment n'oublier pas de passer en parameter le vue que vous inflater
        ButterKnife.bind(this);

        //instancite service
        service = RetrofitBuilder.createService(ApiService.class);

        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        //service pour accéder a des ressources privés
        serviceAuth = RetrofitBuilder.createServiceWithAuth(ApiService.class, tokenManager);
        //instantiate facebook manager
        facebookManager = new FacebookManager(service, tokenManager);

        if (tokenManager.getToken().getAccess_token() != null) {

            startActivity(new Intent(LoginActivity.this, AcceuilActivity.class));
            finish();

        }
    }


    public void goForgotPassword(View view) {
        intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.btn_login)
    void login() {


        String email = email_input.getText().toString();
        String password = mot_passe_input.getText().toString();
        showLoading();
        call = service.login(email, password);
        //lance de maniere assynchrone
        call.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {

                Log.w(TAG, "onResponse: " + response);

                if (response.isSuccessful()) {
                    //save token
                    tokenManager.saveToken(response.body());
                    //get the aithenticated user
                    callUser = serviceAuth.getUser();
                    callUser.enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                            if (Integer.parseInt(response.body().getFirst_login()) == 0) {
                                startActivity(new Intent(LoginActivity.this, FillDrivingLicence.class));
                                finish();
                            } else if (Integer.parseInt(response.body().getFirst_login()) == 1) {
                                startActivity(new Intent(LoginActivity.this, AcceuilActivity.class));
                                finish();
                            }


                        }

                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {

                        }
                    });

/*
                    callUser.enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                         Log.d(TAG,response.body().getFirst_login());
                         /* if(Integer.parseInt(response.body().getFirst_login())== 0){
                           // startActivity(new Intent(LoginActivity.this, AcceuilActivity.class));
                           // finish();
                        }
                        }

                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {

                        }
                    });*/
                    //go to profile activity


                } else {
                    if (response.code() == 422) {
                        handleErrors(response.errorBody());
                    }
                    if (response.code() == 401) {
                        ApiError apiError = Utils.convertErrors(response.errorBody());
                        Toast.makeText(LoginActivity.this, apiError.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    showForm();

                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                Log.w(TAG, "onFailure: " + t.getMessage());
                showForm();

            }
        });
    }


    protected void onDestroy() {
        super.onDestroy();
        if (call != null) {
            call.cancel();
            call = null;
        }
        facebookManager.onDestroy();

    }


    private void handleErrors(ResponseBody response) {
        ApiError apiError = Utils.convertErrors(response);

        //Boucler le map pour afficher les erreurs
        for (Map.Entry<String, List<String>> error : apiError.getErrors().entrySet()) {
            if (error.getKey().equals("username")) {
                //recuperer le premier erreur
                email_input.setError(error.getValue().get(0));

            }
            if (error.getKey().equals("password")) {
                //recuperer le premier erreur
                mot_passe_input.setError(error.getValue().get(0));

            }
        }
    }

    @OnClick(R.id.go_to_register)
    void goToRegister() {
        startActivity(new Intent(LoginActivity.this, SignupActivity.class));
    }

    //facebook
    @OnClick(R.id.btn_facebook)
    void loginFacebook() {

        showLoading();
        facebookManager.login(this, new FacebookManager.FacebookLoginListener() {
            @Override
            public void onSuccess() {
                startActivity(new Intent(LoginActivity.this, AcceuilActivity.class));
                finish();

            }

            @Override
            public void onError(String message) {
                showForm();
                Toast.makeText(LoginActivity.this, "OnError facebookManager:" + message, Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        facebookManager.onActivityResult(requestCode, resultCode, data);
    }


    private void showLoading() {

        TransitionManager.beginDelayedTransition(container);
        formContainer.setVisibility(View.GONE);
        loader.setVisibility(View.VISIBLE);


    }

    private void showForm() {
        TransitionManager.beginDelayedTransition(container);
        formContainer.setVisibility(View.VISIBLE);
        loader.setVisibility(View.GONE);


    }


}