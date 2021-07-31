package com.selfyclub.selfyclubappV2.ChangeLanguage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.selfyclub.selfyclubappV2.Main_Menu.MainMenuActivity;
import com.selfyclub.selfyclubappV2.Models.Ads_Get_Model;
import com.selfyclub.selfyclubappV2.R;
import com.selfyclub.selfyclubappV2.SimpleClasses.Variables;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Change_language_F extends  AppCompatActivity {

   TextView tvChangeEng, tvChangeMal, tvChangeHin, tvChangeTam;
   ImageView heart_hin, heart_eng, heart_mal, heart_tam;

   Button change_language_btn;

    CardView cvMala, cvEng, cvHind, cvTam, cvUrdu, cvKanada, cvGujarati, cvTelugu, cvOdiya, cvPunjabi, cvMarathi, cvBengali;
    Button btConform;

   //location
   private static final int REQUEST_LOCATION = 1;
    Button btnGetLocation;
    TextView showLocation;
    LocationManager locationManager;
    String latitude, longitude;
    String url;

    String lang;
    RelativeLayout rlMala, rlHindi, rlEng, rlTamil, rlUrdu, rlKanada, rlTelugu, rlOdiya, rlMarathi, rlBengali, rlGujarati, rlPunjabi;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_language__f);


        ////////////////22-05-10:34 shared preference for language

        cvEng = findViewById(R.id.cv_english);
        cvHind = findViewById(R.id.cv_hindi);
        cvMala = findViewById(R.id.cv_mala);
        cvTam = findViewById(R.id.cv_tamil);

        cvKanada = findViewById(R.id.cv_kanada);
        cvTelugu = findViewById(R.id.cv_telugu);
        cvOdiya = findViewById(R.id.cv_oriya);
        cvUrdu = findViewById(R.id.cv_urdu);
        cvPunjabi = findViewById(R.id.cv_punjabi);
        cvGujarati = findViewById(R.id.cv_gujarati);
        cvMarathi = findViewById(R.id.cv_marathi);
        cvBengali = findViewById(R.id.cv_bengali);

        btConform = findViewById(R.id.btn_conform);
        rlMala = findViewById(R.id.rl_malHead);
        rlEng = findViewById(R.id.rl_eng);
        rlHindi = findViewById(R.id.rl_hindi);
        rlTamil = findViewById(R.id.rl_tamil);

        rlUrdu = findViewById(R.id.rl_urdu);
        rlKanada = findViewById(R.id.rl_kannada);
        rlTelugu = findViewById(R.id.rl_telugu);
        rlOdiya = findViewById(R.id.rl_oriya);
        rlMarathi = findViewById(R.id.rl_marathi);
        rlPunjabi = findViewById(R.id.rl_punjabi);
        rlGujarati = findViewById(R.id.rl_gujarati);
        rlBengali = findViewById(R.id.rl_bengali);
        ///////////////




        //location
       /* ActivityCompat.requestPermissions( this,
                new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);*/
//        showLocation = findViewById(R.id.showLocation);
//        btnGetLocation = findViewById(R.id.btnGetLocation);
        /*btnGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    OnGPS();
                } else {
                    getLocation();
                }
            }
        });*/

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        cvHind.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                setLocal("hi");

                rlMala.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlEng.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlHindi.setBackgroundColor(getResources().getColor(R.color.lightgreen));
                rlTamil.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlUrdu.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlKanada.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlTelugu.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlPunjabi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlGujarati.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlMarathi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlBengali.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlOdiya.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));

                btConform.setVisibility(View.VISIBLE);

                btConform.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(Variables.language,"hindi");
                        editor.apply();

                        Intent i = new Intent(Change_language_F.this, MainMenuActivity.class);
                        startActivity(i);
                    }
                });

            }
        });

        cvMala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                setLocal("ml");

                rlMala.setBackgroundColor(getResources().getColor(R.color.lightgreen));
                rlEng.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlHindi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlTamil.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlUrdu.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlKanada.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlTelugu.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlPunjabi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlGujarati.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlMarathi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlBengali.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlOdiya.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));

                btConform.setVisibility(View.VISIBLE);


                btConform.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(Variables.language,"malayalam");
                        editor.apply();

                        Intent i = new Intent(Change_language_F.this, MainMenuActivity.class);
                        startActivity(i);
                    }
                });

            }
        });

        cvTam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* heart_eng.setVisibility(View.GONE);
                heart_tam.setVisibility(View.VISIBLE);
                heart_hin.setVisibility(View.GONE);
                heart_mal.setVisibility(View.GONE);*/
