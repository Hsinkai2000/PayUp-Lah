package com.app.teampayup.payuplah;

import android.content.Intent;
import android.database.Cursor;
import android.icu.text.DateFormat;
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

import java.io.Console;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;

public class ExpensePlanner extends AppCompatActivity {
    private static final String TAG = "ExpensePlanner";
    private ArrayList<Product> ProductList = new ArrayList<Product>();
    List<String> expenseString;
    private DatabaseHelper dbHelper;
    MaterialCalendarView calendar;
    RecyclerView recyclerView;
    String newDateTime;
    String endDateTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_planner);
        Log.d(TAG, "onCreate: Activity Started");
        calendar = (MaterialCalendarView)findViewById(R.id.calendarView);
        recyclerView = findViewById(R.id.recyclerView);
        StringBuffer buffer = new StringBuffer();

        //get current datetime
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String strMonth = "";
        String strDay = "";
        String strDay2 = "";
        if (month+1 < 10){
            strMonth = "0" + (month+1);
        }
        else{
            strMonth= String.valueOf(month+1);
        }
        if (day<10){
            strDay = "0" + (day-1);
            strDay2 = "0"+day;
        }
        else{
            strDay = String.valueOf(day-1);
            strDay2 = String.valueOf(day);
        }
        String strYear = String.valueOf(cal.get(Calendar.YEAR));
        String currentstartDate = strYear + "-" + strMonth + "-" + strDay + " 23:59:59";
        String currentendDate = strYear + "-" + strMonth + "-" + strDay2 + " 23:59:59";

        //set selected current date
        calendar.setSelectedDate(Calendar.getInstance());

        newDateTime = currentstartDate;
        endDateTime = currentendDate;
        CheckDate();
        AdditemList(ProductList);
        initRecyclerView();
    }

    private void AdditemList(ArrayList List){
        DatabaseHelper db = new DatabaseHelper(this);
        Log.d("DATETIMECHECK", "before database start dateTime : "+ newDateTime);
        Log.d("DATETIMECHECK", "before database start dateTime : "+ endDateTime);
        Cursor res = db.RetrieveProducts(newDateTime, endDateTime);
        Log.d("DATETIMECHECK", "database.count: "+ res.getCount());
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
        /*StringBuffer sBuffer = new StringBuffer("id: " + newDateTime + "\nName: " + itemName + "\nPrice: " + itemPrice
                + "\ndate:  " + String.valueOf(itemDate) + "\ndesc: " +itemdesc+ "\ncat: " + itemcat+ "\ntype: " + itemType );
        showMessage("Details", sBuffer.toString());*/
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
        final Global g = (Global)getApplication();
        calendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                //clear list
                ProductList.clear();
                Log.d(TAG, "onDateSelected: DateMarked");
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                //SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                String strMonth;
                String strDay;
                String strDay2;
                //get year
                int year = date.getYear();
                //get month and check for leading 0
                int month = date.getMonth();
                if ((month+1) < 10){
                    strMonth = "0" + (month+1);
                }
                else{
                    strMonth = String.valueOf(month+1);
                }

                //get day and check for leading 0
                int day = date.getDay();
                if (day < 10){
                    strDay = "0" + (day-1);
                    strDay2 = "0" + day;
                }
                else{
                    strDay = String.valueOf(day-1);
                    strDay2 = String.valueOf(day);
                }

                newDateTime = year + "-" + strMonth + "-" + strDay + " 23:59:59";
                endDateTime = year + "-" + strMonth + "-" + strDay2 + " 23:59:59";
                Log.d("DATETIMECHECK", "onDateSelected start dateTime : "+ newDateTime);
                Log.d("DATETIMECHECK", "onDateSelected end dateTime : "+ endDateTime);
                Log.d("DATETIMECHECK", "onDateSelected old dateTime : "+ date.toString());
                AdditemList(ProductList);
                initRecyclerView();
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
