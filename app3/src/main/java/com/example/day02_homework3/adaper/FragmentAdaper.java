package com.example.day02_homework3.adaper;

import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by 裘翔 on 2019/9/22.
 */

public class FragmentAdaper extends FragmentPagerAdapter{
    private ArrayList<Fragment> list;
    public FragmentAdaper(FragmentManager fm,ArrayList<Fragment> list){
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
