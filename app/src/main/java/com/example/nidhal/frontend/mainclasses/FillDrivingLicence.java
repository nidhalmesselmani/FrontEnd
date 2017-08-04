package com.example.nidhal.frontend.mainclasses;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.transition.TransitionManager;

import android.support.v7.app.AppCompatActivity;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.nidhal.frontend.R;
import com.example.nidhal.frontend.TokenManager;
import com.example.nidhal.frontend.entities.UserResponse;
import com.example.nidhal.frontend.fragments.FillUserInfoFragment;
import com.example.nidhal.frontend.network.ApiService;
import com.example.nidhal.frontend.network.RetrofitBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import   android.app.FragmentManager;

/**
 * Created by Nidhal on 17/07/2017.
 */

public class FillDrivingLicence extends AppCompatActivity {


    //crée le tag Actitivity
    private static final String TAG = "FillDrivingLicence";

    //  @BindView(R.id.name_value)
    // EditText name_value;
    @BindView(R.id.container)
    ScrollView container;

    @BindView(R.id.form_container)
    LinearLayout formContainer;

    @BindView(R.id.numero_permis_value)
    EditText numero_permis_value;


    @BindView(R.id.first_name_value)
    EditText first_name_value;


    @BindView(R.id.last_name_value)
    EditText last_name_value;


    @BindView(R.id.city_value)
    EditText city_value;

    @BindView(R.id.address_value)
    EditText address_value;

    @BindView(R.id.postal_code_value)
    EditText postal_code_value;

    @BindView(R.id.province_value)
    EditText province_value;

    ApiService service;

    TokenManager tokenManager;
    Call<UserResponse> callModifyUser;

    Call<UserResponse> callGetUser;


    Boolean first_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get an instance of the token manager
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

        //service pour accéder a des ressources privés
        service = RetrofitBuilder.createServiceWithAuth(ApiService.class, tokenManager);


        callGetUser = service.getUser();

        callGetUser.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                // .setText(response.body().getEmail());


                Log.d("first_login :", response.body().getFirst_login());


                if (Integer.parseInt(response.body().getFirst_login()) == 1)
                    startActivity(new Intent(FillDrivingLicence.this, ProfileActivity.class));





            }


            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });


        setContentView(R.layout.fill_license);

        ButterKnife.bind(this);


        callGetUser = service.getUser();

        showLoading();
        callGetUser.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                numero_permis_value.setText(response.body().getLicense_number());
                first_name_value.setText(response.body().getFirst_name());
                last_name_value.setText(response.body().getLast_name());
                city_value.setText(response.body().getCity());
                address_value.setText(response.body().getAddress());
                postal_code_value.setText(response.body().getPostal_code());
                province_value.setText(response.body().getProvince());
                showForm();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });



    }


    @OnClick(R.id.apply_fill)
    void apply_fill() {

        String numero_permis = numero_permis_value.getText().toString();
        String first_name = first_name_value.getText().toString();
        String last_name = last_name_value.getText().toString();
        String city = city_value.getText().toString();
        String address = address_value.getText().toString();
        String postal_code = postal_code_value.getText().toString();
        String province = province_value.getText().toString();

        callModifyUser = service.modifyUser(first_name, last_name, address, postal_code, province, city, numero_permis);

        callModifyUser.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Log.w("OnResponse", "success");
                startActivity(new Intent(FillDrivingLicence.this, FillInsurance.class));
                finish();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.w("OnResponse", "fail");
            }
        });


    }


    private void showLoading() {

        TransitionManager.beginDelayedTransition(container);
        formContainer.setVisibility(View.GONE);
     //   loader.setVisibility(View.VISIBLE);


    }

    private void showForm() {
        TransitionManager.beginDelayedTransition(container);
        formContainer.setVisibility(View.VISIBLE);
   //     loader.setVisibility(View.GONE);


    }

    @OnClick(R.id.next)
    void next(){
        startActivity(new Intent(FillDrivingLicence.this, FillInsurance.class));
        finish();
    }


}
