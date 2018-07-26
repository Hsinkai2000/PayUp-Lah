package com.app.teampayup.payuplah;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import java.math.BigDecimal;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<Product> ProductList = new ArrayList<Product>();
    private Context mContext;
    CircleImageView image;
    TextView itemdesc, itemName, txtPrice, txtDate;
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
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        String type = "Expense";
        holder.itemView.setLongClickable(true);
        final DatabaseHelper db = new DatabaseHelper(mContext);
        if(ProductList.get(position).type.contentEquals(type)){ //for expense
            Log.d(TAG, "onBindViewHolder: expense");
            image.setImageResource(android.R.color.transparent);
            itemName.setText(ProductList.get(position).productName);
            itemdesc.setText(ProductList.get(position).description);
            txtPrice.setText("$"+String.valueOf(ProductList.get(position).price));
            image.setImageResource(R.drawable.minus);
            txtDate.setText(ProductList.get(position).date.substring(0, ProductList.get(position).date.length()-9));
        }
        else{
            //all goes through here thou some are expense
            Log.d(TAG, "onBindViewHolder: income");
            image.setImageResource(android.R.color.transparent);
            itemName.setText(ProductList.get(position).productName);
            itemdesc.setText(ProductList.get(position).description);
            txtPrice.setText("$"+String.valueOf(ProductList.get(position).price));
            image.setImageResource(R.drawable.plus);
            txtDate.setText(ProductList.get(position).date.substring(0, ProductList.get(position).date.length()-9));
        }
        parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.GetAllProducts();
                int ID = 0;
                String Name = null;
                String Date = null;
                String desc= null;
                String cat= null;
                String Type= null;
                Double Price = null;
                while (res.moveToNext()){
                    ID = res.getInt(0);
                    Name = res.getString(1);
                    Price = res.getDouble(2);
                    Date = res.getString(3);
                    desc = res.getString(4);
                    cat = res.getString(5);
                    Type = res.getString(6);
                }
                StringBuffer sBuffer = new StringBuffer("ID: " + ID + "\nName: " + Name + "\nPrice: " + String.valueOf(Price)
                        + "\nDate:  " + String.valueOf(Date) + "\nDesc: " +desc+ "\nCat: " + cat+ "\nType: " + Type );
                showMessage("Product Details", sBuffer.toString());


            }
        });

        parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int id = ProductList.get(position).itemID;
                Log.d(TAG, "onLongClick: " + id);
                showDeleteMessage("Confirm", "Are you sure you want to delete?",id, position);
                return true;
            }
        });

    }
    

    @Override
    public int getItemCount() {
        return ProductList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.ImageView);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            itemdesc = itemView.findViewById(R.id.txtdesc);
            itemName = itemView.findViewById(R.id.txtitemName);
            txtDate = itemView.findViewById(R.id.txtDate);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }

    }

    public void showMessage(String title, String message){
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(mContext);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void showDeleteMessage(String title, String message, final int id, final int position){
        final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(mContext);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper db = new DatabaseHelper(mContext);
                db.deleteProductID(id);
                Log.d(TAG, "onClick: ");
                clear(position);
            }
        });
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void clear(int position){
        ProductList.remove(position);
        notifyItemRemoved(position);
        }











}
