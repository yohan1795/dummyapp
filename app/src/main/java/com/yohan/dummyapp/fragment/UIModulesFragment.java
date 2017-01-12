package com.yohan.dummyapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yohan.dummyapp.R;
import com.yohan.dummyapp.activity.NavigationActivityMain;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Admin on 04-01-17.
 */

public class UIModulesFragment extends Fragment {

    @OnClick(R.id.button_ui_linear)
        public void linearClick(){
            ((NavigationActivityMain)getActivity()).addFragment(new LinearLayoutFragment(), getResources().getString(R.string.linear));
        }
    @OnClick(R.id.button_ui_relative)
        public void relativeClick(){
            ((NavigationActivityMain)getActivity()).addFragment(new RelativeLayoutFragment(), getResources().getString(R.string.relative));
        }
    @OnClick(R.id.button_ui_recycler)
        public void recyclerClick(){
            ((NavigationActivityMain)getActivity()).addFragment(new TabViewFragment(), getResources().getString(R.string.recycler));
        }
    @OnClick(R.id.button_ui_web)
        public void webClick(){
            ((NavigationActivityMain)getActivity()).addFragment(new WebViewFragment(), getResources().getString(R.string.web));
        }
    @OnClick(R.id.button_ui_map)
        public void mapClick(){
            ((NavigationActivityMain)getActivity()).addFragment(new MapListFragment(), getResources().getString(R.string.map));
        }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ui_modules, container, false);
        ((NavigationActivityMain) this.getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.ui_modules));
        ((NavigationActivityMain) this.getActivity()).lockDrawer();
        ButterKnife.bind(this, view);
        return view;
    }
}
