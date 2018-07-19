package com.app.teampayup.payuplah;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;


import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.graphics.Color.GRAY;

public class MainActivity extends AppCompatActivity {
    public static ProfileList listOfProfiles = new ProfileList();
    LinearLayout mDotLayout;
    SliderAdapter sliderAdapter;
    SliderPieAdapter sliderPieAdapter;
    private static String TAG ="MainActivity";


   // private float[] yData={25.3f, 10.6f, 66.76f, 44.43f, 46.01f, 16.89f, 23.9f};
   // private String[] xData= {"Mitch", "Jessica","Mohammad","Kelsey","Sam","Robert","Ashley"};
    PieChart pieChart;

    TextView[] mDots;

    public static ProfileList getProfileList() {
        return listOfProfiles;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: starting to create chart");
        //mSlidePieView = (ViewPager) findViewById(R.id.slideViewPie);
        //pieChart = (PieChart)findViewById(R.id.idPieChart);
        ViewPager mSlideViewPager = findViewById(R.id.slideViewPager);
        ViewPager mSlidePieView =findViewById(R.id.idPieChart);
        //PieChart pieChart = (PieChart) findViewById(R.id.idPieChart);

        mDotLayout = findViewById(R.id.dotsLayout);
        sliderAdapter = new SliderAdapter(this);
        sliderPieAdapter = new SliderPieAdapter(this);

        mSlidePieView.setAdapter(sliderPieAdapter);

        mSlideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);
        /*
        pieChart.setRotationEnabled(true);
        //pieChart.setDescription("People own me money");
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Own me Money!");
        pieChart.setCenterTextSize(10);
        pieChart.setDrawCenterText(true);

        addDataSet(pieChart);
        */


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

    /*
    private void addDataSet(PieChart pieChart) {

        Log.d(TAG,"addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i =0; i < yData.length; i ++)
        {
            yEntrys.add(new PieEntry(yData[i], i));
        }
        for(int i =1; i < xData.length; i++)
        {
            xEntrys.add((xData[i]));
        }

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys,"People own me");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add color to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);

        pieDataSet.setColors(colors);

        //add legend to chart

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART); //here got problem shouldn't have a line

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
    */



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
