package com.example.kkeli.mapharma;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
/*
* Class to display the pharmacies of a selected town.
* The Output will be to select a pharmacy to view its details in the PharmaDetailActivy
 */
public class PharmacyActivity extends AppCompatActivity {
    Intent intent = getIntent();
    List<Pharma> pharmas;
    private ListView lv;
    private PharmaHandler pharmaHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainview);

        // Get intent data
        Bundle extras = getIntent().getExtras();
        //TextView tv_name = (TextView) findViewById(R.id.tv_town_name);
        //Log.d("Got name:", extras.getString("name"));
        String townName = extras.getString("name");

        //load the pharma list
        pharmaHandler = new PharmaHandler(getApplicationContext());

        //Get the list view
        lv = (ListView)findViewById(R.id.lv_pharma_list);

        Log.d("Hereeee "," Pharcie");
        loadPharmaData(townName);
        //receive intent results here

        // Load the pharmacie of the selected town
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PharmacyActivity.this, PharmaDetailActivity.class);
                //keep the details for the selected pharma
                intent.putExtra("name", pharmas.get(position).getName());
                Log.d("name",pharmas.get(position).getName());
                intent.putExtra("phone", pharmas.get(position).getPhoneNumber());
                Log.d("phone",pharmas.get(position).getPhoneNumber());

                intent.putExtra("email", pharmas.get(position).getEmail());
                Log.d("email",pharmas.get(position).getEmail());

                intent.putExtra("address", pharmas.get(position).getPostalAddress());
                Log.d("address",pharmas.get(position).getPostalAddress());


                //might not be required
                intent.putExtra("town", pharmas.get(position).getTown());
                intent.putExtra("region", pharmas.get(position).getRegion());

                startActivity(intent);
            }
        });
    }

    //retreive all all pharmas from the database
    private void loadPharmaData(){
        // Code for loading pharmas list in ListView
        pharmas = pharmaHandler.readAllPharma();
        // Initialize Custom Adapter
        CustomAdapter adapter = new CustomAdapter(this, pharmas);
        // Set Adapter
        lv.setAdapter(adapter);
    }

    //retrieve pharma for a specific town
    private void loadPharmaData(String townName ){
        // Code for loading pharmas list in ListView
        pharmas = pharmaHandler.readAllPharma(townName);
        // Initialize Custom Adapter
        CustomAdapter adapter = new CustomAdapter(this, pharmas);
        // Set Adapter to
        lv.setAdapter(adapter);
    }
}
