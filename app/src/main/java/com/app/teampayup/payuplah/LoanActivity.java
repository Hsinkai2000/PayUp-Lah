package com.app.teampayup.payuplah;

import android.content.DialogInterface;
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
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LoanActivity extends AppCompatActivity {
    ListView lvLoanOut, lvLoanIn;
    TextView txtLoanOutTotal, txtLoanInTotal;
    String TAG = "LoanActivity";
    List<OweMoney> loanout = new ArrayList<OweMoney>();
    List<LoanInMoney> loanIn = new ArrayList<LoanInMoney>();
    DatabaseHelper db;

    @Override
    protected void onResume() {
        super.onResume();
        displayGrids();
    }

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
        txtLoanOutTotal = findViewById(R.id.txtLoanOutTotal);
        txtLoanInTotal = findViewById(R.id.txtLoanInTotal);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoLoanMethods = new Intent(getApplicationContext(),PayUpTypeActivity.class);
                startActivity(gotoLoanMethods);
            }
        });

        displayGrids();
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void showDeleteMessageOwe(String title, String message, final int id){
        final int itemID = id;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                db.deleteOweByID(itemID);
                getgrids();
            }
        });
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void showDeleteMessage(String title, String message, final int id){
        final int itemID = id;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                db.deleteLoanInByID(itemID);
                displayGrids();
            }
        });
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void getgrids(){
        loanout.clear();
        loanIn.clear();
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
                BigDecimal borrowAmountoutBD = BigDecimal.valueOf(borrowAmountout);
                OweMoney oweMoney = new OweMoney(OweMoneyIDout, reasonout, placeout, dateout, borrowerNameout, borrowAmountoutBD);
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
                BigDecimal loanAmountInBD = BigDecimal.valueOf(loanAmountin);
                LoanInMoney loanInMoney = new LoanInMoney(LoanInMoneyID, placein, datein, loanNamein, loanAmountInBD, reasonin);
                loanIn.add(loanInMoney);
            }
            //showMessage("Data", loan.get(0).toString());
            //create and set adapter for loanout
            LoanOutListViewAdapter loanOutListViewAdapter = new LoanOutListViewAdapter(getApplicationContext(), loanout);
            lvLoanOut.setAdapter(loanOutListViewAdapter);
            //create and set adapter for borrow
            LoanInListViewAdapter loanInListViewAdapter = new LoanInListViewAdapter(getApplicationContext(),loanIn);
            lvLoanIn.setAdapter(loanInListViewAdapter);
            Double totalPriceLoanIn = 0.00;
            Double totalPriceLoanOut = 0.00;
            for (LoanInMoney lim: loanIn
                 ) {
                totalPriceLoanIn += Double.parseDouble(lim.loanAmount.toString());
            }
            for (OweMoney om: loanout
                 ) {
                totalPriceLoanOut += Double.parseDouble(om.borrowAmount.toString());
            }
            txtLoanInTotal.setText("$" + totalPriceLoanIn.toString());
            txtLoanOutTotal.setText("$" + totalPriceLoanOut.toString());
        }
    }

    public void displayGrids(){
        getgrids();

        lvLoanOut.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StringBuffer sBuffer = new StringBuffer("Reason: " + loanout.get(position).getReason() + "\nPlace: " + loanout.get(position).getPlace() + "\nDate: " + loanout.get(position).getDate()
                        + "\nBorrower's Name: " + loanout.get(position).getBorrowerName() + "\nBorrowed Amount: $" + loanout.get(position).getBorrowAmount());
                showMessage("Details", sBuffer.toString());
            }
        });

        lvLoanIn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StringBuffer sBuffer = new StringBuffer("Reason: " + loanIn.get(position).getReason() + "\nPlace: " + loanIn.get(position).getPlace() + "\nDate: " + loanIn.get(position).getDate()
                        + "\nBorrower's Name: " + loanIn.get(position).getLoanerName() + "\nBorrowed Amount: $" + loanIn.get(position).getLoanAmount());
                showMessage("Details", sBuffer.toString());
            }
        });

        lvLoanIn.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int itemID = loanIn.get(i).LoanInMoneyID;
                showDeleteMessage("Confirm", "Are you sure you want to delete?",itemID);
                return true;
            }
        });
        lvLoanOut.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int itemID = loanout.get(i).OweMoneyID;
                showDeleteMessageOwe("Confirm", "Are you sure you want to delete?", itemID);
                return true;
            }
        });
    }

}
