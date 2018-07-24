package com.app.teampayup.payuplah;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class PurchasedActivity extends AppCompatActivity {
    RecyclerView recylcerExpense;
    final String TAG = "PurchasedActivity";
    ArrayList<Product> ProductList = new ArrayList<Product>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchased);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recylcerExpense = findViewById(R.id.recyclerExpense);

        DatabaseHelper db = new DatabaseHelper(this);
        Cursor res = db.GetAllProducts();
        int itemID = 0;
        String itemName = null;
        String itemDate = null;
        String itemdesc= null;
        String itemcat= null;
        String itemType= null;
        double itemPrice = 0;
        while(res.moveToNext()){
            itemID = res.getInt(0);
            itemName = res.getString(1);
            itemPrice = res.getDouble(2);
            itemDate = res.getString(3);
            itemdesc = res.getString(4);
            itemcat = res.getString(5);
            itemType = res.getString(6);
            Product product = new Product(itemID, itemPrice, itemDate, itemdesc, itemcat, itemName, itemType);
            ProductList.add(product);
        }

        initRecyclerView();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAddPurchasedItem);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToAddPurchasedItem = new Intent(getApplicationContext(), AddPurchasedItem.class);
                startActivity(goToAddPurchasedItem);

            }
        });


    }
    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: showing recyclerview");
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, ProductList);
        recylcerExpense.setAdapter(adapter);
        recylcerExpense.setLayoutManager(new LinearLayoutManager(this));
        recylcerExpense.setHasFixedSize(true);
    }

}
