package com.yohan.dummyapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yohan.dummyapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 27-12-16.
 */

public class MapListAdapter extends BaseAdapter {
    private ArrayList<String> locationList;
    private ArrayList<Float> latList;
    private ArrayList<Float> lngList;
    //Constructor to initialize data
    public MapListAdapter(List<String> locationList, List<String> latList, List<String> lngList)
    {
        this.locationList = new ArrayList<>();
        this.latList = new ArrayList<>();
        this.lngList = new ArrayList<>();

        for(String loc:locationList)
            this.locationList.add(loc);
        for(String lat: latList)
            this.latList.add(new Float(lat));
        for(String lng: lngList)
            this.lngList.add(new Float(lng));
    }

    @Override
    public int getCount() {
        return (int)locationList.size();
    }

    @Override
    public Object getItem(int i) {
        return locationList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item, parent, false);
        TextView location = (TextView) view.findViewById(R.id.title_text);
        location.setText(locationList.get(i));
        return view;
    }

    public float getLat(int i)
    {
        return latList.get(i);
    }

    public float getLng(int i)
    {
        return lngList.get(i);
    }

}
