package com.example.kkeli.mapharma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class PharmaDeGardeActivity extends AppCompatActivity {
    Intent intent = getIntent();
    List<Pharma> pharmas;
    private ListView listView;
    private ListView lv;
    private PharmaHandler pharmaHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainview);
        String[] defaultPharma;
        //Default list for demonstration
        // Get intent data
        Bundle extras = getIntent().getExtras();
        //TextView tv_name = (TextView) findViewById(R.id.tv_town_name);
        //Log.d("Got name:", extras.getString("name"));
        String townName = extras.getString("name");
        String startDate = extras.getString("startDate");
        String endDate = extras.getString("endDate");

        //load the pharma list
        pharmaHandler = new PharmaHandler(getApplicationContext());

        //Get the list view
        lv = (ListView)findViewById(R.id.lv_pharma_list);

        loadPharmaDegarde(townName,startDate,endDate);
        //receive intent results here

        // Load the pharmacie of the selected town
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PharmaDeGardeActivity.this, PharmaDetailActivity.class);
                //keep the details for the selected pharma
                intent.putExtra("name", pharmas.get(position).getName());
               // Log.d("name",pharmas.get(position).getName());
                intent.putExtra("phone", pharmas.get(position).getPhoneNumber());
                //Log.d("phone",pharmas.get(position).getPhoneNumber());

                intent.putExtra("email", pharmas.get(position).getEmail());
                //Log.d("email",pharmas.get(position).getEmail());

                intent.putExtra("address", pharmas.get(position).getPostalAddress());
                //Log.d("address",pharmas.get(position).getPostalAddress());


                //might not be required
                intent.putExtra("town", pharmas.get(position).getTown());
                intent.putExtra("region", pharmas.get(position).getRegion());

                startActivity(intent);
            }
        });
    }

    //retreive all all pharmas from the database
  /*  private void loadPharmaData(){
        // Code for loading pharmas list in ListView
        pharmas = pharmaHandler.readAllPharma();
        // Initialize Custom Adapter
        CustomAdapter adapter = new CustomAdapter(this, pharmas);
        // Set Adapter
        lv.setAdapter(adapter);
    }*/

    //retrieve pharma for a specific town
    private void loadPharmaDegarde(String townName, String startDate, String endDate ){
        // Code for loading pharmas list in ListView
        pharmas = pharmaHandler.readAllPharmaDeGarde(townName, startDate,endDate);
        // Initialize Custom Adapter
        CustomAdapter adapter = new CustomAdapter(this, pharmas);
        // Set Adapter to
        lv.setAdapter(adapter);
    }

}
