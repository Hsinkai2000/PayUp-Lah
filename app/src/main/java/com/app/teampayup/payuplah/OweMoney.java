package com.app.teampayup.payuplah;

public class OweMoney {
    int OweMoneyID;
    String place;
    String date;
    String borrowerName;
    double borrowAmount;

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

    public double getBorrowAmount() {
        return borrowAmount;
    }

    public void setBorrowAmount(double borrowAmount) {
        this.borrowAmount = borrowAmount;
    }

    public OweMoney() {
    }

    public OweMoney(String place, String date, String borrowerName, double borrowAmount) {
        this.place = place;
        this.date = date;
        this.borrowerName = borrowerName;
        this.borrowAmount = borrowAmount;
    }

    public OweMoney(int oweMoneyID, String place, String date, String borrowerName, double borrowAmount) {
        OweMoneyID = oweMoneyID;
        this.place = place;
        this.date = date;
        this.borrowerName = borrowerName;
        this.borrowAmount = borrowAmount;
    }
}
