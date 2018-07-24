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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class LoanActivity extends AppCompatActivity {
    ListView lvLoanOut, lvLoanIn;
    String TAG = "LoanActivity";
    List<OweMoney> loanout = new ArrayList<OweMoney>();
    List<LoanInMoney> loanIn = new ArrayList<LoanInMoney>();
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
        lvLoanIn = findViewById(R.id.lvLoanIn);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoLoanMethods = new Intent(getApplicationContext(),PayUpTypeActivity.class);
                startActivity(gotoLoanMethods);
            }
        });

        //get loan data
        Cursor res = db.getAllOweData();
        int OweMoneyIDout;
        String placeout;
        String dateout;
        String borrowerNameout;
        String reasonout;
        double borrowAmountout;

        //get borrow Data
        Cursor resIn = db.getAllLoanInData();
        int LoanInMoneyID;
        String placein;
        String datein;
        String loanNamein;
        double loanAmountin;
        String reasonin;

        //if data count == 0
        if (res.getCount() == 0 && resIn.getCount() == 0){
            //show message
            showMessage("Error!", "No Loan Found");
            return;
        }
        else{

            while (res.moveToNext()){
                OweMoneyIDout = res.getInt(0);
                reasonout = res.getString(1);
                placeout = res.getString(2);
                dateout = res.getString(3);
                borrowerNameout = res.getString(4);
                borrowAmountout = res.getDouble(5);
                OweMoney oweMoney = new OweMoney(OweMoneyIDout, reasonout, placeout, dateout, borrowerNameout, borrowAmountout);
                Log.d("DEBUGREASON", "onCreate: " + oweMoney.ToString());
                loanout.add(oweMoney);
            }
            while(resIn.moveToNext()){
                LoanInMoneyID = resIn.getInt(0);
                reasonin = resIn.getString(1);
                placein = resIn.getString(2);
                datein = resIn.getString(3);
                loanNamein = resIn.getString(4);
                loanAmountin = resIn.getDouble(5);
                LoanInMoney loanInMoney = new LoanInMoney(LoanInMoneyID, placein, datein, loanNamein, loanAmountin, reasonin);
                loanIn.add(loanInMoney);
            }
            //showMessage("Data", loan.get(0).toString());
            //create and set adapter for loanout
            LoanOutListViewAdapter loanOutListViewAdapter = new LoanOutListViewAdapter(this, loanout);
            lvLoanOut.setAdapter(loanOutListViewAdapter);
            //create and set adapter for borrow
            LoanInListViewAdapter loanInListViewAdapter = new LoanInListViewAdapter(this,loanIn);
            lvLoanIn.setAdapter(loanInListViewAdapter);


            //final ArrayAdapter<OweMoney>oweArray = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1);
        }

        lvLoanOut.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StringBuffer sBuffer = new StringBuffer("reason: " + loanout.get(position).getReason() + "\nplace: " + loanout.get(position).getPlace() + "\nDate: " + loanout.get(position).getDate()
                + "\nBorrower's Name: " + loanout.get(position).getBorrowerName() + "\nBorrowed Amount: $" + loanout.get(position).getBorrowAmount());
                showMessage("Details", sBuffer.toString());
            }
        });

        lvLoanIn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StringBuffer sBuffer = new StringBuffer("reason: " + loanIn.get(position).getReason() + "\nplace: " + loanIn.get(position).getPlace() + "\nDate: " + loanIn.get(position).getDate()
                        + "\nBorrower's Name: " + loanIn.get(position).getLoanerName() + "\nBorrowed Amount: $" + loanIn.get(position).getLoanAmount());
                showMessage("Details", sBuffer.toString());
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
