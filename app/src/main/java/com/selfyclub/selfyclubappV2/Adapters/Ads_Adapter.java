package com.selfyclub.selfyclubappV2.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selfyclub.selfyclubappV2.R;

import java.util.ArrayList;

public class Ads_Adapter extends RecyclerView.Adapter<Ads_Adapter.ViewHolder> {


    Uri uri;

    Context context;

    public Ads_Adapter(Context context, ArrayList<Uri> adUris) {

        this.context = context;
        this.uri = adUris.get(0);
    }


    @NonNull
    @Override
    public Ads_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_ads, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Ads_Adapter.ViewHolder holder, int position) {

        holder.vvAds.setVideoURI(uri);
        holder.vvAds.start();

    }


    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        VideoView vvAds;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            vvAds = itemView.findViewById(R.id.vv_ads);

        }
    }
}
