package com.example.kkeli.mapharma;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.kkeli.mapharma";
    public PharmaHandler pharmaHandler;
    //get the start_date and end_date of this week
    public String weekSartDate;
    public String  weekEndDate;
    Button allPharmaBtn, pharmaDeGardeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //set the start and end date of this week
        weekSartDate = getWeekStartDate();
        weekEndDate  = getWeekEndDate();
        //Log.d("First day ", weekSartDate + " ");
        //Log.d("Last day ", weekEndDate + " ");

        pharmaHandler = new PharmaHandler(getApplicationContext());
        //addTown();
        //addContactTest();
        //addPharmacieDeGarde();


        //test to laod the data for initial check
        //displayPharmaTest();
        //displayTown();
        //Code for loading contact list in ListView

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Functions to respond to user interactions
    public void getAllPharma(View view) {
        //Ready to list the pharmas in the users area
        Intent intent = new Intent(MainActivity.this,AllPharmaActivity.class);
        intent.putExtra("type", "ordinaire");
        startActivity(intent);

    }

    public void getPharmaDeGarde(View view) {
        //Ready to list the pharmas in the users area
        Intent intent = new Intent(MainActivity.this, AllPharmaActivity.class);
        intent.putExtra("type", "garde");
        //set the start and end date
        intent.putExtra("startDate", weekSartDate);
        intent.putExtra("endDate",weekEndDate);
        startActivity(intent);

    }

    public  void checkStock(View view){
        //Ready to list the pharmas in the users area
        Intent intent = new Intent(this, StockActivity.class);

        startActivity(intent);

    }

    public  void checkPrice(View view){
        //Ready to list the pharmas in the users area
        Intent intent = new Intent(this, PriceActivity.class);
        startActivity(intent);

    }

    public void addContactTest(){
        //Code for loading contact list in ListView
        pharmaHandler.addPharmaDetails(new Pharma("Pharmacie des etoile", "9800112233", "ajay@ajay.com", "BP 01 Abijan","","Abidjan","Abidjan",1000));
        pharmaHandler.addPharmaDetails(new Pharma("Pharmacie du ciel", "9644556677", "vishal@vishal.com", "BP 12 Aboisso", "", "Aboisso","Aboisso",900));
        pharmaHandler.addPharmaDetails(new Pharma("Pharmacie le paradis", "9488990011", "mahesh@mahesh.com", "BP 20 Abidjan zone3","", "Abidjan","Abidjan",700));
        pharmaHandler.addPharmaDetails(new Pharma("Pharmacie la vie", "9222334455", "manoj@manoj.com", "BP 45 Bondoukou","", "Boundoukou","Bondoukou",0.5f));
    }

    public void addPharmacieDeGarde(){
        //Code for loading contact list in ListView
        boolean test;
        //1. Verifiet que la pharmacie existe
        int phID = pharmaHandler.readPharmaID("Pharmacie le paradis", "Abidjan");


        if((Integer)phID == null) return;
       // Log.d("myid ", phID + "");

        Date startDate = stringToDate("20160606");
        Date endDate = stringToDate("20160612");
        //2. Ajouter ceci a la table des pharmacie de garde
        //PharmaDeGarde myPharmaDegarde = new PharmaDeGarde(phID,startDate,endDate);
        pharmaHandler.insertPharmaDeGarde(new PharmaDeGarde(phID,startDate,endDate));

    }
    /*
    public String isPharmaExiste(String phName)
    {
        String phID = pharmaHandler.readPharmaID("Pharmacie des etoile");
        if( phID == null) return null;

        return phID;
    }*/
    public void displayPharmaTest()
    {
        // Reading all contacts
        List<Pharma> contacts = pharmaHandler.readAllPharma();

        for(Pharma c : contacts){
            String record = "ID=" + c.getID() + " | Name=" + c.getName() + " | " + c.getPhoneNumber();

            Log.d("Record",record);
        }
    }

    public void displayPharmaTown()
    {
        // Reading all contacts
        List<Pharma> contacts = pharmaHandler.readAllPharma();
        for(Pharma c : contacts){
            String record = "ID=" + c.getID() + " | Name=" + c.getName() + " | " + c.getPhoneNumber();
            Log.d("Record",record);
        }
    }

    public void addTown(){
        String allTown [] = {"Abengourou",
                "Abidjan",
                "Aboisso",
                "Adzopé",
                "Agboville",
                "Ányama",
                "Biankouma",
                "Bingerville",
                "Bondoukou",
                "Bouaflé",
                "Bouaké",
                "Bouna, Ivory Coast|Bouna",
                "Boundiali",
                "Bugu",
                "Dabakala",
                "Dabou",
                "Daloa",
                "Danané",
                "Dimbokro",
                "Divo, Ivory Coast|Divo",
                "Ferkessédougou",
                "Gagnoa",
                "Grand Bassam",
                "Grand Lahou",
                "Jacqueville",
                "Katiola (town,|Katiola",
                "Kong, Ivory Coast|Kong",
                "Korhogo",
                "Kouto",
                "Man, Ivory Coast|Man",
                "Marahoué",
                "Odienné",
                "Oumé",
                "Port Bouet",
                "San Pédro",
                "Sassandra",
                "Séguéla (city,|Séguéla",
                "Sinfra",
                "Soubré",
                "Tiagba",
                "Touba, Ivory Coast|Touba",
                "Yamoussoukro",
                "Tanda"
        };
        Town [] town = new Town [allTown.length];
        for (int i = 0; i < allTown.length; i++){
            town[i] = new Town(allTown[i]);
            pharmaHandler.insertTown(town[i]);
        }
    }

    public void displayTown()
    {
        // Reading all contacts
        List<Town> myTown = pharmaHandler.readAllTown();
        for(Town c : myTown){
            String record = "ID=" + c.getID() + " | Name=" + c.getName() ;
            Log.d("Record",record);
        }
        Log.d("Number of town",pharmaHandler.getTownDoCount()+ " ");
    }

    public Date stringToDate(String myStringDate)
    {
        Date myDate = null;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // here set the pattern as you date in string was containing like date/month/year
            myDate = sdf.parse(myStringDate);
        }catch(ParseException ex){
            // handle parsing exception if date string was different from the pattern applying into the SimpleDateFormat contructor
        }
        return myDate;
    }

    public String getWeekStartDate()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyyMMdd", Locale.getDefault());

        Calendar cal = Calendar.getInstance();

        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        weekSartDate = dateFormat.format(cal.getTimeInMillis());

        return weekSartDate;

    }
    public String getWeekEndDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyyMMdd", Locale.getDefault());

        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);

        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        weekEndDate = dateFormat.format(cal.getTimeInMillis());
        return weekEndDate;
    }

}
