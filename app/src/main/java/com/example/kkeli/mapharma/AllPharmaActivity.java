package com.example.kkeli.mapharma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Date;
import java.util.List;

public class AllPharmaActivity extends AppCompatActivity {
    Intent intent = getIntent();
    List<Town> towns;
    private ListView lv;
    private PharmaHandler pharmaHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainview);

        pharmaHandler = new PharmaHandler(getApplicationContext());

        lv = (ListView)findViewById(R.id.lv_pharma_list);
        Bundle extras = getIntent().getExtras();
        //TextView tv_name = (TextView) findViewById(R.id.tv_town_name);
        //
        final String pharmaType = extras.getString("type");
        final String startDate = extras.getString("startDate");
        final String endDate = extras.getString("endDate");
       // Log.d("Got type:", extras.getString("type"));

        loadTownData();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(pharmaType.equals("garde")){
                    intent = new Intent(AllPharmaActivity.this, PharmaDeGardeActivity.class);
                    intent.putExtra("startDate",startDate);
                    intent.putExtra("endDate",endDate);
                }else{
                    intent = new Intent(AllPharmaActivity.this, PharmacyActivity.class);
                }
                intent.putExtra("name", towns.get(position).getName());
                //Log.d("selected town: ", towns.get(position).getName());
                startActivity(intent);
            }
        });
    }


    private void loadTownData(){
        // Code for loading contact list in ListView
        towns = pharmaHandler.readAllTown();

        // Initialize Custom Adapter
        Town.TownAdpater adapter = new Town.TownAdpater(this, towns);

        // Set Adapter to ListView
        lv.setAdapter(adapter);
    }
}
