package com.example.gads_leaderboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int NUM_PAGES = 2;
    private ViewPager2 viewPager;
    private FragmentStateAdapter pagerAdapter;
    private Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        fragments = new Fragment[2];
        fragments[0] = new HoursList();
        fragments[1] = new ScoresList();

        pagerAdapter = new ScreenSlidePageAdapter(this);
        viewPager.setAdapter(pagerAdapter);


    }

    private class ScreenSlidePageAdapter extends FragmentStateAdapter{
        public ScreenSlidePageAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments[position];
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }



}