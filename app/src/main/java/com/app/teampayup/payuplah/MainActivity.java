package com.app.teampayup.payuplah;

import android.content.Intent;
import android.database.Cursor;
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


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.graphics.Color.GRAY;

public class MainActivity extends AppCompatActivity {
    public static ProfileList listOfProfiles = new ProfileList();
    TextView txtAmtSpent, txtAmtRecieve;
    LinearLayout mDotLayout;
    SliderAdapter sliderAdapter;
    ArrayList<OweMoney> oweMoneyList = new ArrayList<OweMoney>();
    ArrayList<Product> productList = new ArrayList<Product>();
    private static String TAG ="MainActivity";

    TextView[] mDots;

    public static ProfileList getProfileList() {
        return listOfProfiles;
    }

    @Override
    protected void onResume() {
        super.onResume();
        showDisplay();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: starting to create chart");

        //find views
        ViewPager mSlideViewPager = findViewById(R.id.slideViewPager);
        txtAmtRecieve = findViewById(R.id.txtAmtRecieve);
        txtAmtSpent = findViewById(R.id.txtAmtSpent);
        mDotLayout = findViewById(R.id.dotsLayout);
        sliderAdapter = new SliderAdapter(this);


        mSlideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        showDisplay();


    }
    public void showDisplay(){
        //get amt spent from database
        DatabaseHelper db = new DatabaseHelper(this);
        Double totalOwe = 0.00;
        Double totalSpent = 0.00;
        oweMoneyList.clear();
        productList.clear();
        Cursor res = db.GetProducts();
        int itemID = 0;
        String itemName = null;
        String itemDate = null;
        String itemdesc= null;
        String itemcat= null;
        String itemType= null;
        double itemPrice = 0;
        while (res.moveToNext()){
            itemID = res.getInt(0);
            itemName = res.getString(1);
            itemPrice = res.getDouble(2);
            itemDate = res.getString(3);
            itemdesc = res.getString(4);
            itemcat = res.getString(5);
            itemType = res.getString(6);
            BigDecimal itemPriceBD = BigDecimal.valueOf(itemPrice);
            Product product = new Product(itemID, itemPriceBD, itemDate, itemdesc, itemcat, itemName, itemType);
            productList.add(product);
        }

        Cursor res2 = db.getAllOweData();
        int OweMoneyIDout;
        String placeout;
        String dateout;
        String borrowerNameout;
        String reasonout;
        double borrowAmountout;
        while (res2.moveToNext()){
            OweMoneyIDout = res2.getInt(0);
            reasonout = res2.getString(1);
            placeout = res2.getString(2);
            dateout = res2.getString(3);
            borrowerNameout = res2.getString(4);
            borrowAmountout = res2.getDouble(5);
            BigDecimal borrowerAmountoutBD = BigDecimal.valueOf(borrowAmountout);
            OweMoney oweMoney = new OweMoney(OweMoneyIDout, reasonout, placeout, dateout, borrowerNameout, borrowerAmountoutBD);
            oweMoneyList.add(oweMoney);
        }

        res.close();
        res2.close();
        if (productList.size() == 0){
            Log.d("TESTING123", "onCreate: nothing in spend list");
        }
        else {
            for (Product p : productList
                    ) {
                Log.d("TESTING123", (p.getDate()));
                if (p.getType().equals("Expense")) {
                    totalSpent += p.getPrice().doubleValue();
                }
            }
        }
        if (oweMoneyList.size() == 0){
            Log.d("TESTING123", "onCreate: nothing in owe list");
        }
        else {
            for (OweMoney o : oweMoneyList
                    ) {
                Log.d("TESTING123", (o.getDate()));
                totalOwe += o.getBorrowAmount().doubleValue();
            }
        }

        //res2.close();
        txtAmtSpent.setText("$" + totalSpent.toString());
        txtAmtRecieve.setText("$" + totalOwe.toString());

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
