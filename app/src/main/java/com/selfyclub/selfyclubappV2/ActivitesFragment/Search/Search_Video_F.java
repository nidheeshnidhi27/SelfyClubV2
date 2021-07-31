package com.selfyclub.selfyclubappV2.ActivitesFragment.Search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.selfyclub.selfyclubappV2.Adapters.VideosList_Adapter;
import com.selfyclub.selfyclubappV2.Models.Home_Get_Set;
import com.selfyclub.selfyclubappV2.Main_Menu.RelateToFragment_OnBack.RootFragment;
import com.selfyclub.selfyclubappV2.R;
import com.selfyclub.selfyclubappV2.Interfaces.Adapter_Click_Listener;
import com.selfyclub.selfyclubappV2.ApiClasses.ApiLinks;
import com.selfyclub.selfyclubappV2.ApiClasses.ApiRequest;
import com.selfyclub.selfyclubappV2.Interfaces.Callback;
import com.selfyclub.selfyclubappV2.SimpleClasses.Functions;
import com.selfyclub.selfyclubappV2.ActivitesFragment.WatchVideos_F;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.selfyclub.selfyclubappV2.ActivitesFragment.Search.Search_Main_F.search_edit;

/**
 * A simple {@link Fragment} subclass.
 */
public class Search_Video_F extends RootFragment {

    View view;
    Context context;
    String type;
    ShimmerFrameLayout shimmerFrameLayout;
    RecyclerView recyclerView;
    GridLayoutManager linearLayoutManager;
    RelativeLayout no_data_layout;
    ProgressBar load_more_progress;

    int page_count = 0;
    boolean ispost_finsh;

    ArrayList<Home_Get_Set> data_list;
    VideosList_Adapter adapter;

    public Search_Video_F(String type) {
        this.type = type;
    }

    public Search_Video_F() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search, container, false);
        context = getContext();

        shimmerFrameLayout = view.findViewById(R.id.shimmer_view_container);
        shimmerFrameLayout.startShimmer();

        recyclerView = view.findViewById(R.id.recylerview);
        no_data_layout = view.findViewById(R.id.no_data_layout);
        linearLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(linearLayoutManager);

        data_list = new ArrayList<>();
        adapter = new VideosList_Adapter(context, data_list, new Adapter_Click_Listener() {
            @Override
            public void onItemClick(View view, int pos, Object object) {

                Home_Get_Set item = (Home_Get_Set) object;
                OpenWatchVideo(item.video_id);


            }
        });
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean userScrolled;
            int scrollOutitems;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    userScrolled = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                scrollOutitems = linearLayoutManager.findLastVisibleItemPosition();

                Log.d("resp", "" + scrollOutitems);
                if (userScrolled && (scrollOutitems == data_list.size() - 1)) {
                    userScrolled = false;

                    if (load_more_progress.getVisibility() != View.VISIBLE && !ispost_finsh) {
                        load_more_progress.setVisibility(View.VISIBLE);
                        page_count = page_count + 1;
                        Call_Api();
                    }
                }


            }
        });


        load_more_progress = view.findViewById(R.id.load_more_progress);

        page_count = 0;
        Call_Api();

        return view;
    }


    public void Call_Api() {

        JSONObject params = new JSONObject();
        try {

            params.put("type", type);
            params.put("keyword", search_edit.getText().toString());
            params.put("starting_point", "" + page_count);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiRequest.Call_Api(getActivity(), ApiLinks.search, params, new Callback() {
            @Override
            public void Responce(String resp) {
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);

                if (type.equals("video"))
                    Parse_video(resp);


            }
        });

    }


    public void Parse_video(String responce) {

        try {
            JSONObject jsonObject = new JSONObject(responce);
            String code = jsonObject.optString("code");
            if (code.equals("200")) {
                JSONArray msgArray = jsonObject.getJSONArray("msg");

                ArrayList<Home_Get_Set> temp_list = new ArrayList<>();
                for (int i = 0; i < msgArray.length(); i++) {
                    JSONObject itemdata = msgArray.optJSONObject(i);

                    JSONObject Video = itemdata.optJSONObject("Video");
                    JSONObject User = itemdata.optJSONObject("User");
                    JSONObject Sound = itemdata.optJSONObject("Sound");
                    JSONObject UserPrivacy = User.optJSONObject("PrivacySetting");
                    JSONObject UserPushNotification = User.optJSONObject("PushNotification");

                    Home_Get_Set item = Functions.Parse_video_data(User, Sound, Video, UserPrivacy, UserPushNotification);


                    temp_list.add(item);
                }

                if (page_count == 0) {

                    data_list.addAll(temp_list);

                    if (data_list.isEmpty()) {
                        no_data_layout.setVisibility(View.VISIBLE);
                    } else {
                        no_data_layout.setVisibility(View.GONE);

                        recyclerView.setAdapter(adapter);
                    }
                } else {

                    if (temp_list.isEmpty())
                        ispost_finsh = true;
                    else {
                        data_list.addAll(temp_list);
                        adapter.notifyDataSetChanged();
                    }

                }


            } else {
                if (data_list.isEmpty())
                    no_data_layout.setVisibility(View.VISIBLE);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            load_more_progress.setVisibility(View.GONE);
        }

    }


    private void OpenWatchVideo(String video_id) {
        Intent intent = new Intent(getActivity(), WatchVideos_F.class);
        intent.putExtra("video_id", video_id);
        startActivity(intent);
    }


}
