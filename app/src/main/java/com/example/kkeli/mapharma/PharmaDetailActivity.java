package com.example.kkeli.mapharma;
/*
* Class to display the detail of a selected pharmacy
* The is is somehow the last screen unless the user decides to use the map guide
 */
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class PharmaDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharma_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Receive the intent for the pharma detail and display the view
        // Get intent data
        Bundle extras = getIntent().getExtras();

       /* ImageView iv_photo = (ImageView) findViewById(R.id.iv_contact_photo);
        iv_photo.setImageBitmap(BitmapFactory.decodeFile(extras.getString("photograph")));*/

        TextView tv_name = (TextView) findViewById(R.id.tv_pharma_name);
        tv_name.setText(extras.getString("name"));

        TextView tv_phone = (TextView) findViewById(R.id.tv_phone_number);
        tv_phone.setText(extras.getString("phone"));

        TextView tv_address = (TextView) findViewById(R.id.tv_physical_address);
        tv_phone.setText(extras.getString("address"));

        TextView tv_address_desc = (TextView) findViewById(R.id.tv_physical_address);
        tv_phone.setText(extras.getString("address"));

        //if required will extract town and region from the intent extra data
    }
}
