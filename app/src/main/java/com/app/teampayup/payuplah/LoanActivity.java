package com.app.teampayup.payuplah;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class LoanActivity extends AppCompatActivity {
    ListView lvLoanOut;
    String TAG = "LoanActivity";
    List<OweMoney> loan = new ArrayList<OweMoney>();
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //init databasehelper
        db = new DatabaseHelper(getApplicationContext());
        lvLoanOut = findViewById(R.id.lvLoanOut);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoLoanMethods = new Intent(getApplicationContext(),PayUpTypeActivity.class);
                startActivity(gotoLoanMethods);
            }
        });
        loan.add(new OweMoney(55,"test","orchard", "123123", "Johnny",34.00));

        //get data
        Cursor res = db.getAllOweData();
        int OweMoneyID;
        String place, date, borrowerName, reason;
        double borrowAmount;

        //if data count == 0
        if (res.getCount() == 0){
            //show message
            showMessage("Error!", "No Data Found");
            return;
        }
        else{

            while (res.moveToNext()){
                OweMoneyID = res.getInt(0);
                reason = res.getString(1);
                place = res.getString(2);
                date = res.getString(3);
                borrowerName = res.getString(4);
                borrowAmount = res.getDouble(5);
                OweMoney oweMoney = new OweMoney(OweMoneyID, reason, place, date, borrowerName, borrowAmount);
                loan.add(oweMoney);
            }
            //showMessage("Data", loan.get(0).toString());
            LoanOutListViewAdapter loanOutListViewAdapter = new LoanOutListViewAdapter(this, loan);
            lvLoanOut.setAdapter(loanOutListViewAdapter);
        }

    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}
