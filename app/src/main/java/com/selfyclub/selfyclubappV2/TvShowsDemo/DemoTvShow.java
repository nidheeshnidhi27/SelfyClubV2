package com.selfyclub.selfyclubappV2.TvShowsDemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import com.selfyclub.selfyclubappV2.ActivitesFragment.Video_Recording.Video_Recoder_A;
import com.selfyclub.selfyclubappV2.Contest.Contest_A;
import com.selfyclub.selfyclubappV2.R;
import com.selfyclub.selfyclubappV2.SimpleClasses.Variables;

public class DemoTvShow extends AppCompatActivity {

    ScrollView svTvshow;
    FrameLayout flVideo;
    ImageView mov1, mov2, mov3, kid1, kid2, kid3, ser1, ser2, ser3, tvNews, ivContest1, shortFilim1;
    LinearLayout llTvshow, llTvIcon, llYrchnl;
    TextView tvScStar, tvYourChannel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_tv_show);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        svTvshow  = findViewById(R.id.sv_tvshow);
        flVideo = findViewById(R.id.fl_video);
        mov1 = findViewById(R.id.mov1);
        mov2 = findViewById(R.id.mov2);
        mov3 = findViewById(R.id.mov3);
        kid1 = findViewById(R.id.kid1);
        kid2 = findViewById(R.id.kid2);
        kid3 = findViewById(R.id.kid3);
        ser1 = findViewById(R.id.ser1);
        ser2 = findViewById(R.id.ser2);
        ser3 = findViewById(R.id.ser3);
        tvNews = findViewById(R.id.ivnews);
        VideoView videoView = findViewById(R.id.video_view);
        ivContest1 = findViewById(R.id.cont1);
        shortFilim1 = findViewById(R.id.short1);


        llYrchnl = findViewById(R.id.ll_disc_yrchnl);

        llYrchnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(DemoTvShow.this, Video_Recoder_A.class);
                startActivity(i);
            }
        });

        tvYourChannel = findViewById(R.id.tvYourChanel);
        tvYourChannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(DemoTvShow.this);
                builder1.setMessage(R.string.howtocreate);
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Close",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
//                setContentView(image);
                alert11.show();
            }
        });

        tvScStar = findViewById(R.id.tvScStar);

        tvScStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                ImageView image = new ImageView(DemoTvShow.this);
//                image.setImageResource(R.drawable.sccontestrule);

                /*AlertDialog.Builder builder =
                        new AlertDialog.Builder(DemoTvShow.t).
                                setMessage("Message above the image").
                                setPositiveButton("OK", new OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).
                                setView(image);
                builder.create().show();*/

                AlertDialog.Builder builder1 = new AlertDialog.Builder(DemoTvShow.this);
                builder1.setMessage("Complete the all task and make your own space \n"+"Rules and Conditions \n"+"1 : Make 1K Follower own your channel \n"+"2: Video need 1k Like");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Close",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                /*builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });*/

                AlertDialog alert11 = builder1.create();
//                setContentView(image);
                alert11.show();

            }
        });

        flVideo.setVisibility(View.GONE);
        svTvshow.setVisibility(View.VISIBLE);

        llTvshow = findViewById(R.id.ll_demo_tvhead);
        llTvIcon = findViewById(R.id.ll_tv_icon);

        llTvIcon.setVisibility(View.GONE);

        shortFilim1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent icont = new Intent(DemoTvShow.this, DemoShotFilimShow.class);
                startActivity(icont);
            }
        });

        ivContest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent icont = new Intent(DemoTvShow.this, Contest_A.class);
                startActivity(icont);
            }
        });

        tvNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inews = new Intent(DemoTvShow.this,DemoIconList.class);
                startActivity(inews);
            }
        });

        mov1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.yuvam;
                String tvShowDescription = "Yuvam is a 2021 Malayalam political thriller drama film directed by Pinku Peter.[1][2] The movie go through the journey of three young advocates, who tries to prevent KSRTC being privatised due to debts and the inlawful actions by some politicians.";

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Variables.videopath,videoPath);
                editor.apply();

                Intent i = new Intent(DemoTvShow.this,DemoVideoView.class);
                i.putExtra("tvShowDescription",tvShowDescription);
                i.putExtra("picture", R.drawable.mmovie);
                startActivity(i);

            }
        });

        mov2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.arattu;

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(Variables.videopath,videoPath);
                editor.apply();

                Intent i = new Intent(DemoTvShow.this,DemoVideoView.class);
                i.putExtra("tvShowDescription","Neyyattinkara Gopante Aaraattu, or simply Aaraattu (transl. Sacred bath), is an upcoming Indian Malayalam-language action drama co-produced and directed by B. Unnikrishnan and written by Udaykrishna. Starring Mohanlal in the lead role, the film also features Shraddha Srinath, Ramachandra Raju, Nedumudi Venu, Siddique, Prabhakar, Vijayaraghavan, Saikumar, Indrans, Malavika Menon, Swasika, and Rachana Narayanankutty in supporting cast. Rahul Raj composed the original songs and background score.");
                i.putExtra("picture", R.drawable.mmov1);
                startActivity(i);

            }
        });

        mov3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.vellam;

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(Variables.videopath,videoPath);
                editor.apply();

                Intent i = new Intent(DemoTvShow.this,DemoVideoView.class);
                i.putExtra("tvShowDescription","An alcoholic is left stranded by society, friends and family until he successfully completes his de-addiction. Will they accept him when he is clean and sober?\n");
                i.putExtra("picture", R.drawable.mmov1);
                startActivity(i);


            }
        });

        kid1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.kids1;

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(Variables.videopath,videoPath);
                editor.apply();

                Intent i = new Intent(DemoTvShow.this,DemoVideoView.class);
//                i.putExtra("videopath",R.raw.arattu);
                startActivity(i);


            }
        });

        kid2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.kid2;

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(Variables.videopath,videoPath);
                editor.apply();

                Intent i = new Intent(DemoTvShow.this,DemoVideoView.class);
//                i.putExtra("videopath",R.raw.arattu);
                startActivity(i);



            }
        });

        kid3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.kid3;

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(Variables.videopath,videoPath);
                editor.apply();

                Intent i = new Intent(DemoTvShow.this,DemoVideoView.class);
//                i.putExtra("videopath",R.raw.arattu);
                startActivity(i);



            }
        });

        ser1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                svTvshow.setVisibility(View.GONE);
                flVideo.setVisibility(View.VISIBLE);

                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.kudumpavilaku;

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(Variables.videopath,videoPath);
                editor.apply();

                Intent i = new Intent(DemoTvShow.this,DemoVideoView.class);
                startActivity(i);


            }
        });

        ser2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                svTvshow.setVisibility(View.GONE);
                flVideo.setVisibility(View.VISIBLE);

                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.swanthanam;
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(Variables.videopath,videoPath);
                editor.apply();

                Intent i = new Intent(DemoTvShow.this,DemoVideoView.class);
                startActivity(i);


            }
        });

        ser3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                svTvshow.setVisibility(View.GONE);
                flVideo.setVisibility(View.VISIBLE);

                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.vanampadi;
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(Variables.videopath,videoPath);
                editor.apply();

                Intent i = new Intent(DemoTvShow.this,DemoVideoView.class);
                startActivity(i);


            }
        });

    }

}