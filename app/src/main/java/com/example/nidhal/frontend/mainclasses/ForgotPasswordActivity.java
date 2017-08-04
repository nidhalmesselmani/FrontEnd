package com.example.nidhal.frontend.mainclasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nidhal.frontend.R;
import com.example.nidhal.frontend.entities.ApiResponse;
import com.example.nidhal.frontend.network.ApiService;
import com.example.nidhal.frontend.network.RetrofitBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nidhal on 12/07/2017.
 */

public class ForgotPasswordActivity  extends AppCompatActivity {
    Intent LoginIntent;
    ApiService service;

    //crée le tag activity
    private static final String TAG = "ForgotPasswordActivity";
    Call<ApiResponse> forgotPasswordCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpassword);
        ButterKnife.bind(this);

        //instancite service
        service = RetrofitBuilder.createService(ApiService.class);


    }


    @BindView(R.id.email_input)
    EditText email_input;




    @OnClick(R.id.btn_submit_email)
    void submit_email(){


        Toast.makeText(this,"clicked",Toast.LENGTH_SHORT).show();
        String email = email_input.getText().toString();
        Toast.makeText(this,email,Toast.LENGTH_SHORT).show();
        Log.w(TAG,"clicked");
        forgotPasswordCall = service.forgotPassword(email);
        forgotPasswordCall.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.d(TAG,response.body().toString());
                Toast.makeText(ForgotPasswordActivity.this,response.body().toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });



    }

    public void goToLogin(View view) {
        LoginIntent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
        startActivity(LoginIntent);

    }

    public void submit_email(View view) {
        Log.w(TAG,"clicked");

        String email = email_input.getText().toString();
        Toast.makeText(this,email,Toast.LENGTH_SHORT).show();
    }
}
