package com.app.teampayup.payuplah;

import android.widget.BaseAdapter;

import java.math.BigDecimal;

public class OweMoney {
    int OweMoneyID;
    String place;
    String date;
    String borrowerName;
    BigDecimal borrowAmount;
    String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getOweMoneyID() {
        return OweMoneyID;
    }

    public void setOweMoneyID(int oweMoneyID) {
        OweMoneyID = oweMoneyID;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public BigDecimal getBorrowAmount() {
        return borrowAmount;
    }

    public void setBorrowAmount(BigDecimal borrowAmount) {
        this.borrowAmount = borrowAmount;
    }

    public OweMoney() {
    }

    public OweMoney(String reason, String place, String date, String borrowerName, BigDecimal borrowAmount) {
        this.place = place;
        this.date = date;
        this.borrowerName = borrowerName;
        this.borrowAmount = borrowAmount;
        this.reason = reason;
    }

    public OweMoney(int oweMoneyID, String reason, String place, String date, String borrowerName, BigDecimal borrowAmount) {
        OweMoneyID = oweMoneyID;
        this.place = place;
        this.date = date;
        this.borrowerName = borrowerName;
        this.borrowAmount = borrowAmount;
        this.reason = reason;
    }

    public String ToString(){
        return " '" + reason +" '" + place +" '" + date +" '" + borrowerName +" '" + borrowAmount;
    }
}
