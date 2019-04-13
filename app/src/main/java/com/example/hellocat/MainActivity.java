package com.example.hellocat;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.app.Fragment;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.hellocat.mvp.CatBreedMVPFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(android.R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolBar);

        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
//                        return new CatBreedMVPFragment();
                        return  new CatBreedFragment();
                    case 1:
                        return new CatCategoriesFragment();
                    case 2:
                        return new CatFavoritesFragment();

                    default:
                        return new CatBreedFragment();
                }
            }
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return getString(R.string.title_breed);
                    case 1:
                        return getString(R.string.title_category);
                    case 2:
                        return getString(R.string.titke_favorities);
                    default:
                        return getString(R.string.title_breed);
                }
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }
}
