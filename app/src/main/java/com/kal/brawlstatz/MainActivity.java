package com.kal.brawlstatz;
//BETA
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    vpAdapter vpadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);  //splashscreen installation
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //viewPager initialisation
        viewPager2 = findViewById(R.id.viewpager);
        vpadapter = new vpAdapter(this);
        viewPager2.setAdapter(vpadapter);

        //Changing Fragment by selecting Tab.
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==3) viewPager2.setUserInputEnabled(false);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if(tab.getPosition()==3) viewPager2.setUserInputEnabled(true);
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        //Changing the tab when changing fragment by horizontal swipes.
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Objects.requireNonNull(tabLayout.getTabAt(position)).select();
            }
        });
    }
}