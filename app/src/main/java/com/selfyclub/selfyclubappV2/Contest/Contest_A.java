package com.selfyclub.selfyclubappV2.Contest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.selfyclub.selfyclubappV2.ActivitesFragment.Video_Recording.Video_Recoder_A;
import com.selfyclub.selfyclubappV2.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class Contest_A extends AppCompatActivity implements PaymentResultListener
{
    Button paybtn, couponbtn, sbbtn;
    TextView paytext, tvCoupon ;
    ImageView ivCont;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest);
        Checkout.preload(getApplicationContext());

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        paytext=(TextView)findViewById(R.id.paytext);
        paybtn=(Button)findViewById(R.id.paybtn);
        couponbtn=(Button)findViewById(R.id.couponbtn);
        sbbtn=(Button)findViewById(R.id.subbtn);
        ivCont=findViewById(R.id.ivcont);
        tvCoupon=(TextView)findViewById(R.id.tvCoupon);

        sbbtn.setVisibility(View.GONE);
        tvCoupon.setVisibility(View.GONE);



        sbbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String couponcode = tvCoupon.getText().toString();

                if(couponcode.equals("111222") || couponcode.equals("222333"))
                {
                    Intent i = new Intent(Contest_A.this, Video_Recoder_A.class);
                    startActivity(i);
                }
                else
                    Toast.makeText(Contest_A.this, "Invalid Coupon Code", Toast.LENGTH_SHORT).show();
            }
        });

        couponbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ivCont.setVisibility(View.GONE);
                paybtn.setVisibility(View.GONE);
                couponbtn.setVisibility(View.GONE);
                sbbtn.setVisibility(View.VISIBLE);
                tvCoupon.setVisibility(View.VISIBLE);


            }
        });

        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makepayment();
            }
        });

    }

    private void makepayment()
    {

        Checkout checkout = new Checkout();
        checkout.setKeyID("E5VsLtWbP9ZrAJ");
//        checkout.setKeyID("rzp_test_Ca8KTzN5oy1D5v");

        checkout.setImage(R.drawable.image_placeholder);
        final Activity activity = this;

        try {
            JSONObject options = new JSONObject();

            options.put("name", "Asia Digital Smart Marketing Services Private Limited");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", "1000");//300 X 100
            options.put("prefill.email", "asiastardigital@gmail.com");
            options.put("prefill.contact","9496928134");
            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }

//pay_HJH8WdhjFaFrY6
    @Override
    public void onPaymentSuccess(String s) {
        paytext.setText("Successful payment ID :"+s);
    }

    @Override
    public void onPaymentError(int i, String s) {
        paytext.setText("Failed and cause is :"+s);
    }
}