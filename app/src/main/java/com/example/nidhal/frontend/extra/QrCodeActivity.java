package com.example.nidhal.frontend.extra;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.nidhal.frontend.R;
import com.example.nidhal.frontend.TokenManager;
import com.example.nidhal.frontend.entities.Constat;
import com.example.nidhal.frontend.entities.ConstatResponse;
import com.example.nidhal.frontend.entities.Police;
import com.example.nidhal.frontend.mainclasses.LoginActivity;
import com.example.nidhal.frontend.network.ApiService;
import com.example.nidhal.frontend.network.RetrofitBuilder;
import com.example.nidhal.frontend.singleton.MySingleton;
import com.example.nidhal.frontend.subclasses.MyApp;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;


/**
 * Created by Nidhal on 01/08/2017.
 */

public class QrCodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private String qrCode;
    ZXingScannerView mScannerView;

    Call<Constat> callConstat;
    Call<Police> callPolice;
    private int id_user_1;
    private int id_user_2;
    private int id_insurance_1;
    private int id_insurance_2;
    private String nomAssurance2 = null;
    ApiService service;

    TokenManager tokenManager;
    //crée le tag Actitivity
    private static final String TAG = "QrCodeActivity";

    int i = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        mScannerView = new ZXingScannerView(this);


        //get an instance of the token manager
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

        //service pour accéder a des ressources privés
        service = RetrofitBuilder.createServiceWithAuth(ApiService.class, tokenManager);

        // this is essential
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
            }
        }

        //  setContentView(mScannerView);
