package com.example.nidhal.frontend.mainclasses;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nidhal.frontend.R;
import com.example.nidhal.frontend.TokenManager;
import com.example.nidhal.frontend.entities.Agency;
import com.example.nidhal.frontend.entities.AgencyResponse;
import com.example.nidhal.frontend.entities.Insurance;
import com.example.nidhal.frontend.entities.InsuranceResponse;
import com.example.nidhal.frontend.entities.PoliceResponse;
import com.example.nidhal.frontend.entities.UserResponse;
import com.example.nidhal.frontend.network.ApiService;
import com.example.nidhal.frontend.network.RetrofitBuilder;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nidhal on 27/07/2017.
 */

public class FillInsurance extends AppCompatActivity {


    //crée le tag Actitivity
    private static final String TAG = "FillInsurance";
    String[] AssuranceList = {"One", "Two"};

    String[] AgenceList = {"One"};


    List<Insurance> insurances;
    List<Agency> agencies;
    ApiService service;

    TokenManager tokenManager;

    @BindView(R.id.Assurance_spinner)
    MaterialBetterSpinner Assurance_spinner;

    @BindView(R.id.Agence_spinner)
    MaterialBetterSpinner Agence_spinner;

    @BindView(R.id.num_police_value)
    EditText num_police_value;


    Call<InsuranceResponse> insuranceCall;
    Call<AgencyResponse> agencyeCall;
    Call<UserResponse> callGetUser;


    MaterialBetterSpinner insuranceSpinner;
    MaterialBetterSpinner agenceSpinner;
    int idList[];
    String userId;
    Agency selectedAgency;
    Insurance selectedInsurance;


    //make a police call
    Call<Void> callPolice;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creating_account_insurance);

        Toast.makeText(this, "I'm here", Toast.LENGTH_SHORT).show();

        insurances = new ArrayList<Insurance>();
        agencies = new ArrayList<Agency>();
        ;
        //get an instance of the token manager
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

        //service pour accéder a des ressources privés
        service = RetrofitBuilder.createServiceWithAuth(ApiService.class, tokenManager);

        ButterKnife.bind(this);
        //call the insurance service
        callGetUser = service.getUser();

        callGetUser.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                userId = response.body().getId();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });



        insuranceCall = service.getInsurances();

        insuranceCall.enqueue(new Callback<InsuranceResponse>() {
            @Override
            public void onResponse(Call<InsuranceResponse> call, Response<InsuranceResponse> response) {
                int i;
                for (i = 0; i < response.body().getData().toArray().length; i++) {

                    AssuranceList[i] = response.body().getData().get(i).getName();
                    Insurance is = new Insurance();
                    is.setName(response.body().getData().get(i).getName());
                    is.setId(response.body().getData().get(i).getId());
                    insurances.add(is);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(FillInsurance.this, android.R.layout.simple_dropdown_item_1line, AssuranceList);
                insuranceSpinner.setAdapter(arrayAdapter);
            }


            @Override
            public void onFailure(Call<InsuranceResponse> call, Throwable t) {

            }
        });


        //make adapter assurance list spinner
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, AssuranceList);

        insuranceSpinner = (MaterialBetterSpinner) findViewById(R.id.Assurance_spinner);

        insuranceSpinner.setAdapter(arrayAdapter);

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, AgenceList);


        agenceSpinner = (MaterialBetterSpinner) findViewById(R.id.Agence_spinner);
        agenceSpinner.setAdapter(arrayAdapter1);


        insuranceSpinner.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Toast.makeText(getApplicationContext(), "selected" + insuranceSpinner.getText().toString(), Toast.LENGTH_SHORT).show();


                selectedInsurance = new Insurance();
                selectedInsurance.setName(insuranceSpinner.getText().toString());
                int i = 0;
                for (Insurance ainsurance : insurances) {
                    if (ainsurance.getName().equals(insuranceSpinner.getText().toString())) {
                        selectedInsurance.setId(ainsurance.getId());

                        agencyeCall = service.get_agences(ainsurance.getId());

                        agencyeCall.enqueue(new Callback<AgencyResponse>() {
                            @Override
                            public void onResponse(Call<AgencyResponse> call, Response<AgencyResponse> response) {
                                int i;
                                agenceSpinner.setText("");
                                List<String> agenciesList = new ArrayList<String>();
                                for (i = 0; i < response.body().getData().toArray().length; i++) {
                                    agenciesList.add(response.body().getData().get(i).getName());
                                    Agency ag = new Agency();
                                    ag.setId(response.body().getData().get(i).getId());
                                    ag.setName(response.body().getData().get(i).getName());
                                    agencies.add(ag);
                                }
                                ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(FillInsurance.this, android.R.layout.simple_dropdown_item_1line, agenciesList);


                                agenceSpinner.setAdapter(arrayAdapter1);

                            }

                            @Override
                            public void onFailure(Call<AgencyResponse> call, Throwable t) {

                            }
                        });
                    }

                }


            }
        });
        agenceSpinner.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                selectedAgency = new Agency();
                selectedAgency.setName(agenceSpinner.getText().toString());
                for (Agency aAgency : agencies) {
                    if (aAgency.getName().equals(agenceSpinner.getText().toString())) {
                        selectedAgency.setId(aAgency.getId());

                    }

                }

            }
        });
    }


    @OnClick(R.id.apply_fill)
    void apply_fill() {




        callPolice = service.create_police(num_police_value.getText().toString(),userId,String.valueOf(selectedInsurance.getId()),String.valueOf(selectedAgency.getId()));


        callPolice.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {


                Intent intent = new Intent(getBaseContext(), FillVehicle.class);
                intent.putExtra("num_police", num_police_value.getText().toString());
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG,t.toString());
            }
        });



    }


}
