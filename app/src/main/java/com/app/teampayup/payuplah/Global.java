package com.app.teampayup.payuplah;

import android.app.Application;

public class Global extends Application {
    String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