//                change_language_btn.setEnabled(true);
                setLocal("ta");

                rlMala.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlEng.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlHindi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlTamil.setBackgroundColor(getResources().getColor(R.color.lightgreen));
                rlUrdu.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlKanada.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlTelugu.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlPunjabi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlGujarati.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlMarathi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlBengali.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlOdiya.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));

                btConform.setVisibility(View.VISIBLE);

                btConform.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(Variables.language,"tamil");
                        editor.apply();

                        Intent i = new Intent(Change_language_F.this, MainMenuActivity.class);
                        startActivity(i);
                    }
                });

            }
        });


        cvEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setLocal("en");

                rlMala.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlEng.setBackgroundColor(getResources().getColor(R.color.lightgreen));
                rlHindi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlTamil.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlUrdu.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlKanada.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlTelugu.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlPunjabi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlGujarati.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlMarathi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlBengali.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlOdiya.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));

                btConform.setVisibility(View.VISIBLE);

                btConform.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(Variables.language,"english");
                        editor.apply();

                        Intent i = new Intent(Change_language_F.this, MainMenuActivity.class);
                        startActivity(i);
                    }
                });

            }
        });

        cvUrdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setLocal("ur");

                rlMala.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlEng.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlHindi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlTamil.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlUrdu.setBackgroundColor(getResources().getColor(R.color.lightgreen));
                rlKanada.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlTelugu.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlPunjabi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlGujarati.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlMarathi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlBengali.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlOdiya.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));

                btConform.setVisibility(View.VISIBLE);

                btConform.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(Variables.language,"urdu");
                        editor.apply();

                        Intent i = new Intent(Change_language_F.this, MainMenuActivity.class);
                        startActivity(i);
                    }
                });

            }
        });


        cvKanada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setLocal("kn");

                rlMala.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlEng.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlHindi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlTamil.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlUrdu.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlKanada.setBackgroundColor(getResources().getColor(R.color.lightgreen));
                rlTelugu.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlPunjabi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlGujarati.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlMarathi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlBengali.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlOdiya.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));

                btConform.setVisibility(View.VISIBLE);

                btConform.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(Variables.language,"kannada");
                        editor.apply();

                        Intent i = new Intent(Change_language_F.this, MainMenuActivity.class);
                        startActivity(i);
                    }
                });

            }
        });

        cvBengali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setLocal("bn");

                rlMala.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlEng.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlHindi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlTamil.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlUrdu.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlKanada.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlTelugu.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlPunjabi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlGujarati.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlMarathi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlBengali.setBackgroundColor(getResources().getColor(R.color.lightgreen));
                rlOdiya.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));

                btConform.setVisibility(View.VISIBLE);

                btConform.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(Variables.language,"bengali");
                        editor.apply();

                        Intent i = new Intent(Change_language_F.this, MainMenuActivity.class);
                        startActivity(i);
                    }
                });

            }
        });

        cvMarathi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setLocal("mr");

                rlMala.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlEng.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlHindi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlTamil.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlUrdu.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlKanada.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlTelugu.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlPunjabi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlGujarati.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlMarathi.setBackgroundColor(getResources().getColor(R.color.lightgreen));
                rlBengali.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlOdiya.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));

                btConform.setVisibility(View.VISIBLE);

                btConform.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(Variables.language,"marati");
                        editor.apply();

                        Intent i = new Intent(Change_language_F.this, MainMenuActivity.class);
                        startActivity(i);
                    }
                });

            }
        });


        cvGujarati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setLocal("gu");

                rlMala.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlEng.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlHindi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlTamil.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlUrdu.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlKanada.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlTelugu.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlPunjabi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlGujarati.setBackgroundColor(getResources().getColor(R.color.lightgreen));
                rlMarathi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlBengali.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlOdiya.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));

                btConform.setVisibility(View.VISIBLE);

                btConform.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(Variables.language,"gujarati");
                        editor.apply();

                        Intent i = new Intent(Change_language_F.this, MainMenuActivity.class);
                        startActivity(i);
                    }
                });

            }
        });

        cvPunjabi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setLocal("pa");

                rlMala.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlEng.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlHindi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlTamil.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlUrdu.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlKanada.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlTelugu.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlPunjabi.setBackgroundColor(getResources().getColor(R.color.lightgreen));
                rlGujarati.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlMarathi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlBengali.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlOdiya.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));

                btConform.setVisibility(View.VISIBLE);

                btConform.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(Variables.language,"punjabi");
                        editor.apply();

                        Intent i = new Intent(Change_language_F.this, MainMenuActivity.class);
                        startActivity(i);
                    }
                });

            }
        });

        cvOdiya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setLocal("or");

                rlMala.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlEng.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlHindi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlTamil.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlUrdu.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlKanada.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlTelugu.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlPunjabi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlGujarati.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlMarathi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlBengali.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlOdiya.setBackgroundColor(getResources().getColor(R.color.lightgreen));

                btConform.setVisibility(View.VISIBLE);

                btConform.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(Variables.language,"odiya");
                        editor.apply();

                        Intent i = new Intent(Change_language_F.this, MainMenuActivity.class);
                        startActivity(i);
                    }
                });

            }
        });

        cvTelugu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setLocal("te");

                rlMala.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlEng.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlHindi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlTamil.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlUrdu.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlKanada.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlTelugu.setBackgroundColor(getResources().getColor(R.color.lightgreen));
                rlPunjabi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlGujarati.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlMarathi.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlBengali.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));
                rlOdiya.setBackgroundColor(getResources().getColor(R.color.lightblack_lang));

                btConform.setVisibility(View.VISIBLE);

                btConform.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(Variables.language,"telungu");
                        editor.apply();

                        Intent i = new Intent(Change_language_F.this, MainMenuActivity.class);
                        startActivity(i);
                    }
                });

            }
        });

    }


