package com.selfyclub.selfyclubappV2.ActivitesFragment;

import android.content.SharedPreferences;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.selfyclub.selfyclubappV2.LocationActivity;
import com.selfyclub.selfyclubappV2.R;
import com.selfyclub.selfyclubappV2.ApiClasses.ApiLinks;
import com.selfyclub.selfyclubappV2.ApiClasses.ApiRequest;
import com.selfyclub.selfyclubappV2.Interfaces.Callback;
import com.selfyclub.selfyclubappV2.SimpleClasses.Functions;
import com.selfyclub.selfyclubappV2.SimpleClasses.Variables;

import org.json.JSONException;
import org.json.JSONObject;

public class Splash_A extends LocationActivity {


    CountDownTimer countDownTimer;
    String lang = null;
    Double latt, lng;
    String url, LattS, LongS;

    LocationManager locationManager;
    LocationListener locationListener;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

//        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);

        Variables.sharedPreferences = Functions.getSharedPreference(getApplicationContext());

//        Variables.sharedPreferences = getSharedPreferences(Variables.pref_name, MODE_PRIVATE);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        lang = sharedPreferences.getString(Variables.language, null);

        if(Variables.sharedPreferences.getString(Variables.device_id,"0").equals("0")) {
            Call_api_register_device();
        }
//        else
//            Set_Timer();


    }


//    public void Set_Timer(){
//        countDownTimer = new CountDownTimer(2500, 500) {
//
//            public void onTick(long millisUntilFinished) {
//                // this will call on every 500 ms
//            }
//
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            public void onFinish() {
//
////                checkVersion();
//
//
//
//
//                if (lang == null) {
//                    Intent intent = new Intent(Splash_A.this, Change_language.class);
//                    startActivity(intent);
//                    overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
//                    finish();
//                }
//                else {
//                    Intent intent = new Intent(Splash_A.this, MainMenuActivity.class);
//                    startActivity(intent);
//                    overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
//                    finish();
//                }
//            }
//        }.start();
//    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
//        countDownTimer.cancel();
    }

    public void Call_api_register_device(){
        String androidId = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        JSONObject param=new JSONObject();
        try {
            param.put("key",androidId);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiRequest.Call_Api(this, ApiLinks.registerDevice, param, new Callback() {
            @Override
            public void Responce(String resp) {

                try {
                    JSONObject jsonObject=new JSONObject(resp);
                    String code=jsonObject.optString("code");
                    if(code.equals("200")){

//                        Set_Timer();

                        JSONObject msg=jsonObject.optJSONObject("msg");
                        JSONObject Device=msg.optJSONObject("Device");
                        SharedPreferences.Editor editor2 =  Variables.sharedPreferences.edit();
                        editor2.putString(Variables.device_id, Device.optString("id")).commit();


                    }else {
                        Call_api_show_register_device();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        });
       }

    public void Call_api_show_register_device(){
        String androidId = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        JSONObject param=new JSONObject();
        try {
            param.put("key",androidId);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiRequest.Call_Api(this, ApiLinks.showDeviceDetail, param, new Callback() {
            @Override
            public void Responce(String resp) {

                try {
                    JSONObject jsonObject=new JSONObject(resp);
                    String code=jsonObject.optString("code");
                    if(code.equals("200")){

//                        Set_Timer();

                        JSONObject msg=jsonObject.optJSONObject("msg");
                        JSONObject Device=msg.optJSONObject("Device");
                        SharedPreferences.Editor editor2 =  Variables.sharedPreferences.edit();
                        editor2.putString(Variables.device_id, Device.optString("id")).commit();


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        });
    }

   /* public void checkVersion()
    {
        String  versionName = BuildConfig.VERSION_NAME;

        JSONObject parameters = new JSONObject();
        try {
            parameters.put("version",versionName);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiRequest.Call_Api(this, ApiLinks.showUpdatePopup, parameters, new Callback() {
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.update_message));
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent implicit = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.codeleven.selfyclub"));
                startActivity(implicit);
                finish();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setCancelable(false);

        builder.show();


    }*/

}
