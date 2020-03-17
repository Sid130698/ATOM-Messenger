package com.example.atommessenger;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {
private Toolbar mToolbar;
private ViewPager myViewPager;
private TabLayout myTabLayout;
private TabsAccessorAdaptor myTabsAccessorAdaptor;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mToolbar=(Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Atom-Messenger");

        myViewPager=(ViewPager) findViewById(R.id.main_tabs_pager);
        myTabsAccessorAdaptor=new TabsAccessorAdaptor(getSupportFragmentManager());
        myViewPager.setAdapter(myTabsAccessorAdaptor);

        myTabLayout=(TabLayout) findViewById(R.id.main_tabs);
        myTabLayout.setupWithViewPager(myViewPager);
    }
}
