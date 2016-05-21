package com.example.kkeli.mapharma;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class PriceActivity extends AppCompatActivity {
    private ListView listView;
    static final ArrayList<HashMap<String,String>> myList =
            new ArrayList<HashMap<String,String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pharmacieverifierprix);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
        listView = (ListView) findViewById(R.id.priceView);
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                myList,
                R.layout.pharmacieverifierprix,
                new String[] {"Pharmacie: ","Prix: ","Distance: "},
                new int[] {R.id.text1,R.id.text2, R.id.text3}

        );
        populateList();
        listView.setAdapter(adapter);
    }
    private void populateList(){
        HashMap<String, String> test =  new HashMap<String, String>();
        HashMap<String, String> test2 = new HashMap<String, String>();
        HashMap<String, String> test3 = new HashMap<String, String>();
        test.put("Pharmacie: ","Pharmacie Du Plateau");
        test.put("Prix: ", "1000cfa");
        test.put("Distance: ", "800m");

        test2.put("Pharmacie: ","Pharmacie Du Ciel");
        test2.put("Prix: ", "1250cfa");
        test2.put("Distance: ", "4km");

        test3.put("Pharmacie: ","Pharacie Ma Vie");
        test3.put("Prix: ", "855cfa");
        test3.put("Distance: ", "7km");

        myList.add(test);
        myList.add(test2);
        myList.add(test3);
    }
}
