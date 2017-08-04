package com.example.nidhal.frontend.mainclasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nidhal.frontend.R;
import com.example.nidhal.frontend.TokenManager;
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
 * Created by Nidhal on 31/07/2017.
 */

public class FillVehicle  extends AppCompatActivity {


    //crée le tag Actitivity
    private static final String TAG = "FillVehicle";

    //declare police call
    Call<Void> callPolice;
    //declare a service
    ApiService service;
    //declare a token
    TokenManager tokenManager;

    @BindView(R.id.mat_value)
    EditText mat_value;

    @BindView(R.id.brand_value)
    EditText brand_value;

    @BindView(R.id.type_value)
    EditText type_value;
    //declare num police
    String num_police;


    Call<Void> setFirstLoginTrue;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.create_vehicle);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
          num_police = extras.getString("num_police");
            //The key argument here must match that used in the other activity


        }

        ButterKnife.bind(this);
        //get an instance of the token manager
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

        //service pour accéder a des ressources privés
        service = RetrofitBuilder.createServiceWithAuth(ApiService.class, tokenManager);


    }


    @OnClick(R.id.apply_vh)
    void apply(){
        callPolice = service.insertVh(num_police,mat_value.getText().toString(),brand_value.getText().toString(),type_value.getText().toString());
        callPolice.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {


                setFirstLoginTrue = service.setFirstLoginTrue(1);
                setFirstLoginTrue.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.d(TAG,"success");
                        startActivity(new Intent(FillVehicle.this, AcceuilActivity.class));
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d(TAG,t.toString());

                    }
                });


            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG,"fail");

            }
        });
    }
}
