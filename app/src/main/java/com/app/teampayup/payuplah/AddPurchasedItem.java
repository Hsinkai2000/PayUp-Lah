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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class AddPurchasedItem extends AppCompatActivity {
    Button btnDatePicker;
    EditText txtDate, txtName, txtPrice, txtDescription;
    private int mYear, mMonth, mDay;
    Spinner spinnerCat;
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


                DatePickerDialog datePickerDialog = new DatePickerDialog(AddPurchasedItem.this,
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

    //when save is clicked
    public void onSaveClick(View view) {

        //get text of all fields
        String itemName = txtName.getText().toString();
        Float itemPrice = Float.parseFloat(txtPrice.getText().toString());
        String datePurchased = txtDate.getText().toString();
        String itemDesc = txtDescription.getText().toString();
        String itemCat = spinnerCat.getSelectedItem().toString();

        //add all data into a product object
        Product product = new Product();
        product.setProductName(itemName);
        product.setPrice(itemPrice);
        product.setDate(datePurchased);
        product.setDescription(itemDesc);
        product.setCategory(itemCat);

        //insert new data into sqlite Item table
        DatabaseHelper dbhelper = new DatabaseHelper(AddPurchasedItem.this);
        dbhelper.addItem(product);

        //check if new profile is created
        Product product2 = dbhelper.findItem(itemName);
        if (product2 != null){
            Log.d("DEBUG", "newItem: created");
            Toast toast = Toast.makeText(getApplicationContext(), "Product is created", Toast.LENGTH_LONG);
            toast.show();
        }

    }
}

