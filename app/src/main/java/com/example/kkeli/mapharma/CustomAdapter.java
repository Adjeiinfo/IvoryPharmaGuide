package com.example.kkeli.mapharma;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/**
 * Created by 150482 on 2016/05/16.
 */
// CustomAdapter.java
public class CustomAdapter extends BaseAdapter {
    private PharmaHandler pharmaHandler;
    private Pharma pharma;
    private List<Pharma> items;
    private Context context;
    private LayoutInflater inflater;

    public CustomAdapter(Context _context, List<Pharma> _items){
        inflater = LayoutInflater.from(_context);
        this.items = _items;
        this.context = _context;

    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Pharma pharma = items.get(position);

        View view = convertView;

        if(view == null)
            view = inflater.inflate(R.layout.pharmacieproche, null);

        TextView name = (TextView) view.findViewById(R.id.tv_pharma_name);
        TextView distance = (TextView) view.findViewById(R.id.tv_distance);
        ImageView photo = (ImageView) view.findViewById(R.id.list_image);

        name.setText(pharma.getName());
        distance.setText(pharma.getDistance() +" m");
        //photo.setImageBitmap(BitmapFactory.decodeFile(pharma.getPhotograph()));

        return view;
    }

}