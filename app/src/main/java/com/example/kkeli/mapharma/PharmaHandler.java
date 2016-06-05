package com.example.kkeli.mapharma;

/**
 * Created by 150482 on 2016/05/16.
 */
import java.text.ParseException;
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
import android.widget.SimpleCursorTreeAdapter;

public class PharmaHandler extends SQLiteOpenHelper {

    //Pharmacie de garde table
    public  static final String PHD_COLUMN_ID = "phdid";
    public  static final String PHD_COLUMN_PID = "phdpid";
    public static final String  PHD_COLUMN_START_DATE = "start_date";
    public static final String  PHD_COLUMN_END_DATE = "end_date";
    // All variables about DB
    // Database name
    private static final String DATABASE_NAME = "pharma11";

    // Table Column names (add all column names here)
    // Database version
    private static final int DATABASE_VERSION = 13;
    // Pharmacie table name
    private static final String TABLE_PHARMA = "pharmaTable8";
    //Town table name
    private static  final String TABLE_TOWN = "towntable4";
    //Pharmacie de garde table name
    private static final String TABLE_PHD ="pharmaciedegarde4";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_EMAIL = "email";

    //Town Tables column names
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_PHOTOGRAPH = "photograph";
    private static final String COLUMN_TOWN = "town";
    private static final String COLUMN_REGION ="region";
    private static final String TOWN_COLUMN_NAME = "twonname";
    //Common column
    private static final String  KEY_CREATED_AT = "created_at";
    private static final String TOWN_COLUNM_ID = "town_id";
    private static final String COLUMN_ID = "id";
    //Pharma table create statement
    //Town create statement
    private static final String CREATE_TABLE_TOWN =
            "CREATE TABLE "+
                    TABLE_TOWN +
                    "("
                    + TOWN_COLUNM_ID + " INTEGER PRIMARY KEY,"
                    + TOWN_COLUMN_NAME + " TEXT,"
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
            + COLUMN_TOWN + " TEXT,"
            + " FOREIGN KEY(" + COLUMN_ID + ") REFERENCES "
            + TABLE_TOWN + "("+ TOWN_COLUNM_ID +")"
            + ")";
    //Pharmacy de garde
    private static final String CREATE_TABLE_PHD = "CREATE TABLE " +
             TABLE_PHD +
            "("
               + PHD_COLUMN_PID +" INTEGER PRIMARY KEY,"
               + PHD_COLUMN_ID + " INTEGER, "
               + PHD_COLUMN_START_DATE +  " DATETIME,"  //should be date time  and will be changed to
               + PHD_COLUMN_END_DATE + " DATETIME,"      //should be date and will be changed to
               + " FOREIGN KEY(" + PHD_COLUMN_ID + ") REFERENCES "
               + TABLE_PHARMA + "("+ COLUMN_ID +")"
               +")";
    private String[] columns= {COLUMN_ID, COLUMN_NAME, COLUMN_PHONE, COLUMN_EMAIL, COLUMN_ADDRESS, COLUMN_PHOTOGRAPH,COLUMN_REGION,COLUMN_TOWN};

    //Table create statement
    private String[] townColums ={TOWN_COLUNM_ID,TOWN_COLUMN_NAME};
    private String[] phdColumns = {PHD_COLUMN_PID,PHD_COLUMN_ID,PHD_COLUMN_START_DATE,PHD_COLUMN_END_DATE };