/*    private void showChangeLanguageDialog() {
        final String[] listItems = {"हिंदी","മലയാളം","English", "Hindi"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Change_language_F.this);
        mBuilder.setTitle("Choose Language...");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(i==0){
                    setLocal("hi");
                    nextpage();
                    recreate();
                }

                else if (i==1){
                    setLocal("ml");
                    nextpage();
                    recreate();
                }
                else if (i==2){
                    setLocal("ta");
                    nextpage();
                    recreate();
                }
                else if (i==3){
                    setLocal("en");
                    nextpage();
                    recreate();
                }

                dialog.dismiss();
            }
        });

        AlertDialog mDialog =mBuilder.create();
        mDialog.show();

    }*/

    private void nextpage() {

        Intent i = new Intent(Change_language_F.this, MainMenuActivity.class);
        startActivity(i);
    }

    private void setLocal(String lang) {

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();

    }

    public void loadLocale(String lang){

        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang",lang);
        setLocal(language);
    }

    //Location
    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                Change_language_F.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                Change_language_F.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
//                37.421998333333335
//                -122.084
                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);
//                getAds();
//                showLocation.setText("Your Location: " + "\n" + "Latitude: " + latitude + "\n" + "Longitude: " + longitude);
            } else {
//                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getAds() {

        RequestQueue queue = Volley.newRequestQueue(this);

        url = "https://www.selfyclub.com/mobileapp_api/api/showAds";
        JSONObject jsonBody = new JSONObject();
        Gson gson = new Gson();
        try {

//            jsonBody.put("zipLat", latitude);
//            jsonBody.put("ziplng", longitude);
            jsonBody.put("zipLat", "9.0341");
            jsonBody.put("ziplng", "76.8548");
            jsonBody.put("distance", "10");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            Ads_Get_Model adsparsedresponse = gson.fromJson(response.toString(),Ads_Get_Model.class);

//                Toast.makeText(Change_language_F.this, , Toast.LENGTH_SHORT).show();

                Log.e("","");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("","");
            }
        }){

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String,String> headers =  new HashMap<>();
                headers.put("api-key", "156c4675-9608-4591-1111-00000");
                return headers;
            }



        };
        jsonObjectRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });


        queue.add(jsonObjectRequest);

    }

}


