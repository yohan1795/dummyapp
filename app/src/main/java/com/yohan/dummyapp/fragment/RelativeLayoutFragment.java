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
 * Created by Yohan on 20-12-16.
 * To implement Java class for Relative Layout Fragment
 */

public class RelativeLayoutFragment extends Fragment {
    /**
     * To inflate the required view on @param: container
     * @param inflater inflates the layout
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_relative_layout, container, false);
        ((NavigationActivityMain) this.getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.relative));
        return view;
    }

}