/*
        mScannerView.setResultHandler(new ZXingScannerView.ResultHandler() {
                                          @Override
                                          public void handleResult(Result rawResult) {
                                              Toast.makeText(getApplicationContext(), "Contents = " + rawResult.getText() + ", Format = " + rawResult.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).show();
                                              Log.d("jjjj","Contents = " +rawResult.getText() + ", Format = " + rawResult.getBarcodeFormat().toString());
                                              finish();


                                          }
                                      });*/
        //   mScannerView.startCamera();
        MyApp.setQrCode("");

        ButterKnife.bind(this);

        sendRequest("111");
        sendRequest("112233");

    }


    @OnClick(R.id.scan)
    void scan() {
        setContentView(mScannerView);
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();
    }

    @Override
    public void handleResult(Result rawResult) {


        // Do something with the result here
        // Toast.makeText(getApplicationContext(), rawResult.getText(), Toast.LENGTH_SHORT).show();


        //call custom method
        qrCode = rawResult.getText();
        //     String url = "http://192.168.10.92:8001/getBoulices/"+ qrCode;

        mScannerView.stopCamera(); //<- then stop the camera
        setContentView(R.layout.activity_main);

        //volley simple request

//        mTextView = (TextView) findViewById(R.id.text);


        Toast.makeText(this, qrCode, Toast.LENGTH_SHORT).show();
        //here where it is supposed to send the request
        sendRequest(qrCode);


    }


    //custom metho to send requests
    private void sendRequest(String qrCode) {

        if (!(MyApp.getQrCode().equals(qrCode))) {
            //    MyApp.incrementScan();


            MyApp.setQrCode(qrCode);


            callPolice = service.get_police_info(qrCode);

            callPolice.enqueue(new Callback<Police>() {
                @Override
                public void onResponse(Call<Police> call, retrofit2.Response<Police> response) {

                    if (response.isSuccessful()) {
                        i++;
                        if (i == 1) {
                           id_user_1 = response.body().getId_user();
                            id_insurance_1 = response.body().getId_insurance();
                        } else if (i == 2) {
                            id_user_2 = response.body().getId_user();
                            id_insurance_2 = response.body().getId_insurance();



                            //startActivity(new Intent(QrCodeActivity.this, ExtraDataConstat.class));

                            callConstat = service.post_constat(id_user_1,id_user_2,id_insurance_1,id_insurance_2);
                           callConstat.enqueue(new Callback<Constat>() {
                               @Override
                               public void onResponse(Call<Constat> call, retrofit2.Response<Constat> response) {

                                   if (response.isSuccessful()) {
                                      // Toast.makeText(getApplicationContext(), "id: " + String.valueOf(response.body().getId()), Toast.LENGTH_SHORT).show();
                                       Intent intent = new Intent(getBaseContext(), ExtraDataConstat.class);
                                       intent.putExtra("id_constat", response.body().getId());
                                       startActivity(intent);
                                   }
                                   else{
                                       //delete token
                                       tokenManager.deleteToken();
                                       startActivity(new Intent(QrCodeActivity.this, LoginActivity.class));
                                       finish();


                                   }



                               }

                               @Override
                               public void onFailure(Call<Constat> call, Throwable t) {

                               }
                           });


                        }
                    } else {
                        //delete token
                        tokenManager.deleteToken();
                        startActivity(new Intent(QrCodeActivity.this, LoginActivity.class));
                        finish();

                    }
                }

                @Override
                public void onFailure(Call<Police> call, Throwable t) {

                }
            });



         /*   // Toast.makeText(this, "1ere qrCode sauvegardé", Toast.LENGTH_SHORT).show();
//outside of on response
            //  Toast.makeText(this, "outside of onResponse", Toast.LENGTH_SHORT).show();
            JsonObjectRequest jsObjRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {


                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("response", "inside");

                            i++;
                            Log.d("i", String.valueOf(i));
                            // Toast.makeText(getApplicationContext(), "outside of try" + String.valueOf(MyApp.scanFirst), Toast.LENGTH_SHORT).show();

                            try {

                                //Toast.makeText(getApplicationContext(), "inside of try" + String.valueOf(MyApp.getScan()), Toast.LENGTH_SHORT).show();
                                //   if (MyApp.getScan() == 1) {

                                if (i == 1) {
                                    //assurance
                                    targetBundle.putString("nom_ass_1", response.getJSONObject("data").getJSONObject("assurance").getString("nom"));

                                    targetBundle.putString("address_ass_1", response.getJSONObject("data").getJSONObject("assurance").getString("address"));
                                    //agence
                                    targetBundle.putString("nom_agg_1", response.getJSONObject("data").getJSONObject("agence").getString("nom"));
                                    //       Log.d("nom_agence_1", response.getJSONObject("data").getJSONObject("agence").getString("nom"));
                                    targetBundle.putString("address_agg_1", response.getJSONObject("data").getJSONObject("agence").getString("adresse"));
                                    targetBundle.putString("email_agg_1", response.getJSONObject("data").getJSONObject("agence").getString("email"));
                                    //constat
                                    targetBundle.putString("num_police_constat_value_1",response.getJSONObject("data").getJSONObject("constat").getString("num_police"));
                                    targetBundle.putString("date_d_constat_value_1",response.getJSONObject("data").getJSONObject("constat").getString("date_debut"));
                                    targetBundle.putString("date_f_constat_value_1",response.getJSONObject("data").getJSONObject("constat").getString("date_fin"));
                                    targetBundle.putString("marquevh_constat_value_1",response.getJSONObject("data").getJSONObject("constat").getString("Marque_vehicule"));
                                    targetBundle.putString("typevh_constat_value_1",response.getJSONObject("data").getJSONObject("constat").getString("Type_vehicule"));
                                    targetBundle.putString("mnumserievh_constat_value_1",response.getJSONObject("data").getJSONObject("constat").getString("num_serie"));

                                    Toast.makeText(getApplicationContext(),"Votre premier code a été scanné avec succès",Toast.LENGTH_SHORT).show();


                                } else {


                                    if (i == 2) {

                                        //assurance
                                        targetBundle.putString("nom_ass_2", response.getJSONObject("data").getJSONObject("assurance").getString("nom"));
                                        targetBundle.putString("address_ass_2", response.getJSONObject("data").getJSONObject("assurance").getString("address"));

                                        //agence
                                        targetBundle.putString("nom_agg_2", response.getJSONObject("data").getJSONObject("agence").getString("nom"));
                                        targetBundle.putString("address_agg_2", response.getJSONObject("data").getJSONObject("agence").getString("adresse"));
                                        targetBundle.putString("email_agg_2", response.getJSONObject("data").getJSONObject("agence").getString("email"));
                                        //constat
                                        targetBundle.putString("num_police_constat_value_2",response.getJSONObject("data").getJSONObject("constat").getString("num_police"));
                                        targetBundle.putString("date_d_constat_value_2",response.getJSONObject("data").getJSONObject("constat").getString("date_debut"));
                                        targetBundle.putString("date_f_constat_value_2",response.getJSONObject("data").getJSONObject("constat").getString("date_fin"));
                                        targetBundle.putString("marquevh_constat_value_2",response.getJSONObject("data").getJSONObject("constat").getString("Marque_vehicule"));
                                        targetBundle.putString("typevh_constat_value_2",response.getJSONObject("data").getJSONObject("constat").getString("Type_vehicule"));
                                        targetBundle.putString("mnumserievh_constat_value_2",response.getJSONObject("data").getJSONObject("constat").getString("num_serie"));

                                        Toast.makeText(getApplicationContext(),"Votre deuxième code a été scanné avec succès",Toast.LENGTH_SHORT).show();

                                        //  go to the constat Activity
                                        targetActity.putExtras(targetBundle);
                                        startActivity(targetActity);

                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();

                                Log.d("erreur", e.toString());

                            }


                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO Auto-generated method stub

                            Log.d("erreur", error.toString());


                        }
                    });


// Access the RequestQueue through your singleton class.
            MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
            MyApp.scanFirst = true;
*/
        } else {
            Toast.makeText(this, "svp entrez un autre code", Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public void onPause() {
        super.onPause();
        //  mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
