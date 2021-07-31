package com.selfyclub.selfyclubappV2.ActivitesFragment;


import android.Manifest;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.selfyclub.selfyclubappV2.ActivitesFragment.Accounts.Login_A;
import com.selfyclub.selfyclubappV2.ActivitesFragment.Profile.Profile_F;
import com.selfyclub.selfyclubappV2.ActivitesFragment.SoundLists.VideoSound_A;
import com.selfyclub.selfyclubappV2.ActivitesFragment.Video_Recording.Video_Recoder_Duet_A;
import com.selfyclub.selfyclubappV2.Adapters.Home_Adapter;
import com.selfyclub.selfyclubappV2.ApiClasses.ApiLinks;
import com.selfyclub.selfyclubappV2.ApiClasses.ApiRequest;
import com.selfyclub.selfyclubappV2.BuildConfig;
import com.selfyclub.selfyclubappV2.Interfaces.API_CallBack;
import com.selfyclub.selfyclubappV2.Interfaces.Callback;
import com.selfyclub.selfyclubappV2.Interfaces.Fragment_Callback;
import com.selfyclub.selfyclubappV2.Interfaces.Fragment_Data_Send;
import com.selfyclub.selfyclubappV2.Main_Menu.MainMenuFragment;
import com.selfyclub.selfyclubappV2.Main_Menu.RelateToFragment_OnBack.RootFragment;
import com.selfyclub.selfyclubappV2.Models.Ads_Get_Model;
import com.selfyclub.selfyclubappV2.Models.Gif;
import com.selfyclub.selfyclubappV2.Models.Home_Get_Set;
import com.selfyclub.selfyclubappV2.Models.ImageModel;
import com.selfyclub.selfyclubappV2.Models.Msg;
import com.selfyclub.selfyclubappV2.Models.UpdatePopupModel;
import com.selfyclub.selfyclubappV2.Models.VideoAd;
import com.selfyclub.selfyclubappV2.R;
import com.selfyclub.selfyclubappV2.Services.Upload_Service;
import com.selfyclub.selfyclubappV2.SimpleClasses.Functions;
import com.selfyclub.selfyclubappV2.SimpleClasses.TicTic;
import com.selfyclub.selfyclubappV2.SimpleClasses.Variables;
import com.danikula.videocache.HttpProxyCacheServer;
import com.downloader.Error;
import com.downloader.OnDownloadListener;
import com.downloader.OnProgressListener;
import com.downloader.PRDownloader;
import com.downloader.Progress;
import com.downloader.request.DownloadRequest;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.volokh.danylo.hashtaghelper.HashTagHelper;

import org.json.JSONArray;
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
 * A simple {@link Fragment} subclass.
 */

// this is the main view which is show all  the video in list
public class Home_F extends RootFragment implements Player.EventListener, Fragment_Data_Send, View.OnClickListener {

    View view;
    Context context;
    ObjectAnimator animX, animTranslationX;
    ObjectAnimator objectAnimator;
    RecyclerView recyclerView, rvLocationFilter;
    ArrayList<Home_Get_Set> data_list;
    int currentPage = -1;
    LinearLayoutManager layoutManager;
    ProgressBar p_bar;
    ImageView sound_single_note, sound_double_note;
    SwipeRefreshLayout swiperefresh;
    boolean is_user_stop_video = false;
    Boolean doubleBackToExitPressedOnce = false;

    ArrayList<String> locs = new ArrayList<String>();

    private static final int REQUEST_LOCATION = 1;

    int counter = 5;
    Msg msg = null;
    Ads_Get_Model parsedResponse;
    JSONObject jsonResponse;

    TextView following_btn, related_btn;
    String type = "related";

    public Home_F() {
        // Required empty public constructor
    }

    int swipe_count = 0;


    int page_count = 0;
    boolean is_api_runing = false;
    Handler handler;
    Runnable runnable;
    Boolean animation_running = false;

    RelativeLayout upload_video_layout;
    ImageView uploading_thumb;
    ImageView uploading_icon;
    UploadingVideoBroadCast mReceiver;

    LinearLayout llNearby, llLocaFilter;
    ImageView ivLocImg, ivFliter;
    TextView tvNearby;
    String Latitude, Longitude;
    RadioGroup rgLoc;
    Button btnOk;
    String selectedLoc = null;

    int selectId = -1;

    private class UploadingVideoBroadCast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (Functions.isMyServiceRunning(context, Upload_Service.class)) {
                upload_video_layout.setVisibility(View.VISIBLE);
                Bitmap bitmap = Functions.Base64_to_bitmap(Variables.sharedPreferences.getString(Variables.uploading_video_thumb, ""));
                if (bitmap != null)
                    uploading_thumb.setImageBitmap(bitmap);

            } else {
                upload_video_layout.setVisibility(View.GONE);
            }

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        context = getContext();

        checkVersion();

        rgLoc = view.findViewById(R.id.rg_loc);
        llLocaFilter = view.findViewById(R.id.ll_loc_filter);
        btnOk = view.findViewById(R.id.btn_ok);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Latitude = sharedPreferences.getString(Variables.latlocation, null);
        Longitude = sharedPreferences.getString(Variables.lnglocation, null);

        rvLocationFilter = view.findViewById(R.id.rvLocationFilter);



        //sample lat
//        TextView tvLat = view.findViewById(R.id.tvLat);
//        tvLat.setText(Latitude);


        Runnable REPEAT_ANIMATION = new Runnable() {
            @Override
            public void run() {
                ivLocImg.animate().rotationYBy(360).setDuration(5000).withEndAction(this::run).start();
            }
        };

        llNearby = view.findViewById(R.id.ll_nearby);
        ivLocImg = view.findViewById(R.id.iv_loc_img);
        tvNearby = view.findViewById(R.id.tv_loc_base);
        ivFliter = view.findViewById(R.id.ivfilter);

        if (Latitude == null || Longitude == null)
            llNearby.setVisibility(View.GONE);

        ivLocImg.animate().rotationYBy(360).setDuration(5000).withEndAction(REPEAT_ANIMATION).start();

        llNearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ivFliter.setVisibility(View.VISIBLE);

                related_btn.setTextColor(context.getResources().getColor(R.color.graycolor2));
                following_btn.setTextColor(context.getResources().getColor(R.color.graycolor2));
                tvNearby.setTextColor(context.getResources().getColor(R.color.white));

