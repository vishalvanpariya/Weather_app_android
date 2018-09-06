package com.example.vishal.whether_app_for_android;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;


import java.util.LinkedList;

public class Pageadepter extends FragmentStatePagerAdapter {

    private LinkedList<Fragment> linkedList = new LinkedList<Fragment>();


    public Pageadepter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return linkedList.get(position);
    }

    @Override
    public int getCount() {
        return linkedList.size();
    }


    public void addFrag(frag2 frag) {
        linkedList.add(frag);
    }

    public void Removepage(){
        linkedList.clear();
    }

}
