package com.yohan.dummyapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yohan.dummyapp.R;

import java.util.List;

/**
 * Created by Yohan on 21/12/2016.
 *
 * This class is a custom adapter to serve the Recycler view
 */

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.MyViewHolder> {

    List<String> titleList;  //Will contain the list of titles to insert in list items

    //Constructor to set provided list to titleList
    public CustomRecyclerViewAdapter(List<String> titleList)
    {
        this.titleList = titleList;
    }

    //Overrided method onCreate of ViewHolder to set a ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item, parent, false);
        return new MyViewHolder(view);
    }

    //Overrided method onBind of ViewHolder to populate the content required
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        holder.tv_item.setText(titleList.get(position));
    }

    //Overrided method to get no. of elements in list
    @Override
    public int getItemCount()
    {
        return titleList.size();
    }

    //Inner class to implement custom ViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_item; //View sent to ViewHolder

        //Constructr to set Layout of the view
        MyViewHolder(View view)
        {
            super(view);
            tv_item = (TextView) view.findViewById(R.id.title_text);
        }

    }

}