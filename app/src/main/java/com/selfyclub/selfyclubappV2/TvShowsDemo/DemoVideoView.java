package com.selfyclub.selfyclubappV2.TvShowsDemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.selfyclub.selfyclubappV2.R;
import com.selfyclub.selfyclubappV2.SimpleClasses.Variables;

public class DemoVideoView extends AppCompatActivity {

    String videourl, showdescription;
    TextView tvShowDescription;
    VideoView videoView;
    ImageView ivRelatedVideo1, ivFullscreen;
    Boolean isShowing = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_video_view);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        videourl = sharedPreferences.getString(Variables.videopath, null);
        videoView = findViewById(R.id.video_view);
        tvShowDescription = findViewById(R.id.tvShowDescription);
        ivRelatedVideo1 = findViewById(R.id.ivRelatedVideo1);
        ivFullscreen = findViewById(R.id.iv_fullscreen);

        Intent intent = getIntent();
        showdescription = intent.getExtras().getString("tvShowDescription");
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            int picture = bundle.getInt("picture");
            ivRelatedVideo1.setImageResource(picture);
        }

        tvShowDescription.setText(showdescription);

//        String videoPath = "android.resource://" + getPackageName() + "/" + videourl;
        Uri uri = Uri.parse(videourl);

        videoView.setVideoURI(uri);
        videoView.start();

        hideFullscreenDelayed();

        MediaController mediaController = new MediaController(this,true);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        videoView.setOnClickListener(v -> {
            if(isShowing) {
                ivFullscreen.setVisibility(View.GONE);
                isShowing = false;
            }
            else {
                ivFullscreen.setVisibility(View.VISIBLE);
                isShowing = true;
                hideFullscreenDelayed();
            }
        });

    }

    public void hideFullscreenDelayed() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                ivFullscreen.setVisibility(View.GONE);
                isShowing = false;

            }
        }, 5000);

    }

}