                callApiForNearbyVideo();

            }
        });

        ivFliter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                callApiForLocationFlter();

            }
        });


        p_bar = view.findViewById(R.id.p_bar);
        handler = new Handler();
        following_btn = view.findViewById(R.id.following_btn);
        related_btn = view.findViewById(R.id.related_btn);

        following_btn.setOnClickListener(this);
        related_btn.setOnClickListener(this);

        recyclerView = view.findViewById(R.id.recylerview);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(false);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        // this is the scroll listener of recycler view which will tell the current item number
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //here we find the current item number
                final int scrollOffset = recyclerView.computeVerticalScrollOffset();
                final int height = recyclerView.getHeight();
                int page_no = scrollOffset / height;

                if (page_no != currentPage) {
                    currentPage = page_no;

                    Release_Privious_Player();
                    Set_Player(currentPage);

                    if (!is_api_runing && currentPage == (data_list.size() - 1)) {
                        page_count = page_count + 1;
                        Call_video_api();
                    }

                }
            }
        });


        swiperefresh = view.findViewById(R.id.swiperefresh);
        swiperefresh.setProgressViewOffset(false, 0, 200);

        swiperefresh.setColorSchemeResources(R.color.black);
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page_count = 0;
                Call_video_api();
            }
        });

        Call_video_api();

        if (!Variables.is_remove_ads)
            Load_add();

        upload_video_layout = view.findViewById(R.id.upload_video_layout);
        uploading_thumb = view.findViewById(R.id.uploading_thumb);
        uploading_icon = view.findViewById(R.id.uploading_icon);

        mReceiver = new UploadingVideoBroadCast();
        getActivity().registerReceiver(mReceiver, new IntentFilter("uploadVideo"));


        if (Functions.isMyServiceRunning(context, Upload_Service.class)) {
            upload_video_layout.setVisibility(View.VISIBLE);
            Bitmap bitmap = Functions.Base64_to_bitmap(Variables.sharedPreferences.getString(Variables.uploading_video_thumb, ""));
            if (bitmap != null)
                uploading_thumb.setImageBitmap(bitmap);
        }


        return view;
    }

    private void callApiForLocationFlter() {

        locs = null;
        locs = new ArrayList<String>();
        ivFliter.setClickable(false);

        JSONObject parameters = new JSONObject();

        ApiRequest.Call_Api(getActivity(), ApiLinks.showAllVideolocations, parameters, new Callback() {
            @Override
            public void Responce(String resp) {
                Log.e("","");
                try {
                    JSONObject parsedResponse = new JSONObject(resp);
                    JSONArray jsonArray = parsedResponse.getJSONArray("msg");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject videoObject = jsonArray.getJSONObject(i).getJSONObject("Video");
                        String loc = videoObject.getString("locality");
                        locs.add(loc);
                    }

//                    androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(getContext());
//                    alertDialog.setTitle("Select location");
//                    int checkItem = 1;
//                    alertDialog.setSingleChoiceItems(locs, checkItem, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            String selectedLoc = locs;
//                            dialog.dismiss();
//                        }
//                    });
//                    alertDialog.show();

                    rgLoc.removeAllViews();

                    for(int i = 0; i < locs.size(); i++) {
                        RadioButton radioButton = new RadioButton(getContext());
                        radioButton.setId(i);
                        if(selectId != -1 && i == selectId){
                            radioButton.setChecked(true);
                        }

                        radioButton.setText(locs.get(i));
                        radioButton.setTextColor(getResources().getColor(R.color.black));
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        radioButton.setPadding(10, 10, 10, 10);
                        radioButton.setButtonTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.holo_blue_light)));
                        rgLoc.addView(radioButton, i, layoutParams);
                    }

                    if (locs.size()>0)
                        llLocaFilter.setVisibility(View.VISIBLE);
                    else
                        Toast.makeText(context, "No data available. Please try again later", Toast.LENGTH_SHORT).show();

                    rgLoc.setOnCheckedChangeListener((group, checkedId) -> {
                        selectedLoc = locs.get(checkedId);
                        selectId = checkedId;
                        rgLoc.check(checkedId);

                    });

                    btnOk.setOnClickListener(v -> {
                        llLocaFilter.setVisibility(View.GONE);
                        ivFliter.setClickable(true);
//                        Toast.makeText(context, selectedLoc, Toast.LENGTH_SHORT).show();
                        callApiForLocationFilterVideo(selectedLoc);
                    });




//                    Location_Filter_Adapter location_filter_adapter = new Location_Filter_Adapter(getContext(),locs);
//                    rvLocationFilter.setAdapter(location_filter_adapter);
//                    rvLocationFilter.setLayoutManager(new LinearLayoutManager(getContext()));

                    Log.i("info : ", String.valueOf(locs));

                }
                catch(Exception e) {
                    Log.e("Caught Exception : ", e.getMessage());
                }
            }
        });


    }

    private void callApiForLocationFilterVideo(String selectedLoc) {

        JSONObject parameters = new JSONObject();
        try {
            parameters.put("locality",selectedLoc);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiRequest.Call_Api(getActivity(), ApiLinks.showVideosAgainstLocality, parameters, resp -> {
            Log.e("","");
            swiperefresh.setRefreshing(false);
            Parse_data(resp);


        });


    }


    private void callApiForNearbyVideo() {

        JSONObject parameters = new JSONObject();
        try {

            page_count=0;

            if (Variables.sharedPreferences.getString(Variables.u_id, null) != null) {
                parameters.put("user_id", Variables.sharedPreferences.getString(Variables.u_id, "0"));
            }
            parameters.put("device_id", Variables.sharedPreferences.getString(Variables.device_id, "0"));
            parameters.put("starting_point", "" + page_count);
            parameters.put("zipLat",Latitude);
            parameters.put("zipLng",Longitude);
            parameters.put("distance","20");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiRequest.Call_Api(getActivity(), ApiLinks.showRelatedVideosAgainstLocation, parameters, new Callback() {
            @Override
            public void Responce(String resp) {
                swiperefresh.setRefreshing(false);
                Parse_data(resp);


            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.following_btn:

                if (Variables.sharedPreferences.getBoolean(Variables.islogin, false)) {
                    type = "following";
                    ivFliter.setVisibility(View.GONE);
                    swiperefresh.setRefreshing(true);
                    related_btn.setTextColor(context.getResources().getColor(R.color.graycolor2));
                    following_btn.setTextColor(context.getResources().getColor(R.color.white));
                    tvNearby.setTextColor(context.getResources().getColor(R.color.graycolor2));
                    page_count = 0;
                    Call_video_api();
                } else {
                    Open_Login();
                }
                break;

            case R.id.related_btn:
                type = "related";
                ivFliter.setVisibility(View.GONE);
                swiperefresh.setRefreshing(true);
                related_btn.setTextColor(context.getResources().getColor(R.color.white));
                following_btn.setTextColor(context.getResources().getColor(R.color.graycolor2));
                tvNearby.setTextColor(context.getResources().getColor(R.color.graycolor2));
                page_count = 0;
                Call_video_api();
                break;

            default:
                return;
        }

    }


    public void Call_video_api() {
        is_api_runing = true;

        if (type.equalsIgnoreCase("following")) {
            Call_Api_For_get_Followingvideos();
        } else {
            Call_Api_For_get_Allvideos();
        }
    }


    // Bottom two function will call the api and get all the videos form api and parse the json data
    private void Call_Api_For_get_Allvideos() {
        JSONObject parameters = new JSONObject();
        try {

            if (Variables.sharedPreferences.getString(Variables.u_id, null) != null) {
                parameters.put("user_id", Variables.sharedPreferences.getString(Variables.u_id, "0"));
            }
            parameters.put("device_id", Variables.sharedPreferences.getString(Variables.device_id, "0"));
            parameters.put("starting_point", "" + page_count);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiRequest.Call_Api(getActivity(), ApiLinks.showRelatedVideos, parameters, new Callback() {
            @Override
            public void Responce(String resp) {
                swiperefresh.setRefreshing(false);
                Parse_data(resp);
            }
        });


    }

    private void Call_Api_For_get_Followingvideos() {


        JSONObject parameters = new JSONObject();
        try {

            if (Variables.sharedPreferences.getString(Variables.u_id, null) != null)
                parameters.put("user_id", Variables.sharedPreferences.getString(Variables.u_id, "0"));

            parameters.put("device_id", Variables.sharedPreferences.getString(Variables.device_id, "0"));
            parameters.put("starting_point", "" + page_count);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiRequest.Call_Api(getActivity(), ApiLinks.showFollowingVideos, parameters, new Callback() {
            @Override
            public void Responce(String resp) {
                swiperefresh.setRefreshing(false);
                Parse_data(resp);
            }
        });


    }


    public void Parse_data(String responce) {
        try {
            JSONObject jsonObject = new JSONObject(responce);
            String code = jsonObject.optString("code");

            if (code.equals("200")) {
                JSONArray msgArray = jsonObject.getJSONArray("msg");

                ArrayList<Home_Get_Set> temp_list = new ArrayList();
                for (int i = 0; i < msgArray.length(); i++) {
                    JSONObject itemdata = msgArray.optJSONObject(i);

                    JSONObject Video = itemdata.optJSONObject("Video");
                    JSONObject User = itemdata.optJSONObject("User");
                    JSONObject Sound = itemdata.optJSONObject("Sound");
                    JSONObject UserPrivacy = User.optJSONObject("PrivacySetting");
                    JSONObject UserPushNotification = User.optJSONObject("PushNotification");

                    Home_Get_Set item = Functions.Parse_video_data(User, Sound, Video, UserPrivacy, UserPushNotification);


                    if (Variables.is_demo_app) {
                        if (i < Variables.demo_app_videos_count)
                            temp_list.add(item);
                    } else {
                        temp_list.add(item);
                    }

                }

                if (page_count == 0) {
                    currentPage = -1;
                    data_list = new ArrayList<>();
                    data_list.addAll(temp_list);
                    Set_Adapter();
                } else if (page_count > 0) {
                    data_list.addAll(temp_list);
                    adapter.notifyDataSetChanged();
                } else if (temp_list.isEmpty() && type.equalsIgnoreCase("following")) {
                    Functions.show_toast(getActivity(),"Follow an account to see there videos here.");
                    type = "related";
                    related_btn.setTextColor(context.getResources().getColor(R.color.white));
                    following_btn.setTextColor(context.getResources().getColor(R.color.graycolor2));
                }

            } else {
                Functions.show_toast(getActivity(),jsonObject.optString("msg"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            is_api_runing = false;
        }

    }


    private void Call_Api_For_Singlevideos(final int postion) {

        JSONObject parameters = new JSONObject();
        try {
            if (Variables.sharedPreferences.getString(Variables.u_id, null) != null)
                parameters.put("user_id", Variables.sharedPreferences.getString(Variables.u_id, "0"));

            parameters.put("video_id", data_list.get(postion).video_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        ApiRequest.Call_Api(getActivity(), ApiLinks.showVideoDetail, parameters, new Callback() {
            @Override
            public void Responce(String resp) {
                swiperefresh.setRefreshing(false);
                Singal_Video_Parse_data(postion, resp);
            }
        });

    }

    public void Singal_Video_Parse_data(int pos, String responce) {

        try {
            JSONObject jsonObject = new JSONObject(responce);
            String code = jsonObject.optString("code");
            if (code.equals("200")) {
                JSONObject msg = jsonObject.optJSONObject("msg");

                JSONObject Video = msg.optJSONObject("Video");
                JSONObject User = msg.optJSONObject("User");
                JSONObject Sound = msg.optJSONObject("Sound");
                JSONObject UserPrivacy = User.optJSONObject("PrivacySetting");
                JSONObject UserPushNotification = User.optJSONObject("PushNotification");

                Home_Get_Set item = Functions.Parse_video_data(User, Sound, Video, UserPrivacy, UserPushNotification);

                data_list.remove(pos);
                data_list.add(pos, item);
                adapter.notifyDataSetChanged();


            } else {
                Functions.show_toast(getActivity(),jsonObject.optString("msg"));
            }

        } catch (JSONException e) {

            e.printStackTrace();
        }

    }


    boolean is_add_show = false;
    Home_Adapter adapter;

    public void Set_Adapter() {

//        runTimer();
        getAdd();

        adapter = new Home_Adapter(context, data_list, new Home_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int postion, final Home_Get_Set item, View view) {
                switch (view.getId()) {

                    case R.id.user_pic:
                        onPause();
                        OpenProfile(item, false);
                        break;

                    case R.id.username:
                        onPause();
                        OpenProfile(item, false);
                        break;

                    case R.id.comment_layout:
                        OpenComment(item);
                        break;

                    case R.id.animate_rlt:
                        if (Variables.sharedPreferences.getBoolean(Variables.islogin, false)) {

                        } else {
                            Intent intent = new Intent(getActivity(), Login_A.class);
                            startActivity(intent);
                            getActivity().overridePendingTransition(R.anim.in_from_bottom, R.anim.out_to_top);
                            adapter.notifyDataSetChanged();
                        }
                        break;

                    case R.id.shared_layout:
                        if (!is_add_show && (mInterstitialAd != null && mInterstitialAd.isLoaded())) {
                            mInterstitialAd.show();
                            is_add_show = true;
                        } else {
                            is_add_show = false;
                            final VideoAction_F fragment = new VideoAction_F(item.video_id, new Fragment_Callback() {
                                @Override
                                public void Responce(Bundle bundle) {

                                    if (bundle.getString("action").equals("save")) {
                                        Save_Video(item);
                                    }

                                    else if (bundle.getString("action").equals("duet")) {

                                        Duet_video(item);
                                    }

                                    else if (bundle.getString("action").equals("privacy")) {

                                        Privacy_Video_Setting privacy_video_setting = new Privacy_Video_Setting(new Fragment_Callback() {
                                            @Override
                                            public void Responce(Bundle bundle) {
                                                if (bundle != null) {
                                                    if (bundle.getBoolean("call_api")) {
                                                        Call_Api_For_Singlevideos(postion);
                                                    }
                                                }
                                            }
                                        });
                                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                        transaction.setCustomAnimations(R.anim.in_from_bottom, R.anim.out_to_top, R.anim.in_from_top, R.anim.out_from_bottom);
                                        Bundle bundle1 = new Bundle();
                                        bundle1.putString("video_id", item.video_id);
                                        bundle1.putString("privacy_value", item.privacy_type);
                                        bundle1.putString("duet_value", item.allow_duet);
                                        bundle1.putString("comment_value", item.allow_comments);
                                        bundle1.putString("duet_video_id", item.duet_video_id);
                                        privacy_video_setting.setArguments(bundle1);
                                        transaction.addToBackStack(null);
                                        transaction.replace(R.id.WatchVideo_F, privacy_video_setting).commit();
                                        onPause();
                                    }

                                    else if (bundle.getString("action").equals("delete")) {
                                        Functions.Show_loader(context, false, false);
                                        Functions.Call_Api_For_Delete_Video(getActivity(), item.video_id, new API_CallBack() {
                                            @Override
                                            public void ArrayData(ArrayList arrayList) {
                                                //return data in case of array list
                                            }

                                            @Override
                                            public void OnSuccess(String responce) {
                                                data_list.remove(currentPage);
                                                adapter.notifyDataSetChanged();

                                            }

                                            @Override
                                            public void OnFail(String responce) {
                                                // call when api return faild responce
                                            }
                                        });

                                    }

                                    else if (bundle.getString("action").equals("favourite")) {
                                        Favourite_video(item);
                                    }

                                    else if (bundle.getString("action").equals("not_intrested")) {
                                        Not_Interest_video(item);
                                    }

                                    else if (bundle.getString("action").equals("report")) {
                                        Open_video_report(item);
                                    }


                                }
                            });


                            Bundle bundle = new Bundle();
                            bundle.putString("video_id", item.video_id);
                            bundle.putString("user_id", item.user_id);
                            bundle.putSerializable("data", item);
                            fragment.setArguments(bundle);
                            fragment.show(getChildFragmentManager(), "");
                        }

                        break;


                    case R.id.sound_image_layout:

                        if (check_permissions()) {

                            Intent intent = new Intent(getActivity(), VideoSound_A.class);
                            intent.putExtra("data", item);
                            startActivity(intent);
                        }

                        break;

                    case R.id.duet_open_video:
                        Open_duet_video(item);
                        break;

                    default:
                        return;
                }

            }
        }, new Home_Adapter.LikedClicked() {
            @Override
            public void like_clicked(View view, Home_Get_Set item, int position) {
                view.animate().cancel();
                if (Variables.sharedPreferences.getBoolean(Variables.islogin, false)) {
                    view.animate().start();
                    Like_Video(position, item);
                } else {
                    view.animate().cancel();
                    Intent intent = new Intent(getActivity(), Login_A.class);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.in_from_bottom, R.anim.out_to_top);
                }
            }
        });

        adapter.setHasStableIds(true);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    private void Open_duet_video(Home_Get_Set item) {
        Intent intent123 = new Intent(getActivity(), WatchVideos_F.class);
        intent123.putExtra("video_id", item.duet_video_id);
        startActivity(intent123);
    }

    // this will call when swipe for another video and
    // this function will set the player to the current video
    public void Set_Player(final int currentPage) {

        final Home_Get_Set item = data_list.get(currentPage);

        Call_cache();

        HttpProxyCacheServer proxy = TicTic.getProxy(context);
        String proxyUrl = proxy.getProxyUrl(item.video_url);

        LoadControl loadControl = new DefaultLoadControl.Builder()
                .setAllocator(new DefaultAllocator(true, 16))
                .setBufferDurationsMs(1 * 1024, 1 * 1024, 500, 1024)
                .setTargetBufferBytes(-1)
                .setPrioritizeTimeOverSizeThresholds(true)
                .createDefaultLoadControl();

        DefaultTrackSelector trackSelector = new DefaultTrackSelector();
        final SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);

        SimpleCache simpleCache = TicTic.simpleCache;
        CacheDataSourceFactory cacheDataSourceFactory = new CacheDataSourceFactory(simpleCache, new DefaultHttpDataSourceFactory(Util.getUserAgent(getActivity(), "BubbleTok"))
                , CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR);

        ProgressiveMediaSource videoSource = new ProgressiveMediaSource.Factory(cacheDataSourceFactory).createMediaSource(Uri.parse(item.video_url));

        Log.d(Variables.tag, item.video_url);
        // Log.d(Variables.tag,proxyUrl);


        player.prepare(videoSource);

        player.setRepeatMode(Player.REPEAT_MODE_ALL);
        player.addListener(this);


        View layout = layoutManager.findViewByPosition(currentPage);
        final PlayerView playerView = layout.findViewById(R.id.playerview);
        playerView.setPlayer(player);


        player.setPlayWhenReady(is_visible_to_user);
        privious_player = player;


        final RelativeLayout mainlayout = layout.findViewById(R.id.mainlayout);
        playerView.setOnTouchListener(new View.OnTouchListener() {
            private GestureDetector gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    super.onFling(e1, e2, velocityX, velocityY);
                    float deltaX = e1.getX() - e2.getX();
                    float deltaXAbs = Math.abs(deltaX);
                    // Only when swipe distance between minimal and maximal distance value then we treat it as effective swipe
                    if ((deltaXAbs > 100) && (deltaXAbs < 1000)) {
                        if (deltaX > 0) {
                            OpenProfile(item, true);
                        }
                    }


                    return true;
                }

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    super.onSingleTapUp(e);
                    if (!player.getPlayWhenReady()) {
                        is_user_stop_video = false;
                        privious_player.setPlayWhenReady(true);
                    } else {
                        is_user_stop_video = true;
                        privious_player.setPlayWhenReady(false);
                    }


                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    super.onLongPress(e);


                    Show_video_option(item);

                }

                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    if (!player.getPlayWhenReady()) {
                        is_user_stop_video = false;
                        privious_player.setPlayWhenReady(true);
                    }


                    if (Variables.sharedPreferences.getBoolean(Variables.islogin, false)) {
                        if (!animation_running) {
                            if (handler != null && runnable != null) {
                                handler.removeCallbacks(runnable);

                            }
                            runnable = new Runnable() {
                                public void run() {
                                    Show_heart_on_DoubleTap(item, mainlayout, e);
                                    Like_Video(currentPage, item);
                                }
                            };
                            handler.postDelayed(runnable, 100);


                        }
                    } else {

                        Intent intent = new Intent(getActivity(), Login_A.class);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.in_from_bottom, R.anim.out_to_top);
                    }
                    return super.onDoubleTap(e);

                }
            });

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });


        TextView desc_txt = layout.findViewById(R.id.desc_txt);
        HashTagHelper.Creator.create(context.getResources().getColor(R.color.maincolor), new HashTagHelper.OnHashTagClickListener() {
            @Override
            public void onHashTagClicked(String hashTag) {
                onPause();
                OpenHashtag(hashTag);

            }
        }).handle(desc_txt);

        LinearLayout soundimage = (LinearLayout) layout.findViewById(R.id.sound_image_layout);
        Animation aniRotate = AnimationUtils.loadAnimation(context, R.anim.d_clockwise_rotation);
        soundimage.startAnimation(aniRotate);


        if (Variables.sharedPreferences.getBoolean(Variables.islogin, false)) {
            Functions.Call_Api_For_update_view(getActivity(), item.video_id);
        }

        swipe_count++;
        if (swipe_count > 6) {
            Show_add();
            swipe_count = 0;
        }


        if (Variables.is_demo_app && currentPage == data_list.size() - 1) {
            Functions.show_toast(context,"Only allow " + data_list.size() + " Videos in demo app");
        }


        Call_Api_For_Singlevideos(currentPage);

    }

    private void moveViewToScreenCenter(final View view) {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        int originalPos[] = new int[2];
        view.getLocationOnScreen(originalPos);

        int xDelta = (dm.widthPixels - view.getMeasuredWidth() - originalPos[0]) / 2;
        int yDelta = (dm.heightPixels - view.getMeasuredHeight() - originalPos[1]) / 2;

        AnimationSet animSet = new AnimationSet(true);
        animSet.setFillAfter(true);
        animSet.setDuration(4000);
        animSet.setRepeatCount(Animation.INFINITE);
        animSet.setInterpolator(new BounceInterpolator());
        TranslateAnimation translate = new TranslateAnimation(0, xDelta, 0, yDelta);
        animSet.addAnimation(translate);
        ScaleAnimation scale = new ScaleAnimation(1f, 2f, 1f, 2f, ScaleAnimation.RELATIVE_TO_PARENT, .5f, ScaleAnimation.RELATIVE_TO_PARENT, .5f);
        animSet.addAnimation(scale);
        view.startAnimation(animSet);
    }


    SimpleExoPlayer cache_player;

    public void Call_cache() {
        if (currentPage + 1 < data_list.size()) {


            if (cache_player != null)
                cache_player.release();

            cache_player = ExoPlayerFactory.newSimpleInstance(context);

            SimpleCache simpleCache = TicTic.simpleCache;
            CacheDataSourceFactory cacheDataSourceFactory = new CacheDataSourceFactory(simpleCache, new DefaultHttpDataSourceFactory(Util.getUserAgent(getActivity(), "BubbleTok"))
                    , CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR);

            ProgressiveMediaSource videoSource = new ProgressiveMediaSource.Factory(cacheDataSourceFactory).createMediaSource(Uri.parse((data_list.get(currentPage + 1).video_url)));

            cache_player.prepare(videoSource);
        }


    }


    public boolean Show_heart_on_DoubleTap(Home_Get_Set item, final RelativeLayout mainlayout, MotionEvent e) {

        int x = (int) e.getX() - 100;
        int y = (int) e.getY() - 100;
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        final ImageView iv = new ImageView(getApplicationContext());
        lp.setMargins(x, y, 0, 0);
        iv.setLayoutParams(lp);
        if (item.liked.equals("1")) {
            iv.setImageDrawable(getResources().getDrawable(
                    R.drawable.ic_like));
        } else {
            iv.setImageDrawable(getResources().getDrawable(
                    R.drawable.ic_like_fill));
        }
        mainlayout.addView(iv);
        Animation fadeoutani = AnimationUtils.loadAnimation(context, R.anim.fade_out);

        fadeoutani.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //on animation start
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mainlayout.removeView(iv);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // on animation repeat
            }
        });
        iv.startAnimation(fadeoutani);

        return true;
    }


    InterstitialAd mInterstitialAd;

    public void Load_add() {

        // this is test app id you will get the actual id when you add app in your
        //add mob account
        MobileAds.initialize(context, getResources().getString(R.string.ad_app_id));


        //code for intertial add
        mInterstitialAd = new InterstitialAd(context);

        //here we will get the add id keep in mind above id is app id and below Id is add Id
        mInterstitialAd.setAdUnitId(context.getResources().getString(R.string.my_Interstitial_Add));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });


    }


    public void Show_add() {
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }


    @Override
    public void onDataSent(String yourData) {
        int comment_count = Functions.ParseInterger(yourData);
        Home_Get_Set item = data_list.get(currentPage);
        item.video_comment_count = "" + comment_count;
        data_list.remove(currentPage);
        data_list.add(currentPage, item);
        adapter.notifyDataSetChanged();
    }


    // this will call when go to the home tab From other tab.
    // this is very importent when for video play and pause when the focus is changes
    boolean is_visible_to_user;
    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        is_visible_to_user = visible;

        if (privious_player != null && (visible && !is_user_stop_video)) {
            privious_player.setPlayWhenReady(true);
        } else if (privious_player != null && !visible) {
            privious_player.setPlayWhenReady(false);
        }
    }



    // when we swipe for another video this will relaese the privious player
    SimpleExoPlayer privious_player;

    public void Release_Privious_Player() {
        if (privious_player != null) {
            privious_player.removeListener(this);
            privious_player.release();
        }
    }


    // this function will call for like the video and Call an Api for like the video
    public void Like_Video(final int position, final Home_Get_Set home_get_set) {
        String action = home_get_set.liked;

        if (action.equals("1")) {
            action = "0";
            home_get_set.like_count = "" + (Functions.ParseInterger(home_get_set.like_count) - 1);
        } else {
            action = "1";
            home_get_set.like_count = "" + (Functions.ParseInterger(home_get_set.like_count) + 1);
        }


        data_list.remove(position);
        home_get_set.liked = action;
        data_list.add(position, home_get_set);
        adapter.notifyDataSetChanged();

        Functions.Call_Api_For_like_video(getActivity(), home_get_set.video_id, action, null);

    }


    // this will open the comment screen
    private void OpenComment(Home_Get_Set item) {

        int comment_counnt = Functions.ParseInterger(item.video_comment_count);

        Fragment_Data_Send fragment_data_send = this;

        Comment_F comment_f = new Comment_F(comment_counnt, fragment_data_send, "Home_F");
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.in_from_bottom, R.anim.out_to_top, R.anim.in_from_top, R.anim.out_from_bottom);
        Bundle args = new Bundle();
        args.putString("video_id", item.video_id);
        args.putString("user_id", item.user_id);
        args.putSerializable("data", item);
        comment_f.setArguments(args);
        transaction.addToBackStack(null);
        transaction.replace(R.id.MainMenuFragment, comment_f).commit();


    }


    public void Open_Login() {
        Intent intent = new Intent(getActivity(), Login_A.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.in_from_bottom, R.anim.out_to_top);
    }


    // this will open the profile of user which have uploaded the currenlty running video
    private void OpenProfile(Home_Get_Set item, boolean from_right_to_left) {
        if (Variables.sharedPreferences.getString(Variables.u_id, "0").equals(item.user_id)) {

            TabLayout.Tab profile = MainMenuFragment.tabLayout.getTabAt(4);
            profile.select();

        } else {
            Profile_F profile_f = new Profile_F(new Fragment_Callback() {
                @Override
                public void Responce(Bundle bundle) {

                    Call_Api_For_Singlevideos(currentPage);
                }
            });
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

            if (from_right_to_left) {
                transaction.setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left, R.anim.in_from_left, R.anim.out_to_right);
            } else {
                transaction.setCustomAnimations(R.anim.in_from_bottom, R.anim.out_to_top, R.anim.in_from_top, R.anim.out_from_bottom);
            }

            Bundle args = new Bundle();
            args.putString("user_id", item.user_id);
            args.putString("user_name", item.username);
            args.putString("user_pic", item.profile_pic);
            profile_f.setArguments(args);
            transaction.addToBackStack(null);
            transaction.replace(R.id.MainMenuFragment, profile_f).commit();
        }

    }


    // this will open the profile of user which have uploaded the currenlty running video
    private void OpenHashtag(String tag) {

        Taged_Videos_F taged_videos_f = new Taged_Videos_F();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.in_from_bottom, R.anim.out_to_top, R.anim.in_from_top, R.anim.out_from_bottom);
        Bundle args = new Bundle();
        args.putString("tag", tag);
        taged_videos_f.setArguments(args);
        transaction.addToBackStack(null);
        transaction.replace(R.id.MainMenuFragment, taged_videos_f).commit();


    }


    private void Show_video_option(final Home_Get_Set item) {

        Functions.Show_video_option(context, item, new Callback() {
            @Override
            public void Responce(String resp) {

                if (Variables.sharedPreferences.getBoolean(Variables.islogin, false)) {
                    if (resp.equalsIgnoreCase("favourite")) {
                        Favourite_video(item);
                    } else if (resp.equalsIgnoreCase("not_intrested")) {
                        Not_Interest_video(item);
                    } else if (resp.equalsIgnoreCase("report")) {
                        Open_video_report(item);
                    }

                } else
                    Open_Login();
            }
        });
    }

    public void Favourite_video(final Home_Get_Set item) {

        JSONObject params = new JSONObject();
        try {
            params.put("video_id", item.video_id);
            params.put("user_id", Variables.sharedPreferences.getString(Variables.u_id, ""));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Functions.Show_loader(context, false, false);
        ApiRequest.Call_Api(getActivity(), ApiLinks.addVideoFavourite, params, new Callback() {
            @Override
            public void Responce(String resp) {
                Functions.cancel_loader();

                try {
                    JSONObject jsonObject = new JSONObject(resp);

                    String code = jsonObject.optString("code");
                    if (code.equals("200")) {
                        Functions.show_toast(context,"Successfully added to your favourite list!");
                        if (item.favourite != null && item.favourite.equals("0"))
                            item.favourite = "1";
                        else
                            item.favourite = "0";

                        data_list.set(currentPage, item);
                        adapter.notifyDataSetChanged();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


    }

    public void Not_Interest_video(final Home_Get_Set item) {

        JSONObject params = new JSONObject();
        try {
            params.put("video_id", item.video_id);
            params.put("user_id", Variables.sharedPreferences.getString(Variables.u_id, ""));

        } catch (JSONException e) {
            e.printStackTrace();
        }


        Functions.Show_loader(context, false, false);
        ApiRequest.Call_Api(getActivity(), ApiLinks.NotInterestedVideo, params, new Callback() {
            @Override
            public void Responce(String resp) {
                Functions.cancel_loader();
                try {
                    JSONObject jsonObject = new JSONObject(resp);
                    String code = jsonObject.optString("code");
                    if (code.equals("200")) {
                        data_list.remove(item);
                        adapter.notifyDataSetChanged();
                        Functions.show_toast(context,"Successfully added action");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


    }

    public void Open_video_report(Home_Get_Set home_get_set) {
        Report_Type_F _report_typeF = new Report_Type_F(false, null);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.in_from_bottom, R.anim.out_to_top, R.anim.in_from_top, R.anim.out_from_bottom);

        Bundle bundle = new Bundle();
        bundle.putString("video_id", home_get_set.video_id);
        _report_typeF.setArguments(bundle);

        transaction.addToBackStack(null);
        transaction.replace(R.id.MainMenuFragment, _report_typeF).commit();
        onPause();
    }


    public void Save_Video(final Home_Get_Set item) {

        JSONObject params = new JSONObject();
        try {
            params.put("video_id", item.video_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Functions.Show_loader(context, false, false);
        ApiRequest.Call_Api(getActivity(), ApiLinks.downloadVideo, params, new Callback() {
            @Override
            public void Responce(String resp) {
                Functions.cancel_loader();
                try {
                    JSONObject responce = new JSONObject(resp);
                    String code = responce.optString("code");
                    if (code.equals("200")) {
                        final String download_url = responce.optString("msg");
                        if (download_url != null) {

                            Functions.Show_determinent_loader(context, false, false);
                            PRDownloader.initialize(getActivity().getApplicationContext());
                            DownloadRequest prDownloader = PRDownloader.download(ApiLinks.base_url + download_url, Variables.app_showing_folder, item.video_id + ".mp4")
                                    .build()

                                    .setOnProgressListener(new OnProgressListener() {
                                        @Override
                                        public void onProgress(Progress progress) {

                                            int prog = (int) ((progress.currentBytes * 100) / progress.totalBytes);
                                            Functions.Show_loading_progress(prog);

                                        }
                                    });


                            prDownloader.start(new OnDownloadListener() {
                                @Override
                                public void onDownloadComplete() {
                                    Functions.cancel_determinent_loader();

                                    Delete_water_marke_video(download_url);
                                    Scan_file(item);
                                }

                                @Override
                                public void onError(Error error) {

                                    Functions.show_toast(context,"Error");
                                    Functions.cancel_determinent_loader();
                                }


                            });

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }


    public void Delete_water_marke_video(String video_url) {

        JSONObject params = new JSONObject();
        try {
            params.put("video_url", video_url);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiRequest.Call_Api(getActivity(), ApiLinks.deleteWaterMarkVideo, params, null);


    }


    public void Scan_file(Home_Get_Set item) {
        MediaScannerConnection.scanFile(getActivity(),
                new String[]{Variables.app_showing_folder + item.video_id + ".mp4"},
                null,
                new MediaScannerConnection.OnScanCompletedListener() {

                    public void onScanCompleted(String path, Uri uri) {

                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });
    }


    public void Duet_video(final Home_Get_Set item) {

        Log.d(Variables.tag, item.video_url);
        if (item.video_url != null) {

            Functions.Show_determinent_loader(context, false, false);
            PRDownloader.initialize(getActivity().getApplicationContext());
            DownloadRequest prDownloader = PRDownloader.download(item.video_url, Variables.app_showing_folder, item.video_id + ".mp4")
                    .build()

                    .setOnProgressListener(new OnProgressListener() {
                        @Override
                        public void onProgress(Progress progress) {
                            int prog = (int) ((progress.currentBytes * 100) / progress.totalBytes);
                            Functions.Show_loading_progress(prog);

                        }
                    });


            prDownloader.start(new OnDownloadListener() {
                @Override
                public void onDownloadComplete() {
                    Functions.cancel_determinent_loader();

                    Open_duet_Recording(item);

                }

                @Override
                public void onError(Error error) {

                    Functions.show_toast(context,"Error");
                    Functions.cancel_determinent_loader();
                }


            });

        }

    }


    public void Open_duet_Recording(Home_Get_Set item) {

        Intent intent = new Intent(getActivity(), Video_Recoder_Duet_A.class);
        intent.putExtra("data", item);
        startActivity(intent);

    }


    public boolean is_fragment_exits() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        if (fm.getBackStackEntryCount() == 0) {
            return false;
        } else {
            return true;
        }

    }


    // this is lifecyle of the Activity which is importent for play,pause video or relaese the player
    @Override
    public void onResume() {
        super.onResume();
        if ((privious_player != null && (is_visible_to_user && !is_user_stop_video)) && !is_fragment_exits()) {
            privious_player.setPlayWhenReady(true);
        }
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (privious_player != null) {
            privious_player.setPlayWhenReady(false);
        }
    }


    private void runTimer(Ads_Get_Model parsedResponse, JSONObject response){

        long leftLimit = 10000;
        long rightLimit = 120000;
        long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));

        new CountDownTimer( generatedLong, 1000){
            public void onTick(long millisUntilFinished){
//                textView.setText(String.valueOf(counter));
//                counter--;

                Log.d("Countdown Timer : ","Ticking...");
            }
            public  void onFinish(){
                //textView.setText("FINISH!!");
                onPause();
///////
//                getAdd();
                try {

                    List<Fragment> fragments = getFragmentManager().getFragments();

                    boolean homeVisible = fragments.get(0).getUserVisibleHint();
                    if (homeVisible)
                        showAds(parsedResponse, response);
                    else
                        runTimer(parsedResponse, response);

                }
                catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }.start();

    }


    public int randomurl(int limit) {
        return (int) (1 + (Math.random() * (limit - 1)));
    }

    private void getAdd(){

        RequestQueue queue = Volley.newRequestQueue(getContext());

        String url = "https://www.selfyclub.com/mobileapp_api/api/showAds";
        JSONObject jsonBody = new JSONObject();
        Gson gson = new Gson();
        try {

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

//                    Toast.makeText(context, latitude, Toast.LENGTH_SHORT).show();

                    msg = parsedResponse.getMsg();
//                showAds(response.toString());

                    ArrayList<Msg> msgArray = new ArrayList<Msg>(Collections.singleton(msg));
                    if(msgArray.size() > 0)
                        runTimer(parsedResponse, jsonResponse);
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

    private void showAds(Ads_Get_Model resp, JSONObject obj){

        Rect displayRectangle = new Rect();
        Window window = getActivity().getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        //final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.com_facebook_auth_dialog);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        ViewGroup viewGroup = getActivity().findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.customview, viewGroup, false);
       /* dialogView.setMinimumWidth((int)(displayRectangle.width() * 1f));
        dialogView.setMinimumHeight((int)(displayRectangle.height() * 1f));*/


        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        dialogView.setMinimumWidth(metrics.widthPixels);
        dialogView.setMinimumHeight(metrics.heightPixels);

        builder.setView(dialogView);
        TextView buttonOk = dialogView.findViewById(R.id.tv_skipAd);
        VideoView videoAd = dialogView.findViewById(R.id.video_view);
        GifImageView gifAd = dialogView.findViewById(R.id.gif);
        ImageView imageAd = dialogView.findViewById(R.id.iv_ads);
//        ImageView ivClose = dialogView.findViewById(R.id.iv_close);
        TextView tvCountdown = dialogView.findViewById(R.id.tv_countdown);

//        Glide.with(this).load(R.drawable.timergif).into(imageView);

        buttonOk.setVisibility(View.GONE);
        buttonOk.setEnabled(false);

        msg = resp.getMsg();
        List<VideoAd> videos = msg.getVideos();
        List<Gif> gifs = msg.getGifs();
        List<ImageModel> images = msg.getImages();

        ArrayList<String> newList = new ArrayList<String>();
        newList.add("video");
        newList.add("gif");
        newList.add("image");

        Random rand = new Random();
        int newRand = rand.nextInt(3);
        String selected = newList.get(newRand);

        try{

            JSONObject jsonObject = obj;
//            if(jsonObject.get("msg") != null){

                JSONObject itemsObject = (JSONObject)jsonObject.get("msg");
                if(itemsObject.length() > 0) {

                    Uri uri;
//                    ivClose.setVisibility(View.GONE);
                    tvCountdown.setVisibility(View.VISIBLE);

                    switch (selected){
                        case "video":
                            videoAd.setVisibility(View.VISIBLE);
                            gifAd.setVisibility(View.GONE);
                            imageAd.setVisibility(View.GONE);
                            Random vRand = new Random();
                            int newVRand = vRand.nextInt(videos.size());
                            uri = Uri.parse(videos.get(newVRand).getAdfile());
                            videoAd.setMediaController(new MediaController(getActivity()));
                            videoAd.setVideoURI(uri);
                            videoAd.requestFocus();
                            videoAd.start();

                            android.widget.RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) videoAd.getLayoutParams();
                            params.width = metrics.widthPixels;
                            params.height = metrics.heightPixels;
                            params.leftMargin = 0;
                            videoAd.setLayoutParams(params);

                            break;
                        case "gif":
                            videoAd.setVisibility(View.GONE);
                            gifAd.setVisibility(View.VISIBLE);
                            imageAd.setVisibility(View.GONE);

                            Random gRand = new Random();
                            int newGRand = gRand.nextInt(gifs.size());
                            uri = Uri.parse(gifs.get(newGRand).getAdfile());

                            Glide
                                    .with(context)
                                    .load(uri)
                                    .into(gifAd);


                            /*Glide
                                    .with(context) // replace with 'this' if it's in activity
                                    .load(uri)
                                    .into(R.id.gif);*/

                            break;
                        case "image":

                            videoAd.setVisibility(View.GONE);
                            gifAd.setVisibility(View.GONE);
                            imageAd.setVisibility(View.VISIBLE);

                            Random iRand = new Random();
                            int newIRand = iRand.nextInt(images.size());
                            uri = Uri.parse(images.get(newIRand).getAdfile());
                            Glide
                                    .with(context)
                                    .load(uri)
                                    .into(imageAd);
                            break;
                        default: uri = null;

                    }

                }
        }catch (Exception e){

            e.printStackTrace();
        }

        new CountDownTimer( 8000, 1000){
            public void onTick(long millisUntilFinished){

                tvCountdown.setText(String.valueOf(counter));
                counter--;

            }
            public  void onFinish(){
                //textView.setText("FINISH!!");
                onPause();

                counter = 5;
//                getAdd();

                tvCountdown.setVisibility(View.GONE);
//                ivClose.setVisibility(View.VISIBLE);

                buttonOk.setVisibility(View.VISIBLE);
                buttonOk.setEnabled(true);

            }
        }.start();


        final AlertDialog alertDialog = builder.create();
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                onResume();
                runTimer(parsedResponse, jsonResponse);
        }
        });

        alertDialog.setCancelable(false);
        alertDialog.show();
        dimBg(alertDialog);

    }



    @Override
    public void onStop() {
        super.onStop();
        if (privious_player != null) {
            privious_player.setPlayWhenReady(false);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (privious_player != null) {
            privious_player.release();
        }

        if (mReceiver != null) {
            getActivity().unregisterReceiver(mReceiver);
            mReceiver = null;
        }

    }


    public boolean check_permissions() {

        String[] PERMISSIONS = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA
        };

        if (!hasPermissions(context, PERMISSIONS)) {
            requestPermissions(PERMISSIONS, 2);
        } else {

            return true;
        }

        return false;
    }


    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

        if (playbackState == Player.STATE_BUFFERING) {
            p_bar.setVisibility(View.VISIBLE);
        } else if (playbackState == Player.STATE_READY) {
            p_bar.setVisibility(View.GONE);
        }

    }




    private void dimBg(Dialog dialog){
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount=1.0f;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
    }

    public void checkVersion()
    {
        String  versionName = BuildConfig.VERSION_NAME;

        JSONObject parameters = new JSONObject();
        try {
            parameters.put("version",versionName);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiRequest.Call_Api(getActivity(), ApiLinks.showUpdatePopup, parameters, new Callback() {
            @Override
            public void Responce(String resp) {

                Log.e("JSONresponse", "Got Response");

                Gson gson = new Gson();

                try {

                    UpdatePopupModel parsedResponse = gson.fromJson(resp.toString(), UpdatePopupModel.class);
                    if (parsedResponse.getData().equalsIgnoreCase("show")) {
                        showUpdatePopup();
                    }

                } catch (Exception e) {

                    e.printStackTrace();
                    Log.e("JSONSyntaxException", "Parse Error");

                }

                Log.e("", "");


            }
        });

    }

    public void showUpdatePopup()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(getString(R.string.update_message));
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent implicit = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.codeleven.selfyclub"));
                startActivity(implicit);
//                finish();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                finish();
            }
        });
        builder.setCancelable(false);

        builder.show();


    }


}
