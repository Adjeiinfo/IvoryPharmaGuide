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
import java.util.List;

public class StockActivity extends AppCompatActivity {

    private ListView listView;
    static final ArrayList<HashMap<String,String>> myList =
            new ArrayList<HashMap<String,String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pharmacieverifierdispo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
        listView = (ListView) findViewById(R.id.stockView);
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                myList,
                R.layout.pharmacieverifierdispo,
                new String[] {"Pharmacie: ","Disponibilite: ","Distance: "},
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
        test.put("Disponibilite: ", "Abondant");
        test.put("Distance: ", "800m");

        test2.put("Pharmacie: ","Pharmacie Du Ciel");
        test2.put("Disponibilite: ", "Limitee");
        test2.put("Distance: ", "4km");

        test3.put("Pharmacie: ","Pharacie Ma Vie");
        test3.put("Disponibilite: ", "Abondant");
        test3.put("Distance: ", "7km");

        myList.add(test);
        myList.add(test2);
        myList.add(test3);
    }


}
