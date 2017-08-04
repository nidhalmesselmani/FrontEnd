package com.example.nidhal.frontend.mainclasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.nidhal.frontend.R;
import com.example.nidhal.frontend.TokenManager;
import com.example.nidhal.frontend.Utils;
import com.example.nidhal.frontend.entities.AccessToken;
import com.example.nidhal.frontend.entities.ApiError;
import com.example.nidhal.frontend.network.ApiService;
import com.example.nidhal.frontend.network.RetrofitBuilder;
import com.google.common.base.Converter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Nidhal on 12/07/2017.
 */

public class SignupActivity extends AppCompatActivity {
    Intent LoginIntent;

    //crée le tag Actitivity
    private static final String TAG = "SignupActivity";


    @BindView(R.id.email_input)
    EditText email_input;
    @BindView(R.id.password_input)
    EditText password_input;

    @BindView(R.id.name_input)
    EditText name_input;


    ApiService service;
    Call<AccessToken> call;


    //AwesomeValidation validator;
     TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscrire);

        //initialiser butterknife, si dans un fragment n'oublier pas de passer en parameter le vue que vous inflater
        ButterKnife.bind(this);
        //instancite service
        service = RetrofitBuilder.createService(ApiService.class);

        //instantiate validator
        //validator = new AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT);
        //set Rules
   //     setupRules();

        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));

         if(tokenManager.getToken().getAccess_token() != null){

             startActivity(new Intent(SignupActivity.this,AcceuilActivity.class));
             finish();

         }

    }

    @OnClick(R.id.btn_register)
    void register() {

        String email = email_input.getText().toString();
        String password = password_input.getText().toString();
        String name = name_input.getText().toString();

Toast.makeText(SignupActivity.this,email,Toast.LENGTH_SHORT).show();
       // email_input.setError(null);
     //   mot_passe_input.setError(null);

        //si ona un message d'erreur, clear validator

//        validator.clear();

        //si c bn on faire le requete
       // if(validator.validate()) {
            call = service.register(name, email, password);
            //faire de maniere asynchrone
            call.enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {

                    Log.w(TAG, "onResponse: " + response);

                    //test si la reponse correspond a une response dans [200-300]
                    if (response.isSuccessful()) {
                        Log.w(TAG,"onResponse"+response.body());
                             tokenManager.saveToken(response.body());

                        startActivity(new Intent(SignupActivity.this,FillDrivingLicence.class));
                        finish();
                     } else {
                        handleErrors(response.errorBody());

                    }

                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {
                    Log.w(TAG, "onFailure: " + t.getMessage());

                }
            });
       // }
    }

/*
    public void goToLogin(View view) {
        LoginIntent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(LoginIntent);
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (call != null) {
            call.cancel();
            call = null;
        }
    }


    private void handleErrors(ResponseBody response) {
        ApiError apiError = Utils.convertErrors(response);

        //Boucler le map pour afficher les erreurs
        for (Map.Entry<String, List<String>> error : apiError.getErrors().entrySet()) {
            if (error.getKey().equals("email")) {
                //recuperer le premier erreur
                email_input.setError(error.getValue().get(0));

            }
            if (error.getKey().equals("password")) {
                //recuperer le premier erreur
                password_input.setError(error.getValue().get(0));

            }
        }
    }


    //definir validation rules
    public void setupRules() {
        //  RegexTemplate.NOT_EMPTY, pour n'est pas null
    /*    //il faut être un email valid
        validator.addValidation(this, R.id.email_input, Patterns.EMAIL_ADDRESS, R.string.err_email);
        //lettre chiffre et au minimum 6 character
        validator.addValidation(this, R.id.mot_passe_input, "[a-zA-Z0-9]{6,}", R.string.err_password);
*/
    }


    @OnClick(R.id.go_to_login)
    void goToLogin(){
        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
    }
}
