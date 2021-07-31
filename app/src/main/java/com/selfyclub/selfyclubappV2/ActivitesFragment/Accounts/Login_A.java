package com.selfyclub.selfyclubappV2.ActivitesFragment.Accounts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.selfyclub.selfyclubappV2.ActivitesFragment.Profile.Profile_Tab_F;
import com.selfyclub.selfyclubappV2.ApiClasses.ApiLinks;
import com.selfyclub.selfyclubappV2.ApiClasses.ApiRequest;
import com.selfyclub.selfyclubappV2.Interfaces.Callback;
import com.selfyclub.selfyclubappV2.Main_Menu.MainMenuActivity;
import com.selfyclub.selfyclubappV2.Models.User_Model;
import com.selfyclub.selfyclubappV2.R;
import com.selfyclub.selfyclubappV2.SimpleClasses.Functions;
import com.selfyclub.selfyclubappV2.SimpleClasses.Variables;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
//import com.google.android.gms.auth.api.Auth;
//import com.google.android.gms.auth.api.signin.GoogleSignIn;
//import com.google.android.gms.auth.api.signin.GoogleSignInClient;


public class Login_A extends AppCompatActivity {

    CallbackManager callbackManager;
    LoginButton loginButton;
    FirebaseUser firebaseUser;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 100;
    private FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;
    User_Model user_model = new User_Model();
    View top_view;
    long mBackPressed;
    TextView login_title_txt;
    AccessToken accessToken;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        FacebookSdk.sdkInitialize(getApplicationContext());
        if (Build.VERSION.SDK_INT == 26) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_USER);
        }
        getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));

        this.getWindow()
                .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        sharedPreferences=getSharedPreferences(Variables.pref_name,MODE_PRIVATE);
        top_view=findViewById(R.id.top_view);
        login_title_txt=findViewById(R.id.login_title_txt);
        SpannableString ss = new SpannableString("By signing up, you confirm that you agree to our \n Terms of Use and have read and understood \n our Privacy Policy.");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                //Open_Privacy_policy();
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan, 99, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView textView = (TextView) findViewById(R.id.login_terms_condition_txt);
        textView.setText(ss);
        textView.setClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());


        printKeyHash();

        getfbdata();
        getgoogledata();

    }

    private void getgoogledata() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null){
            Toast.makeText(this, "Already Logged IN Google", Toast.LENGTH_SHORT).show();

            Profile_Tab_F DOBF = new Profile_Tab_F();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left, R.anim.in_from_left, R.anim.out_to_right);
            transaction.addToBackStack(null);
            transaction.replace(R.id.login_f, DOBF).commit();
        }
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    private void signIn() {

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void printKeyHash()  {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName() , PackageManager.GET_SIGNATURES);
            for(Signature signature:info.signatures)
            {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.i("keyhash" , Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    private void getfbdata(){
        //Intent intn = new Intent(Login_A.this, MainMenuActivity.class);
        callbackManager = CallbackManager.Factory.create();



        accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if(isLoggedIn){
            Toast.makeText(this, "Already Logged IN", Toast.LENGTH_SHORT).show();

            Profile_Tab_F DOBF = new Profile_Tab_F();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left, R.anim.in_from_left, R.anim.out_to_right);
            transaction.addToBackStack(null);
            transaction.replace(R.id.login_f, DOBF).commit();
        }


        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("email","public_profile"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                accessToken = AccessToken.getCurrentAccessToken();
                handleFacebookAccessToken(accessToken);
//                startActivity(intn);
//                finish();
            }

            @Override
            public void onCancel() {
                // App code
                Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(getApplicationContext(),"Failed to Login", Toast.LENGTH_LONG).show();
                Log.e("Error",exception.toString());
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        Log.d("resp_token",token+"");

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Functions.Show_loader(Login_A.this,false,false);
                            final String id = Profile.getCurrentProfile().getId();
                            GraphRequest request = GraphRequest.newMeRequest(token, new GraphRequest.GraphJSONObjectCallback() {

                                @Override
                                public void onCompleted(JSONObject user, GraphResponse graphResponse) {

                                    Functions.cancel_loader();
                                    Log.d("resp",user.toString());
                                    String fname=""+user.optString("first_name");
                                    String lname=""+user.optString("last_name");
                                    String email= ""+user.optString("email");
                                    String auth_token = token.getToken();


                                    user_model = new User_Model();

                                    user_model.fname = fname;
                                    user_model.email = email;
                                    user_model.lname = lname;
                                    user_model.socail_id = id;
                                    user_model.socail_type = "facebook";
                                    user_model.auth_tokon = auth_token;

                                    call_api_for_login(""+id,
                                            "facebook",
                                            auth_token);

                                }
                            });

                            // here is the request to facebook sdk for which type of info we have required
                            Bundle parameters = new Bundle();
                            parameters.putString("fields", "last_name,first_name,email");
                            request.setParameters(parameters);
                            request.executeAsync();
                        } else {
                            Functions.cancel_loader();
                            Functions.show_toast(Login_A.this, "Authentication failed.");
                        }

                    }
                });
    }

    private void call_api_for_login(String s, String facebook, String auth_token) {

        JSONObject parameters = new JSONObject();
        try {

            parameters.put("social_id", s);
            parameters.put("social",""+facebook);
            parameters.put("auth_token", ""+auth_token);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Functions.Show_loader(this,false,false);
        ApiRequest.Call_Api(this, ApiLinks.registerUser, parameters, new Callback() {
            @Override
            public void Responce(String resp) {
                Functions.cancel_loader();
                parse_login_data(resp,auth_token);

            }
        });
    }
    public void parse_login_data(String loginData,String authtoken){
        try {
            JSONObject jsonObject=new JSONObject(loginData);
            String code=jsonObject.optString("code");
            if(code.equals("200")){

                JSONObject jsonArray=jsonObject.getJSONObject("msg");
                JSONObject userdata = jsonArray.getJSONObject("User");
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(Variables.u_id,userdata.optString("id"));
                editor.putString(Variables.f_name,userdata.optString("first_name"));
                editor.putString(Variables.l_name,userdata.optString("last_name"));
                editor.putString(Variables.u_name,userdata.optString("username"));
                editor.putString(Variables.gender,userdata.optString("gender"));
                editor.putString(Variables.u_pic,userdata.optString("profile_pic"));
                editor.putString(Variables.auth_token,authtoken);
                editor.putBoolean(Variables.islogin,true);
                editor.commit();
                Variables.user_id=Functions.getSharedPreference(this).getString(Variables.u_id,"");

                sendBroadcast(new Intent("newVideo"));


                Variables.Reload_my_videos=true;
                Variables.Reload_my_videos_inner=true;
                Variables.Reload_my_likes_inner=true;
                Variables.Reload_my_notification=true;
                top_view.setVisibility(View.GONE);


                finish();
                Profile_Tab_F DOBF = new Profile_Tab_F();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left, R.anim.in_from_left, R.anim.out_to_right);
                transaction.addToBackStack(null);
                transaction.replace(R.id.login_f, DOBF).commit();

            }
            else if(code.equals("201") && !jsonObject.optString("msg").contains("have been blocked")){
                open_dob_fragment("social");
            }
            else{
                Toast.makeText(this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void open_dob_fragment(String fromWhere) {
        DOB_F DOBF = new DOB_F();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left, R.anim.in_from_left, R.anim.out_to_right);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user_model", user_model);
        bundle.putString("fromWhere",fromWhere);
        DOBF.setArguments(bundle);
        transaction.addToBackStack(null);
        transaction.replace(R.id.login_f, DOBF).commit();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            Log.e("googleresult","process");
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);

            if (account != null) {
                String id=account.getId();
                String fname=""+account.getGivenName();
                String lname=""+account.getFamilyName();
                String auth_token =  account.getIdToken();
                String email = account.getEmail();

                Log.d(Variables.tag, "signInResult:auth_token =" + auth_token);
                // if we do not get the picture of user then we will use default profile picture


                user_model = new User_Model();

                user_model.fname = fname;
                user_model.email = email;
                user_model.lname = lname;
                user_model.socail_id = id;
                user_model.socail_type = "google";
                user_model.auth_tokon = auth_token;


                call_api_for_login(""+id,
                        "google",
                        auth_token);


            }
        } catch (ApiException e) {
            Log.e("Google Login error", "signInResult:failed code=" + e.getStatusCode());
        }
    }

}
