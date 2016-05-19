package com.example.kkeli.mapharma;

/**
 * Created by 150482 on 2016/05/16.
 */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PharmaHandler extends SQLiteOpenHelper {

    // All variables about DB
    // Database name
    private static final String DATABASE_NAME = "pharma3";

    // Database version
    private static final int DATABASE_VERSION = 1;

    // Pharmacie table name
    private static final String TABLE_PHARMA = "pharmaTable5";

    //Town table name
    private static  final String TABLE_TOWN = "towntable";

    // Table Column names (add all column names here)
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_PHOTOGRAPH = "photograph";
    private static final String COLUMN_TOWN = "town";
    private static final String COLUMN_REGION ="region";

    //Town Tables column names
    private static final String TOWN_COLUNM_ID = "town_id";
    private static final String TOWN_COLUMN_NAME = "twonname";

    //Common column
    private static final String  KEY_CREATED_AT = "created_at";

    private String[] columns= {COLUMN_ID, COLUMN_NAME, COLUMN_PHONE, COLUMN_EMAIL, COLUMN_ADDRESS, COLUMN_PHOTOGRAPH,COLUMN_REGION,COLUMN_TOWN};

    private String[] townColums ={TOWN_COLUNM_ID,TOWN_COLUMN_NAME};
    // Create database
    public PharmaHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Table create statement
    //Pharma table create statement

    //Town create statement
    private static final String CREATE_TABLE_TOWN =
            "CREATE TABLE "+
                    TABLE_TOWN +
                    "("
                    + TOWN_COLUNM_ID + " INTEGER PRIMARY KEY,"
                    + TOWN_COLUMN_NAME + " TEXT"
                    + KEY_CREATED_AT + " DATETIME"+
                    ")";

    //Pharma create statement
    private static final String CREATE_TABLE = "CREATE TABLE " +
            TABLE_PHARMA +
            "("
            + COLUMN_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_PHONE + " TEXT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_ADDRESS + " TEXT,"
            + COLUMN_PHOTOGRAPH + " TEXT,"
            + COLUMN_REGION + " TEXT,"
            + COLUMN_TOWN + " TEXT"
            + ")";

    // Create table
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_TOWN);

        Log.d("Table created", "Test");
    }

    // Drop table if older version exist
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHARMA);
        onCreate(db);
    }

	/*
	 * Handling Pharma table using sql queries.
	 * */


    // Add Pharma
    public boolean addPharmaDetails(Pharma pharma){
        // Get db writable
        SQLiteDatabase db = this.getWritableDatabase();
        // Get the values to insert
        ContentValues vals = new ContentValues();
        vals.put(COLUMN_NAME, pharma.getName());
        vals.put(COLUMN_PHONE, pharma.getPhoneNumber());
        vals.put(COLUMN_EMAIL, pharma.getEmail());
        vals.put(COLUMN_ADDRESS, pharma.getPostalAddress());
        vals.put(COLUMN_PHOTOGRAPH, pharma.getPhotograph());
        vals.put(COLUMN_REGION,pharma.getRegion());
        vals.put(COLUMN_TOWN, pharma.getTown());

        // Insert values into table
        long i = db.insert(TABLE_PHARMA, null, vals);
        // Close database
        db.close();

        if(i != 0){
            return true;
        }else{
            return false;
        }
    }

    // Reading all pharma
    public List<Pharma> readAllPharma(){
        // Get db writable
        SQLiteDatabase db = this.getWritableDatabase();

        // Define pharmas list
        List<Pharma> pharmas = new ArrayList<Pharma>();
        Cursor cursor = db.query(TABLE_PHARMA, columns, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Pharma pharma = new Pharma();
            pharma.setID(Integer.parseInt(cursor.getString(0)));
            pharma.setName(cursor.getString(1));
            pharma.setPhoneNumber(cursor.getString(2));
            pharma.setEmail(cursor.getString(3));
            pharma.setPostalAddress(cursor.getString(4));
            pharma.setPhotograph(cursor.getString(5));
            pharma.setRegion(cursor.getString(6));
            pharma.setTown(cursor.getString(7));
            pharmas.add(pharma);
            cursor.moveToNext();
        }

        // Make sure to close the cursor
        cursor.close();
        return pharmas;
    }

    // Update pharma pharma
    public boolean editPharma(Pharma pharma){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues vals = new ContentValues();
        vals.put(COLUMN_NAME, pharma.getName());
        vals.put(COLUMN_PHONE, pharma.getPhoneNumber());

        // updating row
        int i = db.update(TABLE_PHARMA, vals, COLUMN_ID + " = ?",  new String[] { String.valueOf(pharma.getID()) });

        db.close();

        if(i != 0){
            return true;
        }else{
            return false;
        }

    }

    // Deleting pharma
    public boolean deletePharma(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        int i = db.delete(TABLE_PHARMA, COLUMN_ID + " = ?",  new String[] { String.valueOf(id) });

        db.close();

        if(i != 0){
            return true;
        }else{
            return false;
        }
    }

    //------------------------------- Town related -------------------------------
    //Town related CRUD
    //Insert a town
    /**
     * Creating tag
     */
    public long createTag(Town town) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TOWN_COLUNM_ID, town.getName());
        values.put(KEY_CREATED_AT, getDateTime());

        // insert row
        long town_id = db.insert(TABLE_TOWN, null, values);

        return town_id;
    }

    // Reading all town
    public List<Town> readAllTown(){
        // Get db writable
        SQLiteDatabase db = this.getWritableDatabase();
        // Define towns list
        List<Town> towns = new ArrayList<Town>();
        Cursor cursor = db.query(TABLE_TOWN, townColums, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Town town = new Town();
            town.setID(Integer.parseInt(cursor.getString(0)));
            town.setName(cursor.getString(1));
            towns.add(town);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return towns;
    }

    /**
     * getting town count
     */
    public int getTownDoCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TOWN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        // return count
        return count;
    }

    /**
     * update town
     */
    public int updateTowo(Town town) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TOWN_COLUNM_ID, town.getName());

        // updating row
        return db.update(TABLE_TOWN, values, TOWN_COLUNM_ID + " = ?",
                new String[] { String.valueOf(town.getID()) });
    }
    /**
     * delete town
     */
    public void deleteToDo(long town_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TOWN, TOWN_COLUNM_ID + " = ?",
                new String[] { String.valueOf(town_id) });
    }


    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    /**
     * get datetime
     * */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

}