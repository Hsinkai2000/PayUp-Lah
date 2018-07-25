package com.app.teampayup.payuplah;

import java.math.BigDecimal;

public class LoanInMoney {
    int LoanInMoneyID;
    String place;
    String date;
    String loanerName;
    BigDecimal loanAmount;
    String reason;

    public int getLoanInMoneyID() {
        return LoanInMoneyID;
    }

    public void setLoanInMoneyID(int loanInMoneyID) {
        LoanInMoneyID = loanInMoneyID;
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

    public String getLoanerName() {
        return loanerName;
    }

    public void setLoanerName(String loanerName) {
        this.loanerName = loanerName;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LoanInMoney() {
    }

    public LoanInMoney(String place, String date, String loanerName, BigDecimal loanAmount, String reason) {
        this.place = place;
        this.date = date;
        this.loanerName = loanerName;
        this.loanAmount = loanAmount;
        this.reason = reason;
    }

    public LoanInMoney(int loanInMoneyID, String place, String date, String loanerName, BigDecimal loanAmount, String reason) {
        LoanInMoneyID = loanInMoneyID;
        this.place = place;
        this.date = date;
        this.loanerName = loanerName;
        this.loanAmount = loanAmount;
        this.reason = reason;
    }
}
