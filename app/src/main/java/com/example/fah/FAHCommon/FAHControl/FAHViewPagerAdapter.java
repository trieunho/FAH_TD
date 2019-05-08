package com.example.fah.FAHCommon.FAHControl;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.fah.FAHScreen.Main.ViewPaper.TabFlagment;

import java.util.ArrayList;
import java.util.List;

public class FAHViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mFragmentTitleList = new ArrayList<>();

    public FAHViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFrag(TabFlagment tabFlagment) {
        mFragmentList.add(tabFlagment.getTabFlagment());
        mFragmentTitleList.add(tabFlagment.getTitle());
    }

    @Override
    public CharSequence getPageTitle(int position) {

        // return null to display only the icon
        return null;
    }
}
