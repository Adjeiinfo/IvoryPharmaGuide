package com.example.kkeli.mapharma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class AllPharmaActivity extends AppCompatActivity {
    Intent intent = getIntent();
    private ListView lv;
    private PharmaHandler pharmaHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainview);

        pharmaHandler = new PharmaHandler(getApplicationContext());

        lv = (ListView)findViewById(R.id.lv_pharma_list);

        loadTownData();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AllPharmaActivity.this, PharmacyActivity.class);
                startActivity(intent);
            }
        });
    }


    private void loadTownData(){
        // Code for loading contact list in ListView
        List<Town> towns = pharmaHandler.readAllTown();

        // Initialize Custom Adapter
        Town.TownAdpater adapter = new Town.TownAdpater(this, towns);

        // Set Adapter to ListView
        lv.setAdapter(adapter);
    }
}
