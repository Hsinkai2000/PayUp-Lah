package com.app.teampayup.payuplah;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;


import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    public static ProfileList listOfProfiles = new ProfileList();
    LinearLayout mDotLayout;
    SliderAdapter sliderAdapter;
    TextView[] mDots;

    public static ProfileList getProfileList() {
        return listOfProfiles;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager mSlideViewPager = findViewById(R.id.slideViewPager);
        mDotLayout = findViewById(R.id.dotsLayout);
        sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);
        /*
        CircleImageView circleView = findViewById(R.id.btnAddProfile);
        circleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoAddProfile = new Intent(getApplicationContext(),AddProfile.class);
                startActivity(gotoAddProfile);
            }
        });*/

    }

    public void addDotsIndicator(int position){
        mDots = new TextView[2];
        mDotLayout.removeAllViews();
        for (int i = 0 ; i < mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText((Html.fromHtml("&#8226;")));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mDotLayout.addView(mDots[i]);
        }

        //change selected to white
        if (mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.windowBackground));
        }
    }

    //check for change
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
