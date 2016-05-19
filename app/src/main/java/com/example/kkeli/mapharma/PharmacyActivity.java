package com.example.kkeli.mapharma;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

/*
* Class to display the pharmacies of a selected town.
* The Output will be to select a pharmacy to view its details in the PharmaDetailActivy
 */
public class PharmacyActivity extends AppCompatActivity {

    Intent intent = getIntent();
    private ListView lv;
    private PharmaHandler pharmaHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_activy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //load the pharma list
        loadPharmaData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Get the list view
        lv = (ListView)findViewById(R.id.lv_pharma_list);
        //receive intent results here

        // Load the pharmacie of the selected town
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PharmacyActivity.this, PharmaDetailActivity.class);
               // intent.putExtra("id",contacts.get(position).getID());
                //intent.putExtra("name", contacts.get(position).getName());
                //intent.putExtra("phone", contacts.get(position).getPhoneNumber());
                startActivity(intent);
            }
        });
    }

    private void loadPharmaData(){
        // Code for loading contact list in ListView
        List<Pharma> pharmas = pharmaHandler.readAllPharma();

        // Initialize Custom Adapter
        CustomAdapter adapter = new CustomAdapter(this, pharmas);

        // Set Adapter to
        lv.setAdapter(adapter);
    }

}
