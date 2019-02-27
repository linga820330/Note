package com.example.note;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoneyFragment extends Fragment {

    private TabLayout tab_Money;
    private ViewPager  vp_Money;
    private TabAdapter adapter;

    public static final String[] tabTitle = new String[]{"1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"};

    private View view;


    public MoneyFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_money, container, false);
        tab_Money = (TabLayout)view.findViewById(R.id.tab_Money);
        vp_Money = (ViewPager)view.findViewById(R.id.vp_Money);
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < tabTitle.length; i++) {
            fragments.add(Money_tabFragment.newInstance(i + 1));
        }
        //將TabLayout和ViewPager關聯起来
        adapter = new TabAdapter(getActivity().getSupportFragmentManager(),fragments);
        //adapter.notifyDataSetChanged();
        vp_Money.setAdapter(adapter);
        tab_Money.setupWithViewPager(vp_Money);
        //設置可以滑動
        tab_Money.setTabMode(TabLayout.MODE_SCROLLABLE);



        return view;
    }

    public class TabAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> fragments;


        public TabAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }


        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        //设置tablayout标题
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitle[position];

        }

    }
}
