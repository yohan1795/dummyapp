package com.yohan.dummyapp.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yohan.dummyapp.R;
import com.yohan.dummyapp.activity.NavigationActivityMain;

/**
 * Created by Admin on 29-12-16.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {
    private Button btn_ui_modules;
    private Button btn_api_modules;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container,  false);
        ((NavigationActivityMain) this.getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.home));
        btn_ui_modules = (Button) view.findViewById(R.id.uiButton);
        btn_api_modules = (Button) view.findViewById(R.id.apiButton);

        btn_ui_modules.setOnClickListener(this);
        btn_api_modules.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.uiButton)
        {
            ((NavigationActivityMain) getActivity()).addFragment(new UIModulesFragment(), "UI Modules");
        }
        else if(view.getId()==R.id.apiButton)
        {
            ((NavigationActivityMain) getActivity()).addFragment(new APIModulesFragment(), "API Modules");
        }

    }

    @Override
    public void onResume() {
        ((NavigationActivityMain) this.getActivity()).unlockDrawer();
        super.onResume();
    }
}
