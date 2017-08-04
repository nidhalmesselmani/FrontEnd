package com.example.nidhal.frontend.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.nidhal.frontend.R;
import com.example.nidhal.frontend.extra.QrCodeActivity;
import com.example.nidhal.frontend.mainclasses.AcceuilActivity;
import com.example.nidhal.frontend.mainclasses.FillInsurance;
import com.example.nidhal.frontend.subclasses.MyApp;
import com.google.zxing.Result;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Nidhal on 01/08/2017.
 */

public class DecideFragment extends Fragment {
    String[] choixList = {"1", "2"};
    @BindView(R.id.choix)
    MaterialBetterSpinner choix;


    @BindView(R.id.scan)
    Button scan;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.decide, container, false);
        //   View v = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, v);
        choix = (MaterialBetterSpinner) v.findViewById(R.id.choix);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, choixList);
        choix.setAdapter(arrayAdapter);

        scan.setEnabled(false);
        MyApp.number =0;
        choix.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                scan.setEnabled(true);


            }
        });



        return v;
    }


    @OnClick(R.id.scan)
    void scan(){

        MyApp.number ++ ;
        if(MyApp.number <=Integer.parseInt(choix.getText().toString()))
        startActivity(new Intent(getActivity(), QrCodeActivity.class));
   /*     getActivity().setContentView(mScannerView);
        mScannerView.setResultHandler(new ZXingScannerView.ResultHandler() {
            @Override
            public void handleResult(Result rawResult) {
                Toast.makeText(getActivity(), "Contents = " + rawResult.getText() + ", Format = " + rawResult.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).show();

                getActivity().setContentView(R.layout.decide);

            }
        });*/
       // mScannerView.startCamera();


    }


}