    // Create database
    public PharmaHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_TOWN);
        db.execSQL(CREATE_TABLE_PHD);
        Log.d("Table created", "Test");
    }

    // Drop table if older version exist
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TOWN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHARMA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHD);
        onCreate(db);
    }

	/*h
	 * Handling Pharma table using sql queries.
	 * */

    // insert Pharma
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
        return i != 0;
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

    // Reading all pharma
    public List<Pharma> readAllPharma(String townName){
        // Get db writable
        SQLiteDatabase db = this.getWritableDatabase();

        // Define pharmas list
        List<Pharma> pharmas = new ArrayList<Pharma>();
        String sQuery = "SELECT  * FROM "
                + TABLE_PHARMA + " WHERE "
                + COLUMN_TOWN + " = "
                +'"'+ townName + '"';
        Cursor cursor = db.rawQuery(sQuery,null);
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
    // Reading all pharma
    public int readPharmaID(String pharmaName, String townName){
        // Get db writable
        SQLiteDatabase db = this.getWritableDatabase();

        int pharmaID = 999999;
        String sQuery = "SELECT  * From "
                + TABLE_PHARMA + " WHERE "
                + COLUMN_NAME + " = "
                +'"'+ pharmaName + '"'
                + " AND " + COLUMN_TOWN+ " = "
                +  '"'+ townName + '"';  //Changer cette ligne  mais avancons un peu
        Cursor cursor = db.rawQuery(sQuery,null);

        try {
            while (cursor.moveToNext()) {
                pharmaID = cursor.getInt(0);
            }
        } finally {
            cursor.close();
        }
        return pharmaID;
    }



    // insert town
    public boolean insertTown(Town town){
        // Get db writable
        SQLiteDatabase db = this.getWritableDatabase();
        // Get the values to insert
        ContentValues vals = new ContentValues();
        vals.put(TOWN_COLUMN_NAME, town.getName());

        // Insert values into table
        long i = db.insert(TABLE_TOWN, null, vals);
        // Close database
        db.close();

        return i != 0;
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

        return i != 0;
    }

    // Deleting pharma
    public boolean deletePharma(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        int i = db.delete(TABLE_PHARMA, COLUMN_ID + " = ?",  new String[] { String.valueOf(id) });
        db.close();

        return i != 0;
    }

    //------------------------------- Town related -------------------------------
    //Town related CRUD
    //Insert a town
    /**
     * Creating tag
     */

    public long createTown(Town town) {
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
    public int updateTown(Town town) {
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
    public void deleteTown(long town_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TOWN, TOWN_COLUNM_ID + " = ?",
                new String[] { String.valueOf(town_id) });
    }


    //------------------------------- Pharmacie de garde related-------------------------------
    //Town related CRUD
    //Insert pharmacy de garde
    /**
     * Creating pharmacy de garde
     */
    	/*
	 * Handling Pharma table using sql queries.
	 * */

    // insert Pharma
    public boolean insertPharmaDeGarde(PharmaDeGarde pharma){
        // Get db writable
        SQLiteDatabase db = this.getWritableDatabase();

        // Get the values to insert
        ContentValues vals = new ContentValues();
        vals.put(PHD_COLUMN_ID, pharma.getPID());
        //Log.d("MyId", pharma.getPID()+"");
        //Log.d("insert Start date", pharma.getStartDate() +" ");
        //Log.d("insert Start date", pharma.getEndDate() +" ");
        vals.put(PHD_COLUMN_START_DATE, getDate(pharma.getStartDate()));
        vals.put(PHD_COLUMN_END_DATE, getDate(pharma.getEndDate()));

        // Insert values into table
        long i = db.insert(TABLE_PHD, null, vals);
        // Close database

        db.close();
        return i != 0;

    }

    // Reading all pharma
    public List<Pharma> readAllPharmaDeGarde(String townName, String startDate, String endDate){
        // Get db writable
        SQLiteDatabase db = this.getWritableDatabase();

        // Define pharmas list
        // Make the query
        String phdQuery = " SELECT * FROM " +TABLE_PHARMA +" ph "
                         +" INNER JOIN " + TABLE_PHD + " phg " + " ON "
                         + " ph" +"." + COLUMN_ID + " = " + "phg" +"." +  PHD_COLUMN_ID
                         +" WHERE ph." + COLUMN_TOWN +" = " + '"' +townName + '"'
                         + " AND "+ "strftime('%Y%m%d', "+"phg" +"."+PHD_COLUMN_START_DATE + " )= " + "'"+startDate + "'"
                         + " AND "+ "strftime('%Y%m%d', "+"phg" + "."+ PHD_COLUMN_END_DATE + " )= " + "'" + endDate+ "'";
        //Log.d("query: ", phdQuery);

        List<Pharma> pharmas = new ArrayList<Pharma>();
        Cursor cursor = db.rawQuery(phdQuery, null);
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

    /*
    * update town
    */
    public int updatePharmaGarde(PharmaDeGarde pharmaDeGarde) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
       // values.put(PHD_COLUMN_START_DATE, pharmaDeGarde.getName());

        // updating row
        return db.update(TABLE_TOWN, values, TOWN_COLUNM_ID + " = ?",
                new String[] { String.valueOf(pharmaDeGarde.getPID()) });
    }
    /**
     * delete town
     */
    public void deletePharmaGarde(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PHD, PHD_COLUMN_ID + " = ?",
                new String[] { String.valueOf(id) });
    }


    //-----------------general stuff---------------------------------------
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

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public  String getDate(Date dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(dateString);
    }
}