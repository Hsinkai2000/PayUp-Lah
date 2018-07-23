package com.app.teampayup.payuplah;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LoanOutListViewAdapter extends BaseAdapter{
    TextView txtName, txtPrice, txtReason;
    Context mContext;
    List<OweMoney> loan = new ArrayList<OweMoney>();

    public LoanOutListViewAdapter(Context context, List<OweMoney>loanout){
        this.mContext = context;
        this.loan = loanout;
    }


    @Override
    public int getCount() {
        return loan.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        itemView = LayoutInflater.from(mContext).inflate(R.layout.list_loanout, parent, false);
        TextView txtName, txtPrice, txtReason;

        //connect textViews
        txtName = itemView.findViewById(R.id.txtName);
        txtPrice = itemView.findViewById(R.id.txtPrice);
        txtReason = itemView.findViewById(R.id.txtReasons);

        //set Textviews
        txtName.setText(loan.get(position).borrowerName);
        Log.d("TEXTNAME_DEBUG", loan.get(position).borrowerName);
        txtPrice.setText("$" + String.valueOf(loan.get(position).borrowAmount));
        txtReason.setText(loan.get(position).reason);


        return itemView;
    }
}
