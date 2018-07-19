package com.app.teampayup.payuplah;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;


import java.util.ArrayList;

import com.github.mikephil.charting.charts.PieChart;

public class SliderPieAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    private float[] yData={25.3f, 10.6f, 66.76f, 44.43f, 46.01f, 16.89f, 23.9f};
    private String[] xData= {"Mitch", "Jessica","Mohammad","Kelsey","Sam","Robert","Ashley"};
    private ViewPager mSlidePieView;
    PieChart pieChart;

    public SliderPieAdapter(Context context)
    {

        this.context = context;
    }

    //Arrays
    public int[] slides_pie =
    {
        R.layout.slider_layout
    };


    @Override
    public int getCount() {
        return 3;

    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view ==(RelativeLayout) o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout, container, false);

        PieChart slidePie = (PieChart)view.findViewById(R.id.idPieChart);

        pieChart = (PieChart)view.findViewById(R.id.idPieChart);
        //pieChart.setRotationEnabled(true);
        //pieChart.setDescription("People own me money");
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Own me Money!");
        pieChart.setCenterTextSize(10);
        pieChart.setDrawCenterText(true);

        addDataSet(pieChart);

        PieChart pieChart;
        container.addView(view);

        return  view;

       /* super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: starting to create chart");
        //mSlidePieView = (ViewPager) findViewById(R.id.slideViewPie);
        pieChart = (PieChart)findViewById(R.id.idPieChart);
        ViewPager mSlideViewPager = findViewById(R.id.slideViewPager);
        mDotLayout = findViewById(R.id.dotsLayout);
        sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);
        pieChart.setRotationEnabled(true);
        //pieChart.setDescription("People own me money");
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Own me Money!");
        pieChart.setCenterTextSize(10);
        pieChart.setDrawCenterText(true);

        addDataSet(pieChart);*/

    }

    private void addDataSet(PieChart pieChart) {

        //Log.d(TAG,"addDataSet started");
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
    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView((RelativeLayout)object);
    }
}
