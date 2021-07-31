package com.selfyclub.selfyclubappV2.TvShowsDemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.selfyclub.selfyclubappV2.R;

public class DemoIconList extends AppCompatActivity {

    CardView cvManorama;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_icon_list);

        cvManorama = findViewById(R.id.cv_nwsmano);
        progressBar = findViewById(R.id.pBar);

        progressBar.setVisibility(View.GONE);

        cvManorama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Intent imano = new Intent(DemoIconList.this,DemoShowIcon.class);
                startActivity(imano);
            }
        });
    }
}