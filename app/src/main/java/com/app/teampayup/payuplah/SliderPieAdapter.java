package com.app.teampayup.payuplah;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;

public class SliderPieAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderPieAdapter(Context context)
    {

        this.context = context;
    }

    //Arrays
    //public int[] slides_pie{
       // R.layout.slider_layout;

    //}

    @Override
    public int getCount() {
        return 0;

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
}
