package com.selfyclub.selfyclubappV2.Main_Menu.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.selfyclub.selfyclubappV2.SimpleClasses.Variables;

public class Util{


    public static void setLocation(Context mContext, String latitude, String longitude){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Variables.latlocation,String.valueOf(latitude));
        editor.putString(Variables.lnglocation,String.valueOf(longitude));
        editor.apply();
    }


}
