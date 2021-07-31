package com.selfyclub.selfyclubappV2.ActivitesFragment;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.selfyclub.selfyclubappV2.ApiClasses.ApiLinks;
import com.selfyclub.selfyclubappV2.ApiClasses.ApiRequest;
import com.selfyclub.selfyclubappV2.Interfaces.Callback;
import com.selfyclub.selfyclubappV2.Main_Menu.RelateToFragment_OnBack.RootFragment;
import com.selfyclub.selfyclubappV2.R;
import com.selfyclub.selfyclubappV2.SimpleClasses.Functions;
import com.selfyclub.selfyclubappV2.SimpleClasses.Variables;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Change_Password_F extends RootFragment implements View.OnClickListener {
    View view;
    TextView old_password_et,new_password_et,re_password_et;
    Button change_pass;
    public Change_Password_F() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_change_password, container, false);
        view.findViewById(R.id.Goback).setOnClickListener(this);
        view.findViewById(R.id.change_password_btn).setOnClickListener(this);

        old_password_et=view.findViewById(R.id.old_password_et);
        new_password_et=view.findViewById(R.id.new_password_et);
        re_password_et=view.findViewById(R.id.re_password_et);
        change_pass=view.findViewById(R.id.change_password_btn);

        re_password_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int count) {
                String txtName = re_password_et.getText().toString();
                if (txtName.length() > 0) {
                    change_pass.setEnabled(true);
                    change_pass.setClickable(true);
                } else {
                    change_pass.setEnabled(false);
                    change_pass.setClickable(false);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return  view;
    }

    // this will check the validations like none of the field can be the empty
    public boolean Check_Validation(){

        String o_password=old_password_et.getText().toString();
        String n_password=new_password_et.getText().toString();
        String v_password=re_password_et.getText().toString();

        if (o_password.isEmpty()) {
            Functions.show_toast(getActivity(), "Please enter valid old password");
            return false;
        }

        if (o_password.length() <= 5 ||  o_password.length() >=12) {
            Functions.show_toast(getActivity(), "Password length should be between 5 to 12");
            return false;
        }


        if(TextUtils.isEmpty(n_password)|| n_password.length()<2 || n_password.length() > 12 ){
            Functions.show_toast(getActivity(), "Please enter valid new password");
            return false;
        }

        if (n_password.length() <= 5 ||  n_password.length() >=12) {
            Functions.show_toast(getActivity(), "Password length should be between 5 to 12");
            return false;
        }


        if (v_password.isEmpty()) {
            Functions.show_toast(getActivity(), "Please enter valid verify password");
            return false;
        }
        if (!v_password.equals(n_password)) {
            Functions.show_toast(getActivity(), "verify password doesnot match new password");
            return false;
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Goback:
                getActivity().onBackPressed();
                break;


            case R.id.change_password_btn:
                if(Check_Validation()){
                    Call_Api_for_change_pass();
                }
                break;
        }
    }

    private void Call_Api_for_change_pass() {
        Functions.Show_loader(getActivity(),false,false);

        JSONObject parameters = new JSONObject();
        try {
            parameters.put("user_id", Variables.sharedPreferences.getString(Variables.u_id,"0"));
            parameters.put("old_password",old_password_et.getText().toString());
            parameters.put("new_password",new_password_et.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiRequest.Call_Api(getActivity(), ApiLinks.changePassword, parameters, new Callback() {
            @Override
            public void Responce(String resp) {
                Functions.cancel_loader();
                try {
                    JSONObject response=new JSONObject(resp);
                    String code=response.optString("code");
                    JSONArray msg=response.optJSONArray("msg");
                    if(code.equals("200")) {
                        getActivity().onBackPressed();
                    }else {
                        String msg_txt =  response.getString("msg");
                        Functions.show_toast(getActivity(),msg_txt);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}