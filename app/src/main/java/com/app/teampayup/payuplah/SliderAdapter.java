package com.app.teampayup.payuplah;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    //Arrays
    public int[] slide_images ={
            R.drawable.addprofile,
            R.drawable.addprofile,
            R.drawable.addprofile,
            R.drawable.addprofile,
            R.drawable.addprofile,
            R.drawable.addprofile
    };
    public String[] slide_headings ={
            "Add Profile",
            "Add Profile",
            "Add Profile",
            "Add Profile",
            "Add Profile",
            "Add Profile"
    };

    @Override
    public int getCount() {
        return 2;//number of pages
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout_fragment1, container, false);
        final CircleImageView btnAdd = (CircleImageView)view.findViewById(R.id.btnAddProfile);
        final CircleImageView btnAdd1 = view.findViewById(R.id.btnAdd1);
        final CircleImageView btnAdd2 = view.findViewById(R.id.btnAdd2);
        TextView tv1 = view.findViewById(R.id.slideDesc1);
        TextView tv2 = view.findViewById(R.id.slideDesc2);
        TextView tv3 = view.findViewById(R.id.slideDesc3);
        final int cPos = position;
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cPos == 0){
                    Intent gotoAddProfile = new Intent(context, AddProfile.class);
                    context.startActivity(gotoAddProfile);
                }
                else if (cPos == 1){
                    Intent gotoAddProfile = new Intent(context, AddProfile.class);
                    context.startActivity(gotoAddProfile);
                }
            }
        });
        btnAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cPos == 0){
                    Intent gotoAddProfile = new Intent(context, AddProfile.class);
                    context.startActivity(gotoAddProfile);
                }
                else if (cPos == 1){
                    Intent gotoAddProfile = new Intent(context, AddProfile.class);
                    context.startActivity(gotoAddProfile);
                }
            }
        });
        btnAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cPos == 0){
                    Intent gotoAddProfile = new Intent(context, AddProfile.class);
                    context.startActivity(gotoAddProfile);
                }
                else if (cPos == 1){
                    Intent gotoAddProfile = new Intent(context, AddProfile.class);
                    context.startActivity(gotoAddProfile);
                }
            }
        });

        btnAdd.setImageResource(slide_images[position]);
        btnAdd1.setImageResource(slide_images[position]);
        btnAdd2.setImageResource(slide_images[position]);

        tv1.setText(slide_headings[position]);
        tv2.setText(slide_headings[position]);
        tv3.setText(slide_headings[position]);

        container.addView(view);
        return view;
    }

    //stop at the end
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
