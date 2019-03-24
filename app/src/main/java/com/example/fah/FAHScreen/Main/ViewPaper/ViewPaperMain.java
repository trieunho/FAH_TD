package com.example.fah.FAHScreen.Main.ViewPaper;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.example.fah.FAHControl.FAHViewPaper.FAHViewPagerAdapter;
import com.example.fah.FAHScreen.Main.Tab.NotificationFragment;
import com.example.fah.FAHScreen.Main.Tab.SearchFragment;
import com.example.fah.FAHScreen.Main.Tab.HotPostFragment;
import com.example.fah.FAHScreen.Main.Tab.MenuFragment;
import com.example.fah.R;
import com.example.fah.TestControl.TestFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPaperMain {
    List<TabFlagment> listTabFlagmemt;

    public ViewPaperMain(){
        listTabFlagmemt = new ArrayList<>();
        listTabFlagmemt.add(new TabFlagment(new SearchFragment(), "Search Data", R.drawable.fah_icon_search));
        listTabFlagmemt.add(new TabFlagment(new HotPostFragment(), "Search Data", R.drawable.fah_icon_work));
        listTabFlagmemt.add(new TabFlagment(new NotificationFragment(), "Search Data", R.drawable.fah_icon_notifications));
        listTabFlagmemt.add(new TabFlagment(new MenuFragment(), "Search Data", R.drawable.fah_icon_menu));
    }

    public void SetupTabIcons(TabLayout tabLayout) {
        for(int i = 0; i < listTabFlagmemt.size(); i++){
            tabLayout.getTabAt(i).setIcon(listTabFlagmemt.get(i).getIcon());
        }

    }

    public void SetupContentTab(FragmentManager manager, ViewPager viewPager){
        FAHViewPagerAdapter adapter = new FAHViewPagerAdapter(manager);

        for(int i = 0; i < listTabFlagmemt.size(); i++){
            adapter.addFrag(listTabFlagmemt.get(i));
        }

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(listTabFlagmemt.size());
    }
}
