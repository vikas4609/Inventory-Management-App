package com.example.sih2020.serviceMan;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.sih2020.R;
import com.example.sih2020.responsibleMan.adapters.RTabAdapter;
import com.google.android.material.tabs.TabLayout;

public class ServicemanRequests extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceman_requests);

        tabLayout=(TabLayout)findViewById(R.id.s_tabLayout);
        viewPager=(ViewPager)findViewById(R.id.s_viewpager);
        tabLayout.addTab(tabLayout.newTab().setText("PENDING"));
        tabLayout.addTab(tabLayout.newTab().setText("COMPLETED"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final RTabAdapter rTabAdapter = new RTabAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(rTabAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

}

