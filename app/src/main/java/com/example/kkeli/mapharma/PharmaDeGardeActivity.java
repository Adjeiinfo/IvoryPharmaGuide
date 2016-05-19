package com.example.kkeli.mapharma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PharmaDeGardeActivity extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recherchepharmacie);
        String[] defaultPharma;
        //Default list for demonstration
/*
        //Default list for demonstration
        listView = (ListView) findViewById(R.id.listview1);


        defaultPharma = new String[] { "Abidjan","Adzope","Agboville","Boundoukou","Tanda","Yamoussoukro" };
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_selectable_list_item,
                defaultPharma );

        listView.setAdapter(arrayAdapter);*/
    }
}
