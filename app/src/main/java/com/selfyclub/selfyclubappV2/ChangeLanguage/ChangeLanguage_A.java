package com.selfyclub.selfyclubappV2.ChangeLanguage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.selfyclub.selfyclubappV2.Main_Menu.MainMenuActivity;
import com.selfyclub.selfyclubappV2.R;

import java.util.Locale;

public class ChangeLanguage_A extends AppCompatActivity {

    Button btn;

    Button changelang;
    TextView tv1,tv2;
    ImageView ivEng, ivMal, ivTamil, ivHindi;
    CardView cvMala, cvEng, cvHind, cvTam;
    Button btConform;
    RelativeLayout rlMala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_language_);

        ivEng = findViewById(R.id.eng_img);
        ivHindi = findViewById(R.id.hin_img);
        ivMal = findViewById(R.id.mal_img);
        ivTamil = findViewById(R.id.tamil_img);

        cvEng = findViewById(R.id.cv_english);
        cvHind = findViewById(R.id.cv_hi);
        cvMala = findViewById(R.id.cv_mala);
        cvTam = findViewById(R.id.cv_tamil);

        btConform = findViewById(R.id.btn_conform);
        rlMala = findViewById(R.id.rl_malHead);

        tv1 = findViewById(R.id.tv1);

        tv2 = findViewById(R.id.textView);

        cvHind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Intent i = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(i);*/

                setLocal("hi");

                btConform.setVisibility(View.VISIBLE);
                btConform.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        nextpage();
                    }
                });

//                recreate();


//                showChangeLanguageDialog();

            }
        });

        cvMala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Intent i = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(i);*/

                setLocal("ml");
                rlMala.setBackgroundColor(getResources().getColor(R.color.white));

                btConform.setVisibility(View.VISIBLE);
                btConform.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        nextpage();
                    }
                });

//                showChangeLanguageDialog();

            }
        });

        cvTam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Intent i = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(i);*/

                setLocal("ta");
//                recreate();
                btConform.setVisibility(View.VISIBLE);
                btConform.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        nextpage();
                    }
                });
            }
        });

        cvEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Intent i = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(i);*/

                setLocal("en");
//                recreate();
                btConform.setVisibility(View.VISIBLE);
                btConform.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        nextpage();
                    }
                });
            }
        });
    }

    private void showChangeLanguageDialog() {
        final String[] listItems = {"हिंदी","മലയാളം","English"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(ChangeLanguage_A.this);
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

    }

    private void nextpage() {

        Intent i = new Intent(ChangeLanguage_A.this,MainMenuActivity.class);
        ChangeLanguage_A.this.finish();
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

    public void loadLocale(){

        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang","");
        setLocal(language);
    }

}