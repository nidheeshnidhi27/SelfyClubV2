package com.selfyclub.selfyclubappV2.ChangeLanguage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.selfyclub.selfyclubappV2.Main_Menu.MainMenuActivity;
import com.selfyclub.selfyclubappV2.R;
import com.selfyclub.selfyclubappV2.SimpleClasses.Functions;
import com.selfyclub.selfyclubappV2.SimpleClasses.Variables;

public class Change_language_Fragment extends Fragment {

    View view;

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




    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.activity_change_language__f, container, false);

        /*//location
        ActivityCompat.requestPermissions( this,
                new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            OnGPS();
        } else {
            getLocation();
        }
*/
        Variables.sharedPreferences = Functions.getSharedPreference(getContext());

//        Variables.sharedPreferences = getSharedPreferences(Variables.pref_name, MODE_PRIVATE);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        lang = sharedPreferences.getString(Variables.language, null);

//        if (lang != null) {
//            getActivity().finishAndRemoveTask();
//
////            this.finish();
////            System.exit(0);
//        }
        ////////////////22-05-10:34 shared preference for language

        cvEng = view.findViewById(R.id.cv_english);
        cvHind = view.findViewById(R.id.cv_hindi);
        cvMala = view.findViewById(R.id.cv_mala);
        cvTam = view.findViewById(R.id.cv_tamil);

        cvKanada = view.findViewById(R.id.cv_kanada);
        cvTelugu = view.findViewById(R.id.cv_telugu);
        cvOdiya = view.findViewById(R.id.cv_oriya);
        cvUrdu = view.findViewById(R.id.cv_urdu);
        cvPunjabi = view.findViewById(R.id.cv_punjabi);
        cvGujarati = view.findViewById(R.id.cv_gujarati);
        cvMarathi = view.findViewById(R.id.cv_marathi);
        cvBengali = view.findViewById(R.id.cv_bengali);

        btConform = view.findViewById(R.id.btn_conform);
        rlMala = view.findViewById(R.id.rl_malHead);
        rlEng = view.findViewById(R.id.rl_eng);
        rlHindi = view.findViewById(R.id.rl_hindi);
        rlTamil = view.findViewById(R.id.rl_tamil);

        rlUrdu = view.findViewById(R.id.rl_urdu);
        rlKanada = view.findViewById(R.id.rl_kannada);
        rlTelugu = view.findViewById(R.id.rl_telugu);
        rlOdiya = view.findViewById(R.id.rl_oriya);
        rlMarathi = view.findViewById(R.id.rl_marathi);
        rlPunjabi = view.findViewById(R.id.rl_punjabi);
        rlGujarati = view.findViewById(R.id.rl_gujarati);
        rlBengali = view.findViewById(R.id.rl_bengali);

        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        cvHind.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
//                setLocal("hi");

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

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(Variables.language,"hindi");
                        editor.apply();

                        Intent i = new Intent(getActivity(), MainMenuActivity.class);
                        startActivity(i);
                    }
                });

            }
        });

        cvMala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                setLocal("ml");

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

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(Variables.language,"malayalam");
                        editor.apply();

                        Intent i = new Intent(getActivity(), MainMenuActivity.class);
                        startActivity(i);
                    }
                });

            }
        });

        cvTam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                setLocal("ta");

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

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(Variables.language,"tamil");
                        editor.apply();

                        Intent i = new Intent(getActivity(), MainMenuActivity.class);
                        startActivity(i);
                    }
                });

            }
        });


        cvEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                setLocal("en");

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

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(Variables.language,"english");
                        editor.apply();

//                        Intent i = new Intent(Change_language.this, MainMenuActivity.class);
                        Intent i = new Intent(getActivity(), MainMenuActivity.class);
                        startActivity(i);
                    }
                });

            }
        });

        cvUrdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                setLocal("ur");

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

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(Variables.language,"urdu");
                        editor.apply();

                        Intent i = new Intent(getActivity(), MainMenuActivity.class);
                        startActivity(i);
                    }
                });

            }
        });


        cvKanada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                setLocal("kn");

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

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(Variables.language,"kannada");
                        editor.apply();

                        Intent i = new Intent(getActivity(), MainMenuActivity.class);
                        startActivity(i);
                    }
                });

            }
        });

        cvBengali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                setLocal("bn");

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

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(Variables.language,"bengali");
                        editor.apply();

                        Intent i = new Intent(getActivity(), MainMenuActivity.class);
                        startActivity(i);
                    }
                });

            }
        });

        cvMarathi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                setLocal("mr");

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

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(Variables.language,"marati");
                        editor.apply();

                        Intent i = new Intent(getActivity(), MainMenuActivity.class);
                        startActivity(i);
                    }
                });

            }
        });


        cvGujarati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                setLocal("gu");

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

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(Variables.language,"gujarati");
                        editor.apply();

                        Intent i = new Intent(getActivity(), MainMenuActivity.class);
                        startActivity(i);
                    }
                });

            }
        });

        cvPunjabi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                setLocal("pa");

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

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(Variables.language,"punjabi");
                        editor.apply();

                        Intent i = new Intent(getActivity(), MainMenuActivity.class);
                        startActivity(i);
                    }
                });

            }
        });

        cvOdiya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                setLocal("or");

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

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(Variables.language,"odiya");
                        editor.apply();

                        Intent i = new Intent(getActivity(), MainMenuActivity.class);
                        startActivity(i);
                    }
                });

            }
        });

        cvTelugu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                setLocal("te");

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

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(Variables.language,"telungu");
                        editor.apply();

                        Intent i = new Intent(getActivity(), MainMenuActivity.class);
                        startActivity(i);
                    }
                });

            }
        });

        return view;

    }



    /*private void setLocal(String lang) {

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getActivity().getBaseContext().getResources().updateConfiguration(config,getActivity().getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();

    }*/

    /*public void loadLocale(String lang){

        SharedPreferences prefs = getActivity().getSharedPreferences("Settings", MODE_PRIVATE);
        String language = prefs.getString("My_Lang",lang);
        setLocal(language);
    }*/

/*    //Location
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
                Change_language.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                Change_language.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Variables.latlocation,latitude);
                editor.putString(Variables.lnglocation,longitude);
                editor.apply();


//                getAds();
//                showLocation.setText("Your Location: " + "\n" + "Latitude: " + latitude + "\n" + "Longitude: " + longitude);
            } else {
//                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }*/


}