package com.app.teampayup.payuplah;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.dx.dxloadingbutton.lib.LoadingButton;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class AddPurchasedItem extends AppCompatActivity {
    Button btnDatePicker;
    LoadingButton btnSave;
    EditText txtDate, txtName, txtPrice, txtDescription;
    private int mYear, mMonth, mDay;
    Spinner spinnerCat, spinnerType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_purchased_item);

        //initialise items
        btnDatePicker=(Button)findViewById(R.id.btn_date);
        txtDate=(EditText)findViewById(R.id.in_date);
        spinnerCat = (Spinner)findViewById(R.id.spinnerCat);
        txtName=(EditText)findViewById(R.id.txtName);
        txtPrice=(EditText)findViewById(R.id.txtPrice);
        txtDescription=(EditText)findViewById(R.id.txtDescription);
        spinnerType= (Spinner)findViewById(R.id.spinnerType);
        btnSave = (LoadingButton)findViewById(R.id.btnSave);

        //get current date
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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


                DatePickerDialog datePickerDialog = new DatePickerDialog(AddPurchasedItem.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                if (monthOfYear+1 < 10 && dayOfMonth <10){
                                    txtDate.setText(year +  "-0" + (monthOfYear + 1) + "-0"+dayOfMonth);
                                }
                                else if (monthOfYear+1 < 10){
                                    txtDate.setText(year+ "-0" + (monthOfYear + 1) + "-" + dayOfMonth);
                                }
                                else if (dayOfMonth < 10){
                                    txtDate.setText(year + "-" + (monthOfYear + 1) + "-0"+dayOfMonth);
                                }
                                else {
                                    txtDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth );
                                }
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSave.startLoading(); //start loading
                //get text of all fields
                if (!txtName.getText().toString().isEmpty() && !txtPrice.getText().toString().isEmpty() && !txtDescription.getText().toString().isEmpty() && spinnerCat.getSelectedItem().toString() != spinnerCat.getItemAtPosition(0).toString()  && spinnerType.getSelectedItem().toString() != spinnerType.getItemAtPosition(0).toString()) {

                    String itemName = txtName.getText().toString();
                    BigDecimal itemPrice = BigDecimal.valueOf(Double.parseDouble(txtPrice.getText().toString()));
                    String datePurchased = txtDate.getText().toString() + " 00:00:00";
                    String itemDesc = txtDescription.getText().toString();
                    String itemCat = spinnerCat.getSelectedItem().toString();
                    String itemType = spinnerType.getSelectedItem().toString();


                    //add all data into a product object
                    Product product = new Product(itemPrice, datePurchased, itemDesc, itemCat, itemName, itemType);

                    //insert new data into sqlite Item table
                    DatabaseHelper dbhelper = new DatabaseHelper(AddPurchasedItem.this);
                    dbhelper.addItem(product);

                    Log.d("DEBUG", "newItem: created");

                    btnSave.loadingSuccessful();
                    btnSave.reset();
                    txtName.setText(null);
                    txtPrice.setText(null);
                    txtDescription.setText(null);
                    spinnerCat.setSelection(0);
                    spinnerType.setSelection(0);

                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Please enter all inputs", Toast.LENGTH_SHORT);
                    toast.show();
                    btnSave.loadingFailed();
                    btnSave.isResetAfterFailed();
                }
            }
        });
    }

}

