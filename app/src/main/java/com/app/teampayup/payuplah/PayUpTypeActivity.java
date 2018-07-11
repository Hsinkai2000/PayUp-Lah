package com.app.teampayup.payuplah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;

public class PayUpTypeActivity extends AppCompatActivity {
    CircleImageView groupPayment, singlePayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_up_type);
        groupPayment = findViewById(R.id.groupPayment);
        singlePayment = findViewById(R.id.singlePayment);

    }

    public void onClickGroupPay(View view) {
        Intent gotoGroupPay = new Intent(getApplicationContext(), GroupPayActivity.class);
        startActivity(gotoGroupPay);
    }

    public void onClickSinglePay(View view) {
        Intent gotoSinglePay = new Intent(getApplicationContext(), SinglePayActivity.class);
        startActivity(gotoSinglePay);
    }

}
