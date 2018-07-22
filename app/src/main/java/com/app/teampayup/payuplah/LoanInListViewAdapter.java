package com.app.teampayup.payuplah;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class LoanInListViewAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<LoanInMoney> loan;


    public LoanInListViewAdapter(Context context, List<LoanInMoney> loanIn){
        this.mContext = context;
        this.loan = loanIn;
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
        txtName.setText(loan.get(position).loanerName);
        Log.d("TEXTNAME_DEBUG", loan.get(position).loanerName);
        txtPrice.setText("$" + String.valueOf(loan.get(position).loanAmount));
        txtReason.setText(loan.get(position).reason);


        return itemView;
    }
}
