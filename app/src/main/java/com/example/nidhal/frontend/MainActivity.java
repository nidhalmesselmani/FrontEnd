package com.example.nidhal.frontend;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;



import com.example.nidhal.frontend.singleton.MySingleton;
import com.example.nidhal.frontend.subclasses.MyApp;
import com.google.zxing.Result;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import org.json.JSONException;
import org.json.JSONObject;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    TextView mTextView;
    private String qrCode;


    int i = 0;

    //next activity declaration;
    public Intent targetActity;
    Bundle targetBundle;
//fin de declaration des variables


    TextView user1;
    TextView user2;


    private String nomAssurance1 = null;
    private String nomAssurance2 = null;
    //declare and instatiate bottomBar


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.starter);


          MyApp.mBottomBar = (BottomBar) findViewById(R.id.bottomBar);

/*        MyApp.mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if(tabId == R.id.tab_acceuil){
                    Toast.makeText(getApplicationContext(),"acceuil",Toast.LENGTH_SHORT).show();
//                    AcceuilFragment f = new AcceuilFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer,f).commit();
                }


                if(tabId == R.id.tab_constat){
                 //   Toast.makeText(getApplicationContext(),"constat",Toast.LENGTH_SHORT).show();
                    ConstatFragment f = new ConstatFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer,f).commit();

                }

                if(tabId == R.id.tab_profile) {
                    Toast.makeText(getApplicationContext(), "profile", Toast.LENGTH_SHORT).show();
                    ProfileFragmentUser f = new ProfileFragmentUser();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer,f).commit();

                }
            }
        });
*/

       // mBottomBar = BottomBar.attach(this,savedInstanceState);
       /* this is essential
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        //creating the new intent to navigate to the next activity
        targetActity = new Intent(getApplicationContext(), ConstatActivity.class);
        //creating the new bundle
        targetBundle = new Bundle();
        //starting the application
        Log.d("starting", "go go go");



     */



        //volley simple request
       // mTextView = (TextView) findViewById(R.id.text);

      /*  telephone = (TextView) findViewById(R.id.telephoneValue);
        //    nomAssurance = (TextView) findViewById(R.id.nomAssuranceValue);
        nomAssurance = (TextView) findViewById(R.id.nomAssuranceValue);

*/
        //  MyApp.qrCode = null;

        //initialisation du variable globale,this is essential
        /*
        MyApp.setQrCode("");
        MyApp.setScan(0);
*/

     /* this is essential
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
            }
        }*/

    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }*/

    @Override
    public void onPause() {
        super.onPause();
      //  mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        // Toast.makeText(getApplicationContext(), rawResult.getText(), Toast.LENGTH_SHORT).show();


        //call custom method
        qrCode = rawResult.getText();
        //     String url = "http://192.168.10.92:8001/getBoulices/"+ qrCode;
        String url = MyApp.getAppUrl() + "getConstats/" + qrCode;
        mScannerView.stopCamera(); //<- then stop the camera
        setContentView(R.layout.activity_main);

        //volley simple request

//        mTextView = (TextView) findViewById(R.id.text);


      Toast.makeText(this,url,Toast.LENGTH_SHORT).show();
        //here where it is supposed to send the request
          sendRequest(url);




        /* delay */


        /*if(i==1){
            Toast.makeText(getApplicationContext(), "User1"+this.getNomUser1(), Toast.LENGTH_SHORT).show();
        }*/


    }

    //custom metho to send requests
    private void sendRequest(String url) {

        if (!(MyApp.getQrCode().equals(url))) {
            //    MyApp.incrementScan();


            MyApp.setQrCode(url);
            // Toast.makeText(this, "1ere qrCode sauvegardé", Toast.LENGTH_SHORT).show();
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

        } else {
           Toast.makeText(this,"svp entrez un autre code",Toast.LENGTH_SHORT).show();

        }


    }


    public void scan(View view) {

        setContentView(mScannerView);
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();
    }


    public void fillConstat() {
        MyApp.setQrCode("");
        MyApp.setScan(0);


    }



}
