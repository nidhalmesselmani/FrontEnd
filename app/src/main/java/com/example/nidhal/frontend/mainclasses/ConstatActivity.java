package com.example.nidhal.frontend.mainclasses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nidhal.frontend.R;

public class ConstatActivity extends AppCompatActivity {

    //assurance
    private TextView nom_ass_1 = null;
    private TextView nom_ass_2 = null;
    private TextView address_ass_1 = null;
    private TextView address_ass_2 = null;
    //agence
    private TextView nom_agg_1 = null;
    private TextView nom_agg_2 = null;
    private TextView address_agg_1 = null;
    private TextView address_agg_2 = null;
    private TextView email_agg_1 = null;
    private TextView email_agg_2 = null;
    //constat
    private TextView num_police_1 = null;
    private TextView num_police_2 = null;
    private TextView date_debut_1 = null;
    private TextView date_debut_2 = null;
    private TextView date_fin_1 = null;
    private TextView date_fin_2 = null;
    private TextView marque_vh_1 = null;
    private TextView marque_vh_2 = null;
    private TextView type_vh_1 = null;
    private TextView type_vh_2 = null;
    private TextView num_serie_vh_1 = null;
    private TextView num_serie_vh_2 = null;

    //checkboxes
    CheckBox stationement_1;
    CheckBox stationement_2;

    CheckBox q_stationnement_1;
    CheckBox q_stationnement_2;

    CheckBox p_stationnement_1;
    CheckBox p_stationnement_2;

    CheckBox s_parking_1 ;
    CheckBox s_parking_2 ;

    CheckBox e_parking_1;
    CheckBox e_parking_2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constat);



        stationement_1 = (CheckBox) findViewById(R.id.checkbox_stationement_1);
        stationement_2 = (CheckBox)findViewById(R.id.checkbox_stationement_2);


        stationement_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                    stationement_2.setChecked(false);
            }
        });

        stationement_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    stationement_1.setChecked(false);
            }
        });



        q_stationnement_1 = (CheckBox) findViewById(R.id.q_stationnement_1);
        q_stationnement_2 = (CheckBox) findViewById(R.id.q_stationnement_2);

        q_stationnement_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    q_stationnement_2.setChecked(false);
            }
        });
        q_stationnement_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    q_stationnement_1.setChecked(false);
            }
        });

        p_stationnement_1 = (CheckBox) findViewById(R.id.p_stationnenemt_1);
        p_stationnement_2 = (CheckBox) findViewById(R.id.p_stationnenemt_2);

        p_stationnement_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    p_stationnement_2.setChecked(false);
            }
        });

        p_stationnement_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    p_stationnement_1.setChecked(false);
            }
        });


        s_parking_1 = (CheckBox) findViewById(R.id.s_parking_1);
        s_parking_2 = (CheckBox) findViewById(R.id.s_parking_2);


        s_parking_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    s_parking_2.setChecked(false);
            }
        });

        s_parking_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    s_parking_1.setChecked(false);
            }
        });




        e_parking_1 = (CheckBox) findViewById(R.id.e_parking_1);
        e_parking_2 = (CheckBox) findViewById(R.id.e_parking_2);
        e_parking_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    e_parking_2.setChecked(false);
            }
        });

        e_parking_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    e_parking_1.setChecked(false);
            }
        });


        //get the current intent
        Intent currentIntent = getIntent();
        //get the bundle inside of the intent
        Bundle currentBundle = currentIntent.getExtras();
        //bind names
        //assurance
        nom_ass_1 = (TextView) findViewById(R.id.name_ass_value_1);
        nom_ass_2 = (TextView) findViewById(R.id.name_ass_value_2);


        //agence
        nom_agg_1 = (TextView) findViewById(R.id.name_agg_value_1);
        nom_agg_2 = (TextView) findViewById(R.id.name_agg_value_2);
        //constat
        //numero polices
        num_police_1 = (TextView) findViewById(R.id.num_police_constat_value_1);
        num_police_2 = (TextView) findViewById(R.id.num_police_constat_value_2);
        //bind addresses
        //assurance
        address_ass_1 = (TextView) findViewById(R.id.address_ass_value_1);
        address_ass_2 = (TextView) findViewById(R.id.address_ass_value_2);
        //agence
        address_agg_1 = (TextView) findViewById(R.id.address_agg_value_1);
        address_agg_2 = (TextView) findViewById(R.id.address_agg_value_2);
        //constat
        //date_debut
        date_debut_1 = (TextView) findViewById(R.id.date_d_constat_value_1);
        date_debut_2 = (TextView) findViewById(R.id.date_d_constat_value_2);
        //date_fin
        date_fin_1 = (TextView) findViewById(R.id.date_f_constat_value_1);
        date_fin_2 = (TextView) findViewById(R.id.date_f_constat_value_2);
        //marque vehicule
        marque_vh_1 = (TextView) findViewById(R.id.marquevh_constat_value_1);
        marque_vh_2 = (TextView) findViewById(R.id.marquevh_constat_value_2);
        //type vehicule
        type_vh_1 = (TextView) findViewById(R.id.typevh_constat_value_1);
        type_vh_2 = (TextView) findViewById(R.id.typevh_constat_value_2);
        //numero_serie
        num_serie_vh_1 = (TextView)findViewById(R.id.mnumserievh_constat_value_1);
        num_serie_vh_2 = (TextView)findViewById(R.id.mnumserievh_constat_value_2);

        //bind emails
        //agence
        email_agg_1 = (TextView) findViewById(R.id.email_agg_value_1);
        email_agg_2 = (TextView) findViewById(R.id.email_agg_value_2);
        //set names
        //assurance
        nom_ass_1.setText(currentBundle.getString("nom_ass_1"));
        nom_ass_2.setText(currentBundle.getString("nom_ass_2"));
        //agence
        nom_agg_1.setText(currentBundle.getString("nom_agg_1"));
        nom_agg_2.setText(currentBundle.getString("nom_agg_2"));

        //set addresses
        //assurance
        address_ass_1.setText(currentBundle.getString("address_ass_1"));
        address_ass_2.setText(currentBundle.getString("address_ass_2"));

        //agence
        address_agg_1.setText(currentBundle.getString("address_agg_1"));
        address_agg_2.setText(currentBundle.getString("address_agg_2"));
        //set emails
        //agence
        email_agg_1.setText(currentBundle.getString("email_agg_1"));
        email_agg_2.setText(currentBundle.getString("email_agg_2"));
        //constat
        num_police_1.setText(currentBundle.getString("num_police_constat_value_1"));
        num_police_2.setText(currentBundle.getString("num_police_constat_value_2"));
        date_debut_1.setText(currentBundle.getString("date_d_constat_value_1"));
        date_debut_2.setText(currentBundle.getString("date_d_constat_value_2"));
        date_fin_1.setText(currentBundle.getString("date_f_constat_value_1"));
        date_fin_2.setText(currentBundle.getString("date_f_constat_value_2"));
        marque_vh_1.setText(currentBundle.getString("marquevh_constat_value_1"));
        marque_vh_2.setText(currentBundle.getString("marquevh_constat_value_2"));
        type_vh_1.setText(currentBundle.getString("typevh_constat_value_1"));
        type_vh_2.setText(currentBundle.getString("typevh_constat_value_2"));
        num_serie_vh_1.setText(currentBundle.getString("mnumserievh_constat_value_1"));
        num_serie_vh_2.setText(currentBundle.getString("mnumserievh_constat_value_2"));




    }
}
