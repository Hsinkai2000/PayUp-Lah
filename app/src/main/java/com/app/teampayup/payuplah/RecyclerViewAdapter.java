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
    private ArrayList<Product> Income = new ArrayList<Product>();
    private ArrayList<Product> Expense = new ArrayList<Product>();
    private Context mContext;

    CircleImageView image;
    TextView itemdesc, itemName, txtPrice;
    RelativeLayout parentLayout;

    public RecyclerViewAdapter(Context mContext, ArrayList<Product> Income, ArrayList<Product> Expense) {
        this.mContext = mContext;
        this.Income = Income;
        this.Expense = Expense;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_day, parent, false);
        ViewHolder holder = new ViewHolder(view);
        image = view.findViewById(R.id.image);
        txtPrice = view.findViewById(R.id.txtPrice);
        itemdesc = view.findViewById(R.id.txtdesc);
        itemName = view.findViewById(R.id.txtitemName);
        parentLayout = view.findViewById(R.id.parent_layout);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        itemName.setText(Expense.get(position).productName);
        itemdesc.setText(Expense.get(position).description);
        txtPrice.setText(String.valueOf(Expense.get(position).price));

        //Income = dbHelp.RetrieveIncome();
        //Expense = dbHelp.RetrieveExpense();
        /*if(Income.size()!= 0 && Expense.size() != 0){
            for(int i=0; i< Income.size(); i++){
                holder.image.setImageResource(R.drawable.plus);
                holder.imageName.setText(Income.get(position));
            }
            for(int i=0; i<Expense.size(); i++){
                holder.image.setImageResource(R.drawable.minus);
                holder.imageName.setText(Expense.get(position));
            }
        }
        else if(Income.size() >0 && Expense.size()== 0){
            for(int i=0; i< Income.size(); i++){
                holder.image.setImageResource(R.drawable.plus);
                holder.imageName.setText(Income.get(position));
            }
        }
        else if(Income.size() == 0 && Expense.size()> 0){
            for(int i=0; i<Expense.size(); i++){
                holder.image.setImageResource(R.drawable.minus);
                holder.imageName.setText(Expense.get(position));
            }
        }*/






    }

    @Override
    public int getItemCount() {
        return Expense.size();
        //return Expense.size() + Income.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);

        }

    }





}
