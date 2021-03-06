package com.app.teampayup.payuplah;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.dx.dxloadingbutton.lib.LoadingButton;
import com.wafflecopter.multicontactpicker.ContactResult;
import com.wafflecopter.multicontactpicker.MultiContactPicker;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class LoanInActivity extends AppCompatActivity {
    EditText txtDate, txtcontact, txtAmountOwed, txtPlace, txtreason;
    Button btnDatePicker;
    LoadingButton btnDoneOwe;
    private int mYear, mMonth, mDay;
    private static final int CONTACT_PICKER_REQUEST = 991;
    List<ContactResult> results;
    CheckBox chkSplit;
    int MY_PERMISSIONS_REQUEST_READ_CONTACTS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_in);

        //initialise items
        Button btngocontacts = findViewById(R.id.btngocontacts);
        btnDoneOwe = findViewById(R.id.btnDoneOwe);
        txtAmountOwed = findViewById(R.id.txtAmountOwed);
        txtPlace = findViewById(R.id.txtPlace);
        txtDate = (EditText) findViewById(R.id.in_date);
        btnDatePicker = (Button) findViewById(R.id.btn_date);
        txtcontact = findViewById(R.id.txtcontact);
        chkSplit = findViewById(R.id.chkSplit);
        txtreason = findViewById(R.id.txtReason);

        //button to select contacts clicked
        btngocontacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(LoanInActivity.this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                    new MultiContactPicker.Builder(LoanInActivity.this) //Activity/fragment context
                            .theme(R.style.MyCustomPickerTheme) //Optional - default: MultiContactPicker.Azure
                            .hideScrollbar(false) //Optional - default: false
                            .showTrack(true) //Optional - default: true
                            .searchIconColor(Color.WHITE) //Optional - default: White
                            .setChoiceMode(MultiContactPicker.CHOICE_MODE_SINGLE) //Optional - default: CHOICE_MODE_MULTIPLE
                            .handleColor(ContextCompat.getColor(LoanInActivity.this, R.color.colorTransparentWhite)) //Optional - default: Azure Blue
                            .bubbleColor(ContextCompat.getColor(LoanInActivity.this, R.color.colorPrimary)) //Optional - default: Azure Blue
                            .bubbleTextColor(Color.WHITE) //Optional - default: White
                            .showPickerForResult(CONTACT_PICKER_REQUEST);
                }
                else{
                    ActivityCompat.requestPermissions(LoanInActivity.this,
                            new String[]{Manifest.permission.READ_CONTACTS},
                            MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                    Toast.makeText(LoanInActivity.this, "Remember to go into settings and enable the contacts permission.", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnDoneOwe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create objects
                btnDoneOwe.startLoading();
                if (!txtPlace.getText().toString().isEmpty() && results.size() != 0 && !txtreason.getText().toString().isEmpty()) {
                    DatabaseHelper dbHelper = new DatabaseHelper(LoanInActivity.this);
                    String place = txtPlace.getText().toString();
                    String date = txtDate.getText().toString();
                    String reason = txtreason.getText().toString();
                    Double loanAmount = Double.parseDouble(txtAmountOwed.getText().toString());
                    // check if contact has been selected
                    if (results.isEmpty() == false) {
                        //save each name instance into the database
                        for (int i = 0; i < results.size(); i++) {
                            BigDecimal loanAmountBD =  BigDecimal.valueOf(loanAmount);
                            //create new oweMoney object
                            LoanInMoney loanInMoney = new LoanInMoney(place, date, results.get(i).getDisplayName(), loanAmountBD, reason);
                            //add each peron instance into database
                            dbHelper.addLoanIn(loanInMoney);
                            Log.d("DEBUG", "onDoneOweClicked: Loan Person Added :D");
                        }
                        btnDoneOwe.loadingSuccessful();
                        btnDoneOwe.reset();
                        txtPlace.setText(null);
                        txtAmountOwed.setText(null);
                        txtcontact.setText(null);
                        results.clear();
                        txtDate.setText(null);
                        txtreason.setText(null);
                    }
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Please enter all inputs", Toast.LENGTH_SHORT);
                    toast.show();
                    btnDoneOwe.loadingFailed();
                    btnDoneOwe.isResetAfterFailed();
                }
            }
        });
        //get current date
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(new Date());
        txtDate.setText(date);

        //to choose date
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(LoanInActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });
    }


    //get result from contacts
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String allMembers = "";
        if (requestCode == CONTACT_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                results = MultiContactPicker.obtainResult(data);
                for (int i = 0; i < results.size(); i++) {
                    //log contacts
                    Log.d("MyTag", results.get(i).getDisplayName());
                    //display contact mame in string
                    if (results.size() > 1) {
                        allMembers += results.get(i).getDisplayName() + ", ";
                    } else {
                        allMembers += results.get(i).getDisplayName();
                    }

                }
                //display names in the textbox
                txtcontact.setText(allMembers);
            } else if (resultCode == RESULT_CANCELED) {
                System.out.println("User closed the picker without selecting items.");
            }
        }
    }

}
