package com.yohan.dummyapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yohan.dummyapp.R;
import com.yohan.dummyapp.activity.NavigationActivityMain;
import com.yohan.dummyapp.adapter.MapListAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Admin on 27-12-16.
 */

public class MapListFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView locationList;
    private MapListAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_list, container, false);
        ((NavigationActivityMain) this.getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.map));
        locationList = (ListView) view.findViewById(R.id.lv_map_list);

        List<String> locNames = Arrays.asList(getResources().getStringArray(R.array.locations));
        List<String> locLats = Arrays.asList(getResources().getStringArray(R.array.locationsLat));
        List<String> locLngs = Arrays.asList(getResources().getStringArray(R.array.locationsLng));

        adapter = new MapListAdapter(locNames, locLats, locLngs);

        locationList.setAdapter(adapter);
        locationList.setOnItemClickListener(this);

        ((NavigationActivityMain)getActivity()).showHome();
        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
    {
        Bundle bundle = new Bundle();
        bundle.putString("location", (String)adapter.getItem(position));
        bundle.putFloat("lat", adapter.getLat(position));
        bundle.putFloat("lng", adapter.getLng(position));

        GoogleMapFragment fragment = new GoogleMapFragment();
        fragment.setArguments(bundle);

        ((NavigationActivityMain) getActivity()).addFragment(fragment, "Location");
    }
}
