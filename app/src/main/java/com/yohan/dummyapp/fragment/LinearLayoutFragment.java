package com.yohan.dummyapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yohan.dummyapp.R;
import com.yohan.dummyapp.activity.NavigationActivityMain;

/**
 * Created by Admin on 20-12-16.
 */

public class LinearLayoutFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_linear_layout, container, false);
        ((NavigationActivityMain) this.getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.linear));
        return view;
    }

}
