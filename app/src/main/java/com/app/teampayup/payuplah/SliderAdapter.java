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
            R.drawable.expenses,
            R.drawable.loan,
            R.drawable.history,
            R.drawable.piechart,
            R.drawable.addprofile
           // R.drawable.addprofile
    };
    public String[] slide_headings ={
            "Add Expenditure",
            "Add Loans",
            "View Expenditure",
            "Pie Chart",
            "Add Profile"
           // "Pie Chart"
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
                    Intent gotoExpenditure = new Intent(context, PurchasedActivity.class);
                    context.startActivity(gotoExpenditure);
                }
                else if (cPos == 1){
                    Intent goToPieChart = new Intent(context, ExpensePie.class);
                    context.startActivity(goToPieChart);
                }
            }
        });
        btnAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cPos == 0){
                    Intent goToLoan = new Intent(context, LoanActivity.class);
                    context.startActivity(goToLoan);
                }
                else if (cPos == 1){
                Intent goToAddProfile = new Intent(context, AddProfile.class);
                    context.startActivity(goToAddProfile);
                }
            }
        });
        btnAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cPos == 0){
                    Intent goToViewExpense = new Intent(context, ExpensePlanner.class);
                    context.startActivity(goToViewExpense);
                }
            }
        });
        if (cPos == 0){
            btnAdd.setImageResource(slide_images[0]);
            btnAdd1.setImageResource(slide_images[1]);
            btnAdd2.setImageResource(slide_images[2]);

            tv1.setText(slide_headings[0]);
            tv2.setText(slide_headings[1]);
            tv3.setText(slide_headings[2]);
        }
        else{
            btnAdd.setImageResource(slide_images[3]);
            btnAdd1.setImageResource(slide_images[4]);
            btnAdd2.setImageResource(android.R.color.transparent);

            tv1.setText(slide_headings[3]);
            tv2.setText(slide_headings[4]);
            tv3.setText(null);
        }

        container.addView(view);
        return view;
    }

    //stop at the end
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
