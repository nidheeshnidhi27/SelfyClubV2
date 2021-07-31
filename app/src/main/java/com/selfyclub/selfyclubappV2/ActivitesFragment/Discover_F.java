package com.selfyclub.selfyclubappV2.ActivitesFragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.selfyclub.selfyclubappV2.Adapters.Discover_Adapter;
import com.selfyclub.selfyclubappV2.Adapters.Sliding_Adapter;
import com.selfyclub.selfyclubappV2.Contest.Contest_A;
import com.selfyclub.selfyclubappV2.Models.CMsg;
import com.selfyclub.selfyclubappV2.Models.Contest_Get_Model;
import com.selfyclub.selfyclubappV2.Models.Discover_Get_Set;
import com.selfyclub.selfyclubappV2.Models.Home_Get_Set;
import com.selfyclub.selfyclubappV2.Main_Menu.RelateToFragment_OnBack.RootFragment;
import com.selfyclub.selfyclubappV2.Models.Language_Based_Video_Model;
import com.selfyclub.selfyclubappV2.Models.MsgLang;
import com.selfyclub.selfyclubappV2.Models.PublicModel;
import com.selfyclub.selfyclubappV2.Models.SliderModel;
import com.selfyclub.selfyclubappV2.Models.Video_Discover_Model;
import com.selfyclub.selfyclubappV2.R;
import com.selfyclub.selfyclubappV2.ActivitesFragment.Search.Search_Main_F;
import com.selfyclub.selfyclubappV2.Interfaces.Adapter_Click_Listener;
import com.selfyclub.selfyclubappV2.ApiClasses.ApiLinks;
import com.selfyclub.selfyclubappV2.ApiClasses.ApiRequest;
import com.selfyclub.selfyclubappV2.Interfaces.Callback;
import com.selfyclub.selfyclubappV2.SimpleClasses.Functions;
import com.selfyclub.selfyclubappV2.SimpleClasses.TouchSafeSwipeRefreshLayout;
import com.google.gson.Gson;
import com.rd.PageIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Discover_F extends RootFragment implements View.OnClickListener {

    View view;
    Context context;

    HorizontalScrollView hsvLang;
    ImageView ivChooseMalayalam, ivChooseEnglish, ivChooseTamil, ivChooseHindi, ivContest;

    RecyclerView recyclerView, rvDemoShow;
    EditText search_edit;

    String jsonResponse;
    Contest_Get_Model parsedResponse;
    Gson gson = new Gson();
    CMsg msg;

    TouchSafeSwipeRefreshLayout swiperefresh;

    public Discover_F() {
        // Required empty public constructor
    }

    ArrayList<Discover_Get_Set> datalist;
    Discover_Adapter adapter;


    PageIndicatorView pageIndicatorView;
    ViewPager viewPager;
    String lang = null;

    LinearLayout ll_DemoShow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_discover, container, false);
        context = getContext();

        // Functions.white_status_bar(getActivity());

        datalist = new ArrayList<>();

        ivChooseEnglish = view.findViewById(R.id.iv_choose_lang_eng);
        ivChooseMalayalam = view.findViewById(R.id.iv_choose_lang_mal);
        ivChooseTamil = view.findViewById(R.id.iv_choose_lang_tamil);
        ivChooseHindi = view.findViewById(R.id.iv_choose_lang_hindi);
        hsvLang = view.findViewById(R.id.hsv_disc_lang);
        rvDemoShow = view.findViewById(R.id.rv_demoShow);
        ll_DemoShow = view.findViewById(R.id.ll_demoShowList);

        ivContest = view.findViewById(R.id.iv_contest);

        callApiForContest();

        recyclerView = (RecyclerView) view.findViewById(R.id.recylerview);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setVisibility(View.VISIBLE);
        ll_DemoShow.setVisibility(View.GONE);

        ivContest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,Contest_A.class);
                startActivity(i);

                /*Intent browserintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.josalukkasonline.com/"));
                startActivity(browserintent);*/

            }
        });

        ivChooseEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* lang = "english";
                Call_api_for_language(lang);*/

                recyclerView.setVisibility(View.GONE);
                ll_DemoShow.setVisibility(View.VISIBLE);

            }
        });

        ivChooseMalayalam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recyclerView.setVisibility(View.GONE);
                ll_DemoShow.setVisibility(View.VISIBLE);

                /*Intent i = new Intent(getActivity(), DemoTvShow.class);
                startActivity(i);*/

                /*lang = "malayalam";
                Call_api_for_language(lang);*/
            }
        });

        ivChooseTamil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*lang = "tamil";
                Call_api_for_language(lang);*/

                recyclerView.setVisibility(View.GONE);
                ll_DemoShow.setVisibility(View.VISIBLE);

            }
        });

        ivChooseHindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*lang = "hindi";
                Call_api_for_language(lang);*/

                recyclerView.setVisibility(View.GONE);
                ll_DemoShow.setVisibility(View.VISIBLE);

            }
        });

        recyclerView.setVisibility(View.VISIBLE);

        adapter = new Discover_Adapter(context, datalist, new Discover_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(ArrayList<Home_Get_Set> datalist, int postion) {

                OpenWatchVideo(postion, datalist);

            }
        });
        recyclerView.setAdapter(adapter);


        search_edit = view.findViewById(R.id.search_edit);
        search_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // this method call before text change

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String query = search_edit.getText().toString();
                if (adapter != null)
                    adapter.getFilter().filter(query);

            }

            @Override
            public void afterTextChanged(Editable s) {
                // this method call after text change
            }
        });


        viewPager = view.findViewById(R.id.viewPager);
        pageIndicatorView = view.findViewById(R.id.pageIndicatorView);
        swiperefresh = view.findViewById(R.id.swiperefresh);
        swiperefresh.setColorSchemeResources(R.color.black);
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Call_api_for_get_slider();
                Call_Api_For_get_Allvideos();
            }
        });

        view.findViewById(R.id.search_layout).setOnClickListener(this);
        view.findViewById(R.id.search_edit).setOnClickListener(this);

        Call_Api_For_get_Allvideos();
        Call_api_for_get_slider();
        return view;
    }

    private void callApiForContest() {

        JSONObject parameters = new JSONObject();
        try {
            parameters.put("starting_point",0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiRequest.Call_Api(getActivity(), ApiLinks.showAllContests, parameters, new Callback() {
            @Override
            public void Responce(String resp) {

                Log.e("","");
                jsonResponse = resp;

                try {

                    parsedResponse = gson.fromJson(resp.toString(), Contest_Get_Model.class);

                    msg = parsedResponse.getMsg();
//                showAds(response.toString());

                    ArrayList<CMsg> msgArray = new ArrayList<CMsg>(Collections.singleton(msg));
//                    if(msgArray.size() > 0)
//                        showContest(parsedResponse, jsonResponse);


                } catch (IllegalStateException e) {

                    e.printStackTrace();
                    Log.e("IllegalStateException",e.getMessage());
                }catch(Exception e1){
                    e1.printStackTrace();
                    Log.e("Exception",e1.getMessage());
                }


            }
        });


    }


    private void Call_api_for_language(String lang) {

        JSONObject parameters = new JSONObject();

        try {
            parameters.put("language", lang);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiRequest.Call_Api(getActivity(), ApiLinks.showDiscoverySections, parameters, new Callback() {
            @Override
            public void Responce(String resp) {
                Parse_data(resp);
                swiperefresh.setRefreshing(false);
            }
        });

    }


    private void Call_api_for_get_slider() {

        ApiRequest.Call_Api(getActivity(), ApiLinks.showAppSlider, new JSONObject(), new Callback() {
            @Override
            public void Responce(String resp) {
                Parse_Slider_data(resp);
            }
        });

    }


    private void enableDisableSwipeRefresh(boolean enable) {
        if (swiperefresh != null) {
            swiperefresh.setEnabled(enable);
        }
    }

    ArrayList<SliderModel> slider_list = new ArrayList<>();

    public void Parse_Slider_data(String resp) {
        try {
            JSONObject jsonObject = new JSONObject(resp);

            String code = jsonObject.optString("code");
            if (code.equals("200")) {

                slider_list.clear();

                JSONArray msg = jsonObject.optJSONArray("msg");
                for (int i = 0; i < msg.length(); i++) {
                    JSONObject object = msg.optJSONObject(i);
                    JSONObject AppSlider = object.optJSONObject("AppSlider");

                    SliderModel sliderModel = new SliderModel();
                    sliderModel.id = AppSlider.optString("id");
                    sliderModel.image = AppSlider.optString("image");
                    sliderModel.url = AppSlider.optString("url");

                    slider_list.add(sliderModel);
                }

                Set_slider_adapter();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void Set_slider_adapter() {

        pageIndicatorView.setCount(slider_list.size());
        pageIndicatorView.setSelection(0);

        viewPager.setAdapter(new Sliding_Adapter(getActivity(), slider_list, new Adapter_Click_Listener() {
            @Override
            public void onItemClick(View view, int pos, Object object) {
                String slider_url = slider_list.get(pos).url;
                if (slider_url != null && !slider_url.equals("")) {
                    Webview_F webview_f = new Webview_F();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left, R.anim.in_from_left, R.anim.out_to_right);
                    Bundle bundle = new Bundle();
                    bundle.putString("url", slider_url);
                    bundle.putString("title", "Link");
                    webview_f.setArguments(bundle);
                    transaction.addToBackStack(null);
                    transaction.replace(R.id.discovery_container, webview_f).commit();
                }
            }
        }));


        pageIndicatorView.setViewPager(viewPager);


    }

    private void Call_Api_For_get_Allvideos() {

        JSONObject parameters = new JSONObject();

        ApiRequest.Call_Api(getActivity(), ApiLinks.showDiscoverySections, parameters, new Callback() {
            @Override
            public void Responce(String resp) {
                Parse_data(resp);
                swiperefresh.setRefreshing(false);
            }
        });

    }


    public void Parse_language_data(String responce) {

        datalist.clear();

        try {

            Gson gson = new Gson();
            Language_Based_Video_Model parsedResponse = gson.fromJson(responce, Language_Based_Video_Model.class);
            MsgLang msg = parsedResponse.getMsg();
            List<PublicModel> publicModel = msg.getPublic();

            for(int i=0; i < publicModel.size(); i++) {
                Video_Discover_Model video = publicModel.get(i).getVideo();
            }

            JSONObject jsonObject = new JSONObject(responce);
            String code = jsonObject.optString("code");
            if (code.equals("200")) {
                JSONObject msgObject = jsonObject.getJSONObject("msg");
                JSONArray publicArray = msgObject.getJSONArray("public");

                for (int d = 0; d < publicArray.length(); d++) {

                    JSONObject discover_object = publicArray.optJSONObject(d);
                    JSONObject Hashtag = discover_object.optJSONObject("Hashtag");

                    Discover_Get_Set discover_get_set = new Discover_Get_Set();
                    discover_get_set.title = Hashtag.optString("name");
                    discover_get_set.views = Hashtag.optString("views");
                    discover_get_set.videos_count=Hashtag.optString("videos_count");

                    JSONArray video_array = Hashtag.optJSONArray("Videos");

                    ArrayList<Home_Get_Set> video_list = new ArrayList<>();
                    for (int i = 0; i < video_array.length(); i++) {
                        JSONObject itemdata = video_array.optJSONObject(i);

                        JSONObject Video = itemdata.optJSONObject("Video");
                        JSONObject User = Video.optJSONObject("User");
                        JSONObject Sound = Video.optJSONObject("Sound");
                        JSONObject UserPrivacy = User.optJSONObject("PrivacySetting");
                        JSONObject UserPushNotification = User.optJSONObject("PushNotification");

                        Home_Get_Set item = Functions.Parse_video_data(User, Sound, Video, UserPrivacy, UserPushNotification);


                        video_list.add(item);
                    }

                    discover_get_set.arrayList = video_list;

                    datalist.add(discover_get_set);

                }

                adapter.notifyDataSetChanged();

            } else {
                Functions.show_toast(getActivity(), jsonObject.optString("msg"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public void Parse_data(String responce) {

        datalist.clear();

        try {
            JSONObject jsonObject = new JSONObject(responce);
            String code = jsonObject.optString("code");
            if (code.equals("200")) {
                JSONArray msgArray = jsonObject.getJSONArray("msg");
                for (int d = 0; d < msgArray.length(); d++) {

                    JSONObject discover_object = msgArray.optJSONObject(d);
                    JSONObject Hashtag = discover_object.optJSONObject("Hashtag");

                    Discover_Get_Set discover_get_set = new Discover_Get_Set();
                    discover_get_set.title = Hashtag.optString("name");
                    discover_get_set.views = Hashtag.optString("views");
                    discover_get_set.videos_count=Hashtag.optString("videos_count");

                    JSONArray video_array = Hashtag.optJSONArray("Videos");

                    ArrayList<Home_Get_Set> video_list = new ArrayList<>();
                    for (int i = 0; i < video_array.length(); i++) {
                        JSONObject itemdata = video_array.optJSONObject(i);

                        JSONObject Video = itemdata.optJSONObject("Video");
                        JSONObject User = Video.optJSONObject("User");
                        JSONObject Sound = Video.optJSONObject("Sound");
                        JSONObject UserPrivacy = User.optJSONObject("PrivacySetting");
                        JSONObject UserPushNotification = User.optJSONObject("PushNotification");

                        Home_Get_Set item = Functions.Parse_video_data(User, Sound, Video, UserPrivacy, UserPushNotification);


                        video_list.add(item);
                    }

                    discover_get_set.arrayList = video_list;

                    datalist.add(discover_get_set);

                }

                adapter.notifyDataSetChanged();

            } else {
                Functions.show_toast(getActivity(), jsonObject.optString("msg"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    // When you click on any Video a new activity is open which will play the Clicked video
    private void OpenWatchVideo(int postion, ArrayList<Home_Get_Set> data_list) {

        Intent intent = new Intent(getActivity(), WatchVideos_F.class);
        intent.putExtra("arraylist", data_list);
        intent.putExtra("position", postion);
        startActivity(intent);

    }


    public void Open_search() {
        Search_Main_F search_main_f = new Search_Main_F();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.MainMenuFragment, search_main_f).commit();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_layout:
                Open_search();
                break;
            case R.id.search_edit:
                Open_search();
                break;
            default:
                return;

        }
    }

}
