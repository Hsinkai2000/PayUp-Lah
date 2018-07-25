package com.app.teampayup.payuplah;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import android.text.format.DateFormat;
import android.util.Log;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
import java.util.Queue;
import java.util.regex.Pattern;

import static android.support.constraint.Constraints.TAG;
import java.util.Calendar;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "PayUpLahDB.db";

    //Profile Table
    public static final String PROFILE_TABLE_NAME = "Profile_Table";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_BUDGET = "budget";

    //Item Table
    public static final String ITEM_TABLE_NAME = "Item_Table";
    public static final String COL_ITEMNAME = "productName";
    public static final String COL_ITEMID = "itemID";
    public static final String COL_PROFILEID = "profileID";
    public static final String COL_PRICE = "price";
    public static final String COL_DATE = "date";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_CATEGORY = "category";
    public static final String COL_TYPE = "type";

    //Owe Table
    public static final String OWE_TABLE_NAME = "Owe_Table";
    public static final String LOANIN_TABLE_NAME = "LoanIN_Table";
    public static final String COL_LOANINID = "LoanInMoneyID";
    public static final String COL_REASON = "Reason";
    public static final String COL_OWEMONEYID = "OweMoneyID";
    public static final String COL_PLACE = "place";
    public static final String COL_BORROWERNAME = "borrowerName";
    public static final String COL_LOANAMOUNT = "loanAmount";
    public static final String COL_LOANERNAME = "loanerName";
    public static final String COL_BORROWAMOUNT = "borrowAmount";


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Profile Table
        /*String CREATE_PROFILE_TABLE = "CREATE TABLE " + PROFILE_TABLE_NAME + "(" + COL_ID + " INTEGER PRIMARY KEY,"+ COL_NAME + " TEXT," + COL_BUDGET + " INTEGER" + ")";
        db.execSQL(CREATE_PROFILE_TABLE);*/

        //Create Item Table
        String CREATE_ITEM_TABLE = "CREATE TABLE " + ITEM_TABLE_NAME + "(" + COL_ITEMID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COL_ITEMNAME + " TEXT, " + COL_PRICE + " REAL, " + COL_DATE + " TEXT, " + COL_DESCRIPTION + " TEXT, " +
                COL_CATEGORY + " TEXT, " + COL_TYPE + " TEXT)";
        db.execSQL(CREATE_ITEM_TABLE);

        //Create Owe Table
        String CREATE_OWE_TABLE = "CREATE TABLE " + OWE_TABLE_NAME + "(" + COL_OWEMONEYID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + COL_REASON + " TEXT,"
                + COL_PLACE + " TEXT," + COL_DATE + " TEXT," + COL_BORROWERNAME + " TEXT," + COL_BORROWAMOUNT + " REAL)";
        db.execSQL(CREATE_OWE_TABLE);

        //Create LoanIn Table
        String CREATE_LOANIN_TABLE = "CREATE TABLE " + LOANIN_TABLE_NAME + "(" + COL_LOANINID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + COL_REASON + " TEXT,"
                + COL_PLACE + " TEXT," + COL_DATE + " TEXT," + COL_LOANERNAME + " TEXT," + COL_LOANAMOUNT + " REAL)";
        db.execSQL(CREATE_LOANIN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //DROP Profile Table
        /*db.execSQL("DROP TABLE IF EXISTS " + PROFILE_TABLE_NAME);
        onCreate(db);*/

        //DROP Item Table
        db.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE_NAME);
        onCreate(db);

        //DROP OWE TABLE
        db.execSQL("DROP TABLE IF EXISTS " + OWE_TABLE_NAME);
        onCreate(db);

        //DROP LOAN TABLE
        db.execSQL("DROP TABLE IF EXISTS " + LOANIN_TABLE_NAME);
        onCreate(db);
    }

    //PROFILE TABLE
    /*public void addProfile(Profile profile){
        ContentValues values = new ContentValues();
        values.put(COL_NAME, profile.getName());
        values.put(COL_BUDGET, profile.getBudget());
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(PROFILE_TABLE_NAME, null, values);
        db.close();
    }

    public Profile findProfile(String name){
        String query = "SELECT * FROM " + PROFILE_TABLE_NAME + " WHERE " + COL_NAME + " = \"" + name + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Profile profile = new Profile();

        if (cursor.moveToFirst()){
            cursor.moveToFirst();
            profile.setId(Integer.parseInt(cursor.getString(0)));
            profile.setName(cursor.getString(1));
            profile.setBudget(Integer.parseInt(cursor.getString(2)));
            cursor.close();
        }else{
            profile = null;
        }
        db.close();
        return  profile;
    }//find profile

    public boolean deleteProfile(String name){
        boolean result = false;
        String query = "SELECT * FROM " + PROFILE_TABLE_NAME + " WHERE " + COL_NAME + " = \"" + name + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        Profile profile = new Profile();
        if (cursor.moveToFirst()){
            profile.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(PROFILE_TABLE_NAME, COL_ID + " =?",
                    new String[]{ String.valueOf(profile.getId())});
            cursor.close();
            result=true;
        }
        db.close();
        return result;
    }//delete profile
*/
    //Item Table
    public void addItem(Product product){
        ContentValues values = new ContentValues();
        values.put(COL_ITEMNAME,product.getProductName());
        values.put(COL_PRICE, product.getPrice().doubleValue());
        values.put(COL_DATE, product.getDate());
        values.put(COL_DESCRIPTION, product.getDescription());
        values.put(COL_CATEGORY, product.getCategory());
        values.put(COL_TYPE, product.getType());
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(ITEM_TABLE_NAME, null, values);
        db.close();
    }

    public Product findItem(String name){
        String query = "SELECT * FROM " + ITEM_TABLE_NAME + " WHERE " + COL_ITEMNAME + " = \"" + name + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Product product = new Product();

        if (cursor.moveToFirst()){
            cursor.moveToFirst();
            product.setItemID(Integer.parseInt(cursor.getString(0)));
            product.setProductName(cursor.getString(1));
            product.setPrice(BigDecimal.valueOf(cursor.getDouble(2)));
            product.setDate(cursor.getString(3));
            product.setDescription(cursor.getString(4));
            product.setCategory(cursor.getString(5));
            product.setType(cursor.getString(6));
            cursor.close();
        }else{
            product = null;
        }
        db.close();
        return  product;
    }//find profile

    public boolean deleteItem(String name){
        boolean result = false;
        String query = "SELECT * FROM " + ITEM_TABLE_NAME + " WHERE " + COL_ITEMNAME + " = \"" + name + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        Product product = new Product();
        if (cursor.moveToFirst()){
            product.setItemID(Integer.parseInt(cursor.getString(0)));
            db.delete(ITEM_TABLE_NAME, COL_ITEMID + " =?",
                    new String[]{ String.valueOf(product.getItemID())});
            cursor.close();
            result=true;
        }
        db.close();
        return result;
    }//delete profile


    //Owe Table
    public void addOwe(OweMoney owemoney){
        ContentValues values = new ContentValues();
        values.put(COL_REASON, owemoney.getReason());
        values.put(COL_PLACE, owemoney.getPlace());
        values.put(COL_DATE, owemoney.getDate());
        values.put(COL_BORROWERNAME, owemoney.getBorrowerName());
        values.put(COL_BORROWAMOUNT, owemoney.getBorrowAmount().doubleValue());
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(OWE_TABLE_NAME, null, values);
        db.close();
    }

    public OweMoney findOweByName(String name){
        String query = "SELECT * FROM " + OWE_TABLE_NAME + " WHERE " + COL_BORROWERNAME + " = \"" + name + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        OweMoney oweMoney = new OweMoney();

        if (cursor.moveToFirst()){
            cursor.moveToFirst();
            oweMoney.setOweMoneyID(Integer.parseInt(cursor.getString(0)));
            oweMoney.setPlace(cursor.getString(1));
            oweMoney.setDate(cursor.getString(2));
            oweMoney.setBorrowerName(cursor.getString(3));
            oweMoney.setBorrowAmount(BigDecimal.valueOf(Double.parseDouble(cursor.getString(4))));
            cursor.close();
        }else{
            oweMoney = null;
        }
        db.close();
        return  oweMoney;
    }//find Owe

    public Cursor getAllOweData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ OWE_TABLE_NAME;
        Cursor res = db.rawQuery(query, null);
        return res;
    }

    public boolean deleteOweByID(int id){
        boolean result = false;
        String query = "SELECT * FROM " + OWE_TABLE_NAME + " WHERE " + COL_OWEMONEYID + " = \"" + id + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        OweMoney oweMoney = new OweMoney();
        if (cursor.moveToFirst()){
            oweMoney.setOweMoneyID(Integer.parseInt(cursor.getString(0)));
            db.delete(OWE_TABLE_NAME, COL_OWEMONEYID + " =?",
                    new String[]{ String.valueOf(oweMoney.getOweMoneyID())});
            cursor.close();
            result=true;
        }
        db.close();
        return result;
    }//delete owe

    /*public Cursor RetrieveExpense(){
        Intent secondIntent = new Intent();
        String date = secondIntent.getStringExtra("DateSelected");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT " + COL_DESCRIPTION+ " FROM " + ITEM_TABLE_NAME + " WHERE " + COL_TYPE + " = \"" + "Expense" + "\" AND " + COL_DATE + " = \"" + date + "\"", null);
        return res;
    }*/
    //" = \"Expense\"

    public Cursor GetAllProducts(){
        Log.d(TAG, "Retrieve all Products: date SQL");
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + ITEM_TABLE_NAME;
        Cursor res = db.rawQuery( query, null);
        return res;
    }

    public Cursor RetrieveProducts(String newDateTime, String endDateTime){

        Log.d("DATETIMECHECK", "in database start dateTime : "+ newDateTime);
        Log.d("DATETIMECHECK", "in database start dateTime : "+ endDateTime);
        Log.d(TAG, "RetrieveProducts: date SQL");
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + ITEM_TABLE_NAME + " WHERE " + COL_DATE + " BETWEEN '" + newDateTime + "' AND '" + endDateTime + "'";
        Log.d("QUERYTEST", "RetrieveProducts: date SQL" + query);
        Cursor res = db.rawQuery( query, null);
        return res;
    }

    public Cursor GetProducts(){
        SQLiteDatabase db = this.getWritableDatabase();
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        String strMonth = "";
        String strMonth2 = "";
        if (month+1 < 10){
            strMonth = "0" + (month+1);
            strMonth2 = "0" + (month+2);
        }
        else{
            strMonth= String.valueOf(month+1);
            strMonth2 = String.valueOf(month+2);
        }
        int year = cal.get(Calendar.YEAR);
        String strYear = String.valueOf(year);

        String query = "SELECT * FROM "+ ITEM_TABLE_NAME + " WHERE " + COL_DATE + " BETWEEN '" + strYear + "-" + strMonth + "-00 00:00:00' AND '" + strYear + "-" + strMonth2 + "-00 00:00:00'";
        Cursor res = db.rawQuery(query, null);
        Log.d("QUERYTEST", query);
        return res;
    }

    //LoanIN Table
    public void addLoanIn(LoanInMoney loanInMoney){
        ContentValues values = new ContentValues();
        values.put(COL_REASON, loanInMoney.getReason());
        values.put(COL_PLACE, loanInMoney.getPlace());
        values.put(COL_DATE, loanInMoney.getDate());
        values.put(COL_LOANERNAME, loanInMoney.getLoanerName());
        values.put(COL_LOANAMOUNT, loanInMoney.getLoanAmount().doubleValue());
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(LOANIN_TABLE_NAME, null, values);
        db.close();
    }

    public LoanInMoney findLoanInByName(String name){
        String query = "SELECT * FROM " + LOANIN_TABLE_NAME + " WHERE " + COL_LOANERNAME + " = \"" + name + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        LoanInMoney loanInMoney = new LoanInMoney();

        if (cursor.moveToFirst()){
            cursor.moveToFirst();
            loanInMoney.setLoanInMoneyID(Integer.parseInt(cursor.getString(0)));
            loanInMoney.setReason(cursor.getString(1));
            loanInMoney.setPlace(cursor.getString(2));
            loanInMoney.setDate(cursor.getString(3));
            loanInMoney.setLoanerName(cursor.getString(4));
            loanInMoney.setLoanAmount(BigDecimal.valueOf(Double.parseDouble(cursor.getString(5))));
            cursor.close();
        }else{
            loanInMoney = null;
        }
        db.close();
        return  loanInMoney;
    }//find Owe

    public Cursor getAllLoanInData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ LOANIN_TABLE_NAME;
        Cursor res = db.rawQuery(query, null);
        return res;
    }//get all loan IN data

    public boolean deleteLoanInByID(int id){
        boolean result = false;
        String query = "SELECT * FROM " + LOANIN_TABLE_NAME + " WHERE " + COL_LOANINID + " = \"" + id + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        OweMoney oweMoney = new OweMoney();
        if (cursor.moveToFirst()){
            oweMoney.setOweMoneyID(Integer.parseInt(cursor.getString(0)));
            db.delete(LOANIN_TABLE_NAME, COL_LOANINID + " =?",
                    new String[]{ String.valueOf(oweMoney.getOweMoneyID())});
            cursor.close();
            result=true;
        }
        db.close();
        return result;
    }//delete owe

    public Cursor getProductPie(){
        Log.d(TAG, "getProductPie:  Getting pie products");
        String query = "SELECT " + COL_ITEMNAME + ", " + COL_PRICE + " FROM " + ITEM_TABLE_NAME + " WHERE " + COL_TYPE + " = \"" + "Income" + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
}
