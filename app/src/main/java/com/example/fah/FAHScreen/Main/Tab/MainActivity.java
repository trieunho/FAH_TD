package com.example.fah.FAHScreen.Main.Tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.fah.FAHModel.Models.Account;
import com.example.fah.FAHScreen.Post.DetailSearchPostActivity;
import com.example.fah.FAHScreen.Main.ViewPaper.ViewPaperMain;
import com.example.fah.R;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPaperMain viewPaperMain = new ViewPaperMain();
    public static Account userLogin=new Account();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewpager);
        initTab();
    }

    private void initTab() {
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorAccent));
        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);
        viewPaperMain.SetupTabIcons(tabLayout);
    }

    private void setupViewPager() {
        viewPaperMain.SetupContentTab(getSupportFragmentManager(), viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // noinspection SimplifiableIfStatement
        switch (item.getItemId()){
            case R.id.action_search: {
                startActivity(new Intent(this, DetailSearchPostActivity.class));
                return true;
            }
            default:{
                return super.onOptionsItemSelected(item);
            }
        }

    }
}
