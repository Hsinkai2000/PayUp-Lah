package com.app.teampayup.payuplah;

import android.content.Intent;
import android.database.Cursor;
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
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ExpensePlanner extends AppCompatActivity {
    private static final String TAG = "ExpensePlanner";
    private ArrayList<String> Income = new ArrayList<String>();
    private ArrayList<String> Expense = new ArrayList<String>();
    private DatabaseHelper dbHelper;
    MaterialCalendarView calendar;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_planner);
        Log.d(TAG, "onCreate: Activity Started");
        calendar = (MaterialCalendarView)findViewById(R.id.calendarView);
        recyclerView = findViewById(R.id.recyclerView);
        CheckDate();
        initImage();
        initRecyclerView();
    }
    private void initImage(){
        Log.d(TAG, "initImage: preparing images");
        Cursor res = dbHelper.RetrieveExpense();
        while (res.moveToNext()){
            Expense.add(res.getString(0));
        }
        //Income = dbHelper.RetrieveIncome();
        //Expense = dbHelper.RetrieveExpense();
    }
    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: showing recyclerview");
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, Income, Expense);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }

    private void CheckDate(){
        Log.d(TAG, "Check date: checking date from calendar");
        Product objProd = new Product();
        calendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Log.d(TAG, "onDateSelected: DateMarked");
                Toast.makeText(ExpensePlanner.this, "" + date, Toast.LENGTH_SHORT).show();
                String selecteddate = String.valueOf(widget.getCurrentDate());
                Intent i = new Intent(ExpensePlanner.this, DatabaseHelper.class);
                i.putExtra("DateSelected", selecteddate);
                startActivity(i);
            }

        });

    }
}
