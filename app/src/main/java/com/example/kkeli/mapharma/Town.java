package com.example.kkeli.mapharma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 150482 on 2016/05/19.
 */
public class Town {

    // Variables
    private int _id;
    private String _name;


    // Constructor
    public Town(){

    }

    // Constructor
    public Town(String name){
        this._name = name;
    }

    // ID getter and setter functions
    public int getID(){
        return _id;
    }

    public void setID(int id){
        this._id = id;
    }

    // Name getter and setter functions
    public String getName(){
        return _name;
    }

    public void setName(String name){
        this._name = name;
    }

    /**
     * Created by 150482 on 2016/05/16.
     */
    // CustomAdapter.java
    public static class TownAdpater extends BaseAdapter {
        private PharmaHandler pharmaHandler;
        private Town town;
        private List<Town> items;
        private Context context;
        private LayoutInflater inflater;

        public TownAdpater(Context _context, List<Town> _items){
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
            Town town = items.get(position);

            View view = convertView;

            if(view == null)
                view = inflater.inflate(R.layout.recherchepharmacie, null);

            TextView name = (TextView) view.findViewById(R.id.tv_ville_detail);

            ImageView photo = (ImageView) view.findViewById(R.id.list_image);

            name.setText(town.getName());

            return view;
        }

    }
}
