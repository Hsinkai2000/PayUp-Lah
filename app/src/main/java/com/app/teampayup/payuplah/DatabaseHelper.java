package com.app.teampayup.payuplah;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "PayUpLahDB.db";

    //Profile Table
    public static final String PROFILE_TABLE_NAME = "Profile_Table";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_BUDGET = "budget";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Profile Table
        String CREATE_PROFILE_TABLE = "CREATE TABLE " + PROFILE_TABLE_NAME + "(" + COL_ID + " INTEGER PRIMARY KEY,"+ COL_NAME + " TEXT," + COL_BUDGET + " INTEGER" + ")";
        db.execSQL(CREATE_PROFILE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //DROP Profile Table
        db.execSQL("DROP TABLE IF EXISTS " + PROFILE_TABLE_NAME);
        onCreate(db);
    }

    //PROFILE TABLE
    public void addProfile(Profile profile){
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
}
