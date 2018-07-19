package com.app.teampayup.payuplah;

import android.content.Context;
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
    private ArrayList<String> Income;
    private ArrayList<String> Expense;
    private Context mContext;
    private DatabaseHelper dbHelp;
    String SelectedDate;


    public RecyclerViewAdapter(Context mContext, ArrayList<String> Income, ArrayList<String> Expense) {
        this.mContext = mContext;
        this.Income = Income;
        this.Expense = Expense;

    }

    /*public void insertDataList() {
        Product objProduct = new Product();
        if(objProduct.getDate() == SelectedDate){

        }
    }*/

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
        /*Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.image);*/
        Income = dbHelp.RetrieveIncome();
        Expense = dbHelp.RetrieveExpense();
        for(int i=0; i< Income.size(); i++){
            holder.image.setImageResource(R.drawable.plus);
            holder.imageName.setText(Income.get(position));
        }
        for(int i=0; i<Expense.size(); i++){
            holder.image.setImageResource(R.drawable.minus);
            holder.imageName.setText(Expense.get(position));
        }



    }

    @Override
    public int getItemCount() {
        return Expense.size() + Income.size();
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
