package com.example.nidhal.frontend.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.nidhal.frontend.R;
import com.example.nidhal.frontend.TokenManager;
import com.example.nidhal.frontend.entities.UserResponse;
import com.example.nidhal.frontend.extra.QrCodeActivity;
import com.example.nidhal.frontend.mainclasses.LoginActivity;
import com.example.nidhal.frontend.network.ApiService;
import com.example.nidhal.frontend.network.RetrofitBuilder;
import com.example.nidhal.frontend.subclasses.MyApp;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Nidhal on 10/07/2017.
 */

public class AcceuilFragment extends Fragment implements View.OnClickListener {
    //declaration des variables
    Button create_profile_btn;
    Button remplir_constat_btn;


    Call<UserResponse> callUser;

    ApiService service;

    TokenManager tokenManager;



    @BindView(R.id.user_name)
    TextView user_name;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.acceuil,container,false);
        //get an instance of the token manager
        tokenManager = TokenManager.getInstance(getActivity().getSharedPreferences("prefs", getActivity().MODE_PRIVATE));

        //service pour accéder a des ressources privés
        service = RetrofitBuilder.createServiceWithAuth(ApiService.class, tokenManager);

        ButterKnife.bind(this, v);

        callUser = service.getUser();
        Log.d("frag","outside");

        callUser.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                 Log.d("frag","inside of response");
                if (response.isSuccessful()) {
                    user_name.setText(response.body().getName());
                }else{
                    //delete token
                    tokenManager.deleteToken();
                    startActivity(new Intent(getActivity().getApplicationContext(), LoginActivity.class));



                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d("frag","inside of fail");
            }
        });







  /*      create_profile_btn = (Button) v.findViewById(R.id.create_profile_btn);
        create_profile_btn.setOnClickListener(this);
      */  Button remplir_constat_btn = (Button) v.findViewById(R.id.remplir_constat);
        remplir_constat_btn.setOnClickListener(this);
        return v;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {

         //   case R.id.create_profile_btn:
       //         Toast.makeText(getActivity().getApplicationContext(),"create profile",Toast.LENGTH_SHORT).show();
            //    ProfileFragmentUser f = new ProfileFragmentUser();
               // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer,f).commit();
            //    MyApp.mBottomBar.selectTabAtPosition(1);
     //           break;
            case R.id.remplir_constat:
                Toast.makeText(getActivity().getApplicationContext(),"rempliar constat",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), QrCodeActivity.class));

            //    DecideFragment f = new DecideFragment();
              //  getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer,f).commit();
                MyApp.mBottomBar.selectTabAtPosition(2);
        //        ConstatFragment f1 = new ConstatFragment();
              //  getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer,f1).commit();
          //      MyApp.mBottomBar.selectTabAtPosition(2);
                break;

        }
    }


}

