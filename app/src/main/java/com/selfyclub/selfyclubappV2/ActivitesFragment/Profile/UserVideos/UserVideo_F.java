package com.selfyclub.selfyclubappV2.ActivitesFragment.Profile.UserVideos;


import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.selfyclub.selfyclubappV2.Models.Home_Get_Set;
import com.selfyclub.selfyclubappV2.Adapters.MyVideos_Adapter;
import com.selfyclub.selfyclubappV2.R;
import com.selfyclub.selfyclubappV2.ApiClasses.ApiLinks;
import com.selfyclub.selfyclubappV2.ApiClasses.ApiRequest;
import com.selfyclub.selfyclubappV2.Interfaces.Callback;
import com.selfyclub.selfyclubappV2.SimpleClasses.Functions;
import com.selfyclub.selfyclubappV2.SimpleClasses.Variables;
import com.selfyclub.selfyclubappV2.ActivitesFragment.WatchVideos_F;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserVideo_F extends Fragment {

    public RecyclerView recyclerView;
    ArrayList<Home_Get_Set> data_list;
    MyVideos_Adapter adapter;
    View view;
    Context context;
    String user_id;

    RelativeLayout no_data_layout;

    NewVideoBroadCast mReceiver;

    public UserVideo_F() {

    }

    boolean is_my_profile = true;

    @SuppressLint("ValidFragment")
    public UserVideo_F(boolean is_my_profile, String user_id) {
        this.is_my_profile = is_my_profile;
        this.user_id = user_id;
    }


    private class NewVideoBroadCast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            Variables.Reload_my_videos_inner = false;
            Call_Api_For_get_Allvideos();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user_video, container, false);

        context = getContext();


        recyclerView = view.findViewById(R.id.recylerview);
        final GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        data_list = new ArrayList<>();
        adapter = new MyVideos_Adapter(context, data_list, (view, pos, object) -> {
            Home_Get_Set item = (Home_Get_Set) object;
            OpenWatchVideo(pos);

        });

        recyclerView.setAdapter(adapter);

        no_data_layout = view.findViewById(R.id.no_data_layout);


        mReceiver = new NewVideoBroadCast();
        getActivity().registerReceiver(mReceiver, new IntentFilter("newVideo"));

        return view;

    }



    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        new Handler().postDelayed(() -> {

            if (view != null && visible) {
                Call_Api_For_get_Allvideos();
            }

        }, 200);
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mReceiver != null) {
            getActivity().unregisterReceiver(mReceiver);
            mReceiver = null;
        }
    }


    Boolean is_api_run = false;

    //this will get the all videos data of user and then parse the data
    private void Call_Api_For_get_Allvideos() {
        is_api_run = true;
        JSONObject parameters = new JSONObject();

////
        /*JSONObject parameters = new JSONObject();

        try {
            parameters.put("language", "english");
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
///
        try {
            parameters.put("user_id", Functions.getSharedPreference(context).getString(Variables.u_id, ""));

            if (!is_my_profile) {
                parameters.put("other_user_id", user_id);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiRequest.Call_Api(getActivity(), ApiLinks.showVideosAgainstUserID, parameters, new Callback() {
            @Override
            public void Responce(String resp) {
                is_api_run = false;
                Parse_data(resp);
            }
        });

        /*ApiRequest.Call_Api(getActivity(), ApiLinks.showVideosAgainstLanguage, parameters, new Callback() {
            @Override
            public void Responce(String resp) {
                is_api_run = false;
                Parse_data(resp);
            }
        });
*/

    }


    public void Parse_data(String responce) {

        data_list.clear();
        try {
            JSONObject jsonObject = new JSONObject(responce);
            String code = jsonObject.optString("code");
            if (code.equals("200")) {
                JSONObject msg = jsonObject.optJSONObject("msg");
                JSONArray public_array = msg.optJSONArray("public");


                for (int i = 0; i < public_array.length(); i++) {
                    JSONObject itemdata = public_array.optJSONObject(i);

                    JSONObject Video = itemdata.optJSONObject("Video");
                    JSONObject User = itemdata.optJSONObject("User");
                    JSONObject Sound = itemdata.optJSONObject("Sound");
                    JSONObject UserPrivacy = User.optJSONObject("PrivacySetting");
                    JSONObject UserPushNotification = User.optJSONObject("PushNotification");

                    Home_Get_Set item = Functions.Parse_video_data(User, Sound, Video, UserPrivacy, UserPushNotification);


                    data_list.add(item);
                }

                if (data_list.isEmpty()) {
                    no_data_layout.setVisibility(View.VISIBLE);
                } else {
                    no_data_layout.setVisibility(View.GONE);
                }

                adapter.notifyDataSetChanged();


            } else {
                no_data_layout.setVisibility(View.VISIBLE);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void OpenWatchVideo(int postion) {

        Intent intent = new Intent(getActivity(), WatchVideos_F.class);
        intent.putExtra("arraylist", data_list);
        intent.putExtra("position", postion);
        startActivity(intent);

    }


    @Override
    public void onResume() {
        super.onResume();
        Call_Api_For_get_Allvideos();
    }
}
