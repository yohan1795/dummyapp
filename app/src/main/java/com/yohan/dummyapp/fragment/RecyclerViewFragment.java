package com.yohan.dummyapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yohan.dummyapp.R;
import com.yohan.dummyapp.adapter.CustomRecyclerViewAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Admin on 21-12-16.
 */

public class RecyclerViewFragment extends Fragment {
    private List<String> myList;
    private RecyclerView rv_List;
    private CustomRecyclerViewAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    public RecyclerViewFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle bundle)
    {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        if(getArguments().getString("lang").equals("en"))
        {
            myList = Arrays.asList(getResources().getStringArray(R.array.en_movie_titles));
        }
        else if(getArguments().getString("lang").equals("hi"))
        {
            myList = Arrays.asList(getResources().getStringArray(R.array.hi_movie_titles));
        }
        else
        {
            Toast.makeText(view.getContext(), "String key \'lang\'not found", Toast.LENGTH_SHORT).show();
        }

        rv_List = (RecyclerView) view.findViewById(R.id.recycler_dummy);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_List.setLayoutManager(layoutManager);

        adapter = new CustomRecyclerViewAdapter(myList);
        rv_List.setAdapter(adapter);

        return view;

    }

}
