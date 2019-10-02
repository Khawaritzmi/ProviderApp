package com.muflihun.favoriteapptester.activity;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.muflihun.favoriteapptester.R;
import com.muflihun.favoriteapptester.adapter.PagerAdapter;
import com.muflihun.favoriteapptester.fragment.MovieFragment;
import com.muflihun.favoriteapptester.fragment.TvFragment;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabs = findViewById(R.id.tabs);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), 2, this);
        MovieFragment movieFragment = new MovieFragment();
        TvFragment tvFragment = new TvFragment();
        pagerAdapter.attach(movieFragment, getResources().getString(R.string.movie));
        pagerAdapter.attach(tvFragment, getResources().getString(R.string.tv_show));
        viewPager.setAdapter(pagerAdapter);
        tabs.setupWithViewPager(viewPager);
    }
}