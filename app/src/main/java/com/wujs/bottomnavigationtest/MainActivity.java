package com.wujs.bottomnavigationtest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.wujs.bottomnavigation.IndexViewPager;
import com.wujs.bottomnavigation.TabItem;
import com.wujs.bottomnavigation.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements TabLayout.OnTabClickListener {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    IndexViewPager mViewPager;
    private List<TabItem> tabs;
    private int selectedPosition;
    private boolean isPermsGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }



    private void initData() {
        tabs = new ArrayList<>();
        tabs.add(BottomTabPosConstant.HOME_TAB_POS, new TabItem(R.drawable.selector_tab_home_icon, R.string.tabactivity_tab_home_text, PaxFragment.class));
        tabs.add(BottomTabPosConstant.CONCERN_TAB_POS, new TabItem(R.drawable.selector_tab_concern_icon, R.string.tabactivity_tab_concern_text, ConcernFragment.class));
        tabs.add(BottomTabPosConstant.NEARBY_TAB_POS, new TabItem(R.drawable.selector_tab_nearby_icon, R.string.tabactivty_tab_nearby_text, NearbyFragment.class));
        tabs.add(BottomTabPosConstant.ME_TAB_POS, new TabItem(R.drawable.selector_tab_me_icon, R.string.tabactivity_tab_me_text, MeFragment.class));
        tabLayout.intData(tabs, this);
        tabLayout.setCurrentTab(0);
        FragAdapter adapter = new FragAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(2);
        //Change the selected tag, when the viewpager changes page.
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mViewPager.setScanScroll(true); //enable scroll of viewpager
                tabLayout.setCurrentTab(position);
                selectedPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onTabClick(TabItem tabItem) {
        int position = tabs.indexOf(tabItem);
        if (position != selectedPosition) {
            mViewPager.setScanScroll(false);
            selectedPosition = position;
            mViewPager.setCurrentItem(tabs.indexOf(tabItem)); // Here change the selected tag by setting current item of ViewPager.
        }
    }


    private class FragAdapter extends FragmentPagerAdapter {

        FragAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int arg0) {
            try {
                return tabs.get(arg0).getTagFramgentClz().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
                return null;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public int getCount() {
            return tabs.size();
        }
    }
}

