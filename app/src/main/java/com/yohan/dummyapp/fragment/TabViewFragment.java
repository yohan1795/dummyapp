package com.yohan.dummyapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yohan.dummyapp.R;
import com.yohan.dummyapp.activity.NavigationActivityMain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 21-12-16.
 */

public class TabViewFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private RecyclerViewFragment englishFragment;
    private RecyclerViewFragment hindiFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab_layout, container, false);
        ((NavigationActivityMain) this.getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.recycler));
        viewPager = (ViewPager) view.findViewById(R.id.tabs_view_pager);

        Bundle bundle_en = new Bundle();
        Bundle bundle_hi = new Bundle();

        bundle_en.putString("lang", "en");
        bundle_hi.putString("lang", "hi");

        englishFragment = new RecyclerViewFragment();
        hindiFragment = new RecyclerViewFragment();

        Log.d("Bundle", bundle_en.toString());
        englishFragment.setArguments(bundle_en);
        hindiFragment.setArguments(bundle_hi);

        adapter = new ViewPagerAdapter(getFragmentManager());

        adapter.addFragment(englishFragment, "English");
        adapter.addFragment(hindiFragment, "Hindi");

        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter
    {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> titleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager)
        {
            super(manager);
        }

        @Override
        public Fragment getItem(int position)
        {
            return fragmentList.get(position);
        }

        @Override
        public int getCount()
        {
            return fragmentList.size();
        }

        public void addFragment(Fragment fragment, String title)
        {
            fragmentList.add(fragment);
            titleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    }
}
