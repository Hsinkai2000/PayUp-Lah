package com.app.teampayup.payuplah;

import android.content.Intent;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.net.sip.SipAudioCall;
import android.nfc.Tag;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
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
import java.util.List;

public class ExpensePlanner extends AppCompatActivity {
    private static final String TAG = "ExpensePlanner";
    private ArrayList<Product> ProductList = new ArrayList<Product>();
    List<String> expenseString;
    private DatabaseHelper dbHelper;
    MaterialCalendarView calendar;
    RecyclerView recyclerView;
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_planner);
        Log.d(TAG, "onCreate: Activity Started");
        calendar = (MaterialCalendarView)findViewById(R.id.calendarView);
        recyclerView = findViewById(R.id.recyclerView);
        StringBuffer buffer = new StringBuffer();
        CheckDate();
        AdditemList(ProductList);
        initRecyclerView();
    }

    private void AdditemList(ArrayList List){
        DatabaseHelper db = new DatabaseHelper(this);
        Cursor res = db.RetrieveProducts();
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
            Product product = new Product(itemID, itemPrice, itemDate, itemdesc, itemcat, itemName, itemType);
            List.add(product);
        }
        StringBuffer sBuffer = new StringBuffer("id: " + itemID + "\nName: " + itemName + "\nPrice: " + itemPrice
                + "\ndate:  " + String.valueOf(itemDate) + "\ndesc: " +itemdesc+ "\ncat: " + itemcat+ "\ntype: " + itemType );
        showMessage("Details", sBuffer.toString());
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: showing recyclerview");
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, ProductList);
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
                //String selecteddate = String.valueOf(widget.getCurrentDate());
            }

        });

    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
