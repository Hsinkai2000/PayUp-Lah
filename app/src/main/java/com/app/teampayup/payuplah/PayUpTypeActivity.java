package com.app.teampayup.payuplah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;

public class PayUpTypeActivity extends AppCompatActivity {
    CircleImageView LoanInPayment, LoanOutPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_up_type);
        LoanInPayment = findViewById(R.id.btnLoanIn);
        LoanOutPayment = findViewById(R.id.btnLoanOut);

    }

    public void onClickGroupPay(View view) {
        Intent gotoLoanIn = new Intent(getApplicationContext(), LoanInActivity.class);
        startActivity(gotoLoanIn);
    }

    public void onClickSinglePay(View view) {
        Intent gotoLoanOut = new Intent(getApplicationContext(), LoanOutActivity.class);
        startActivity(gotoLoanOut);
    }

}
