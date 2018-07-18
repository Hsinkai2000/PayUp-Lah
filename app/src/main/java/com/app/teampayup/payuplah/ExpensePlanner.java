package com.app.teampayup.payuplah;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.sip.SipAudioCall;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ExpensePlanner extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ArrayList<String> mNames = new ArrayList<String>();
    private ArrayList<String> Income = new ArrayList<String>();
    private ArrayList<String> Expense = new ArrayList<String>();
    private DatabaseHelper dbHelper;
    MaterialCalendarView calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onCreate", "onCreate: Activity Started");
        setContentView(R.layout.activity_expense_planner);
        calendar = (MaterialCalendarView)findViewById(R.id.calendarView);
        CheckDate();
        initImage();
    }
    private void initImage(){
        Log.d(TAG, "initImage: preparing images");
        Income = dbHelper.RetrieveIncome();
        Expense = dbHelper.RetrieveExpense();
        initRecyclerView();
    }
    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, Income, Expense);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void CheckDate(){
        //Log.d(TAG, "initBitmaps: preparing bitmaps");
        Product objProd = new Product();
        calendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                String selecteddate = String.valueOf(widget.getCurrentDate());
                Intent i = new Intent(ExpensePlanner.this, DatabaseHelper.class);
                i.putExtra("DateSelected", selecteddate);
                startActivity(i);
            }

        });

    }
}
