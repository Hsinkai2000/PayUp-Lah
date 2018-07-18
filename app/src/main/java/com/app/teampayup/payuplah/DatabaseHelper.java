package com.app.teampayup.payuplah;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Profile Table
        /*String CREATE_PROFILE_TABLE = "CREATE TABLE " + PROFILE_TABLE_NAME + "(" + COL_ID + " INTEGER PRIMARY KEY,"+ COL_NAME + " TEXT," + COL_BUDGET + " INTEGER" + ")";
        db.execSQL(CREATE_PROFILE_TABLE);*/

        //Create Item Table
        String CREATE_ITEM_TABLE = "CREATE TABLE " + ITEM_TABLE_NAME + "(" + COL_ITEMID + " INTEGER PRIMARY KEY," + COL_ITEMNAME + " TEXT," + COL_PRICE + " REAL," + COL_DATE + " TEXT," + COL_DESCRIPTION + " TEXT," +
                COL_CATEGORY + " TEXT," + COL_TYPE + " TEXT)";
        db.execSQL(CREATE_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //DROP Profile Table
        /*db.execSQL("DROP TABLE IF EXISTS " + PROFILE_TABLE_NAME);
        onCreate(db);*/
        //DROP Item Table
        db.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE_NAME);
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
        values.put(COL_PRICE, product.getPrice());
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
            product.setPrice(Integer.parseInt(cursor.getString(2)));
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

    public ArrayList<String> RetrieveExpense(){
        ArrayList<String>ExpenseDesc = new ArrayList<String>();
        String query = "SELECT " + COL_DESCRIPTION+ " FROM " + ITEM_TABLE_NAME + " WHERE " + COL_TYPE + " = \"" + "Expense" + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do{
                Product product = new Product();
                product.setDescription(String.valueOf(cursor.getColumnIndex(product.getDescription())));
            }while (cursor.moveToNext());
        }
        db.close();
        return ExpenseDesc;
    } //retrieve expense description

    public ArrayList<String> RetrieveIncome(){
        Intent secondIntent = new Intent();
        String date = secondIntent.getStringExtra("DateSelected");
        ArrayList<String>IncomeDesc = new ArrayList<String>();
        String query = "SELECT " + COL_DESCRIPTION+ " FROM " + ITEM_TABLE_NAME + " WHERE " + COL_TYPE + " = \"" + "Income" + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do{
                Product product = new Product();
                product.setDescription(String.valueOf(cursor.getColumnIndex(product.getDescription())));
            }while (cursor.moveToNext());
        }
        db.close();
        return IncomeDesc;
    } //retrieve expense description


}
