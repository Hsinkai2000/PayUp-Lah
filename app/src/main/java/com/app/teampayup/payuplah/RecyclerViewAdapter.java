package com.app.teampayup.payuplah;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<Product> ProductList = new ArrayList<Product>();
    private Context mContext;

    CircleImageView image;
    TextView itemdesc, itemName, txtPrice;
    RelativeLayout parentLayout;

    public RecyclerViewAdapter(Context mContext, ArrayList<Product> ProductList) {
        this.mContext = mContext;
        this.ProductList = ProductList;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_day, parent, false);
        ViewHolder holder = new ViewHolder(view);
        image = view.findViewById(R.id.ImageView);
        txtPrice = view.findViewById(R.id.txtPrice);
        itemdesc = view.findViewById(R.id.txtdesc);
        itemName = view.findViewById(R.id.txtitemName);
        parentLayout = view.findViewById(R.id.parent_layout);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(ProductList.get(position).type == "Expense"){ //for expense
            Log.d(TAG, "onBindViewHolder: expense");
            image.setImageResource(android.R.color.transparent);
            itemName.setText(ProductList.get(position).productName);
            itemdesc.setText(ProductList.get(position).description);
            txtPrice.setText(String.valueOf(ProductList.get(position).price));
            image.setImageResource(R.drawable.minus);
        }
        else{
            //all goes through here thou some are expense
            Log.d(TAG, "onBindViewHolder: income");
            image.setImageResource(android.R.color.transparent);
            itemName.setText(ProductList.get(position).productName);
            itemdesc.setText(ProductList.get(position).description);
            txtPrice.setText(String.valueOf(ProductList.get(position).price));
            image.setImageResource(R.drawable.plus);
        }

    }

    @Override
    public int getItemCount() {
        return ProductList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);

        }

    }





}
