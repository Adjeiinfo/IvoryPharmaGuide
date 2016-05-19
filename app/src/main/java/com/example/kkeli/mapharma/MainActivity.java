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

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public PharmaHandler pharmaHandler;
    public final static String EXTRA_MESSAGE = "com.example.kkeli.mapharma";
    Button allPharmaBtn, pharmaDeGardeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pharmaHandler = new PharmaHandler(this);
        addContactTest();
        addTown();

        //test to laod the data for initial check
        displayPharmaTest();
        displayPharmaTown();
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
        Intent intent = new Intent(this,AllPharmaActivity.class);
        startActivity(intent);

    }

    public void getPharmaDeGarde(View view) {
        //Ready to list the pharmas in the users area
        Intent intent = new Intent(this, PharmaDeGardeActivity.class);
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
        pharmaHandler.addPharmaDetails(new Pharma("Pharmacie des etoile", "9800112233", "ajay@ajay.com", "", "","Abidjan","Cocody"));
        pharmaHandler.addPharmaDetails(new Pharma("Pharmacie du ciel", "9644556677", "vishal@vishal.com", "", "","Yakro","Yakro"));
        pharmaHandler.addPharmaDetails(new Pharma("Pharmacie le paradis", "9488990011", "mahesh@mahesh.com", "", "","Daloa","Daloa"));
        pharmaHandler.addPharmaDetails(new Pharma("Pharmacie la vie", "9222334455", "manoj@manoj.com", "", "","Boundoukou","Bondoukou"));
    }

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
        }
    }

    public void displayTownt()
    {
        // Reading all contacts
        List<Town> myTown = pharmaHandler.readAllTown();
        for(Town c : myTown){
            String record = "ID=" + c.getID() + " | Name=" + c.getName() ;
            Log.d("Record",record);
        }
    }

}
