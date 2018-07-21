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
    private ArrayList<String> Income = new ArrayList<String>();
    private ArrayList<String> Expense = new ArrayList<String>();
    private Context mContext;
    private DatabaseHelper dbHelp = new DatabaseHelper(mContext);



    public RecyclerViewAdapter(Context mContext, ArrayList<String> Income, ArrayList<String> Expense) {
        this.mContext = mContext;
        this.Income = Income;
        this.Expense = Expense;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_day, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called."); //for debugging
        Cursor res = dbHelp.RetrieveExpense();
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append(res.getString(0));
            holder.imageName.setText(res.getString(0));
            holder.image.setImageResource(R.drawable.plus);
        }
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
        Cursor res = dbHelp.RetrieveExpense();
        if(res.getCount() == 0){
            return 0;
        }
        else{
            return res.getCount();
        }
        //return Expense.size() + Income.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView imageName;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.txtMoney);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }

    }





}
