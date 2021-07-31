package com.selfyclub.selfyclubappV2.Adapters;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.selfyclub.selfyclubappV2.Models.Ads_Get_Model;
import com.selfyclub.selfyclubappV2.Models.Gif;
import com.selfyclub.selfyclubappV2.Models.ImageModel;
import com.selfyclub.selfyclubappV2.Models.Msg;
import com.selfyclub.selfyclubappV2.Models.VideoAd;
import com.facebook.drawee.view.SimpleDraweeView;
import com.selfyclub.selfyclubappV2.Models.Discover_Get_Set;
import com.selfyclub.selfyclubappV2.Models.Home_Get_Set;
import com.selfyclub.selfyclubappV2.R;
import com.selfyclub.selfyclubappV2.SimpleClasses.Functions;
import com.selfyclub.selfyclubappV2.SimpleClasses.Variables;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by AQEEL on 3/20/2018.
 */

public class Discover_Adapter extends RecyclerView.Adapter<Discover_Adapter.CustomViewHolder > implements Filterable {
    public Context context;

    ArrayList<Discover_Get_Set> datalist;
    ArrayList<Discover_Get_Set> datalist_filter;

    JSONObject jsonResponse;
    Ads_Get_Model parsedResponse;
    Msg msg;


    public interface OnItemClickListener {
        void onItemClick(ArrayList<Home_Get_Set> video_list, int postion);
    }

    public Discover_Adapter.OnItemClickListener listener;

    public Discover_Adapter(Context context, ArrayList<Discover_Get_Set> arrayList, Discover_Adapter.OnItemClickListener listener) {
        this.context = context;
        datalist = arrayList;
        datalist_filter=arrayList;
        this.listener = listener;
    }


