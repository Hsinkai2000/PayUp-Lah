package com.app.teampayup.payuplah;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dx.dxloadingbutton.lib.LoadingButton;

import java.util.concurrent.TimeUnit;


public class AddProfile extends AppCompatActivity {
    public int mMorphCounter1 = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);
        final LoadingButton lb = (LoadingButton)findViewById(R.id.loading_btn);
        lb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lb.startLoading(); //start loading

                lb.loadingSuccessful();//show success
            }
        });

    }

}
