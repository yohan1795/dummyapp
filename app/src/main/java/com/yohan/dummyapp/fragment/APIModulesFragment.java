package com.yohan.dummyapp.fragment;

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

public class APIModulesFragment extends Fragment {
    @OnClick(R.id.buttonHttp)
    public void goToHttp()
    {
        ((NavigationActivityMain)getActivity()).addFragment(new HttpCallFragment(), "HTTP");
    }
    @OnClick(R.id.buttonRetrofit)
    public void goRetrofit()
    {
        ((NavigationActivityMain)getActivity()).addFragment(new RetrofitFragment(), "Retrofit");
    }
    @OnClick(R.id.buttonAdvanceMap)
    public void goMap()
    {
        ((NavigationActivityMain)getActivity()).addFragment(new AdvanceMapFragment(), "Adv. Map");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_api_modules, container, false);
        ButterKnife.bind(this, view);
        ((NavigationActivityMain) this.getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.api_modules));
        ((NavigationActivityMain) this.getActivity()).lockDrawer();
        return view;
    }
}