    @Override
    public Discover_Adapter.CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewtype) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_discover_layout, viewGroup, false);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        Discover_Adapter.CustomViewHolder viewHolder = new Discover_Adapter.CustomViewHolder(view);
        return viewHolder;
    }


    @Override
    public int getItemCount() {
        return datalist_filter.size();
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {

        RecyclerView horizontal_reycerview;

        VideoView vvDiscoverAds;

        ImageView ivDiscoverAds;

        GifImageView givDiscoverAds;

        TextView title,views_txt;


        public CustomViewHolder(View view) {
            super(view);

            horizontal_reycerview = view.findViewById(R.id.horizontal_recylerview);
            title = view.findViewById(R.id.title);
            views_txt=view.findViewById(R.id.views_txt);

            vvDiscoverAds = view.findViewById(R.id.vv_discover_ads);
            ivDiscoverAds = view.findViewById(R.id.iv_discover_ads);
            givDiscoverAds = view.findViewById(R.id.gif_discover_ads);

        }


    }


    @Override
    public void onBindViewHolder(final Discover_Adapter.CustomViewHolder holder, final int i) {

        Discover_Get_Set item = datalist_filter.get(i);

        holder.title.setText(item.title);
        holder.views_txt.setText(Functions.GetSuffix(item.videos_count));

        Horizontal_Adapter adapter = new Horizontal_Adapter(context, item.arrayList);


        if((i+1) % 3 == 0) {

            holder.title.setVisibility(View.INVISIBLE);
            getAdd(holder.vvDiscoverAds, holder.ivDiscoverAds, holder.givDiscoverAds);

        }
        else
        {

            holder.title.setVisibility(View.VISIBLE);
            holder.horizontal_reycerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            holder.horizontal_reycerview.setAdapter(adapter);

        }

//        Horizontal_Adapter adapter = new Horizontal_Adapter(context, item.arrayList);
        holder.horizontal_reycerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.horizontal_reycerview.setAdapter(adapter);

        }

    private void getAdd(VideoView vv, ImageView iv, GifImageView giv){

        RequestQueue queue = Volley.newRequestQueue(context);

        String url = "https://www.selfyclub.com/mobileapp_api/api/showAds";
        JSONObject jsonBody = new JSONObject();
        Gson gson = new Gson();
        try {

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String Latitude = sharedPreferences.getString(Variables.latlocation, null);
            String Longitude = sharedPreferences.getString(Variables.lnglocation, null);

//            jsonBody.put("zipLat", 2.04974733);
//            jsonBody.put("ziplng", -8.84870721);

            jsonBody.put("zipLat", Latitude);
            jsonBody.put("ziplng", Longitude);
            jsonBody.put("distance", "10");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                jsonResponse = response;

                try {

                    parsedResponse = gson.fromJson(response.toString(), Ads_Get_Model.class);

                    msg = parsedResponse.getMsg();
//                showAds(response.toString());

                    ArrayList<Msg> msgArray = new ArrayList<Msg>(Collections.singleton(msg));
                    if(msgArray.size() > 0)
                        showAds(parsedResponse, jsonResponse, vv, iv, giv);
                    Log.e("","");


                } catch (IllegalStateException e) {

                    e.printStackTrace();
                    Log.e("IllegalStateException",e.getMessage());
                }catch(Exception e1){
                    e1.printStackTrace();
                    Log.e("Exception",e1.getMessage());
                }
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

    private void showAds(Ads_Get_Model resp, JSONObject obj, VideoView vv, ImageView iv, GifImageView giv){

//        Glide.with(this).load(R.drawable.timergif).into(imageView);


        msg = resp.getMsg();
        List<VideoAd> videos = msg.getVideos();
        List<Gif> gifs = msg.getGifs();
        List<ImageModel> images = msg.getImages();

        ArrayList<String> newList = new ArrayList<String>();
//        newList.add("video");
        newList.add("gif");
        newList.add("image");

        Random rand = new Random();
        int newRand = rand.nextInt(2);
        String selected = newList.get(newRand);

        try{

            JSONObject jsonObject = obj;

            JSONObject itemsObject = (JSONObject)jsonObject.get("msg");
            if(itemsObject.length() > 0) {

                Uri uri;

                switch (selected){
                    case "video":
                        if (videos.size() > 0) {
                            vv.setVisibility(View.VISIBLE);
                            giv.setVisibility(View.GONE);
                            iv.setVisibility(View.GONE);
                            Random vRand = new Random();
                            int newVRand = vRand.nextInt(videos.size());
                            uri = Uri.parse(videos.get(newVRand).getAdfile());
                            vv.setMediaController(new MediaController(context));
                            vv.setVideoURI(uri);
                            vv.requestFocus();
                            vv.start();
                        }
                        break;
                    case "gif":
                        if (gifs.size() > 0) {
                            vv.setVisibility(View.GONE);
                            giv.setVisibility(View.VISIBLE);
                            iv.setVisibility(View.GONE);

                            Random gRand = new Random();
                            int newGRand = gRand.nextInt(gifs.size());
                            uri = Uri.parse(gifs.get(newGRand).getAdfile());

                            Glide
                                    .with(context)
                                    .load(uri)
                                    .into(giv);
                        }
                        break;
                    case "image":
                        if (images.size() > 0) {
                            vv.setVisibility(View.GONE);
                            giv.setVisibility(View.GONE);
                            iv.setVisibility(View.VISIBLE);

                            Random iRand = new Random();
                            int newIRand = iRand.nextInt(images.size());
                            uri = Uri.parse(images.get(newIRand).getAdfile());
                            Glide
                                    .with(context)
                                    .load(uri)
                                    .into(iv);
                            iv.setScaleType(ScaleType.FIT_XY);
                        }
                        break;
                    default: uri = null;

                }

                //adsView.setVideoPath(itemObject.getString("name"));





            }
//            }
            ////////////////////////////
//gif.bringToFront();

            ////////////////////
        }catch (Exception e){

            e.printStackTrace();
        }


    }


    // that function will filter the result
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    datalist_filter = datalist;
                } else {
                    ArrayList<Discover_Get_Set> filteredList = new ArrayList<>();
                    for (Discover_Get_Set row : datalist) {


                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.title.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    datalist_filter = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = datalist_filter;
                return filterResults;

            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                datalist_filter = (ArrayList<Discover_Get_Set>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    class Horizontal_Adapter extends RecyclerView.Adapter<Horizontal_Adapter.CustomViewHolder> {
        public Context context;

        ArrayList<Home_Get_Set> datalist;


        public Horizontal_Adapter(Context context, ArrayList<Home_Get_Set> arrayList) {
            this.context = context;
            datalist = arrayList;
        }

        @Override
        public Horizontal_Adapter.CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewtype) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_discover_horizontal_layout, viewGroup, false);
            view.setLayoutParams(new RecyclerView.LayoutParams((Variables.screen_width/3)-20, RecyclerView.LayoutParams.WRAP_CONTENT));
            Horizontal_Adapter.CustomViewHolder viewHolder = new Horizontal_Adapter.CustomViewHolder(view);
            return viewHolder;
        }

        @Override
        public int getItemCount() {
            return datalist.size();
        }

        class CustomViewHolder extends RecyclerView.ViewHolder {

            SimpleDraweeView video_thumbnail;



            public CustomViewHolder(View view) {
                super(view);
                video_thumbnail = view.findViewById(R.id.video_thumbnail);

            }

            public void bind(final int pos, final ArrayList<Home_Get_Set> datalist) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(datalist,pos);
                    }
                });
            }


        }

        @Override
        public void onBindViewHolder(final Horizontal_Adapter.CustomViewHolder holder, final int i) {
            holder.setIsRecyclable(false);


            Home_Get_Set item = datalist.get(i);
            holder.bind(i, datalist);

            try {

                if(Variables.is_show_gif) {
                    Glide.with(context)
                            .asGif()
                            .load(item.gif)
                            .skipMemoryCache(true)
                            .thumbnail(new RequestBuilder[]{Glide
                                    .with(context)
                                    .load(item.thum)})
                            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE)
                                    .placeholder(context.getResources().getDrawable(R.drawable.image_placeholder)).centerCrop())
                            .into(holder.video_thumbnail);
                }
                else {
                    if(item.thum!=null && !item.thum.equals("")) {
                        Uri uri = Uri.parse(item.thum);
                        holder.video_thumbnail.setImageURI(uri);
                    }
                }
            }
            catch (Exception e){
                Log.d(Variables.tag,e.toString());
            }




        }



    }


}