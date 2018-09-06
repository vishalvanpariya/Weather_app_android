package com.example.vishal.whether_app_for_android;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vishal.whether_app_for_android.whether_data.Channel;

import java.util.LinkedList;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class frag1 extends Fragment {




    TabLayout tabLayout;
    ViewPager viewPager;
    private Pageadepter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_frag1, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.Viewpager);
        adapter = new Pageadepter(getFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout = (TabLayout) view.findViewById(R.id.Tablayout);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

        return view;
    }

    public void removepage(LinkedList<Channel> linkedList,String tempuni,
                           String winduni,
                           String visiuni,
                           String presuni){
        adapter = new Pageadepter(getFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        adapter.Removepage();
        adapter.notifyDataSetChanged();
        for (int i = 0;i<linkedList.size();i++){
            Bundle bundle = new Bundle();
            bundle.putSerializable("data",linkedList.get(i));
            bundle.putString("temp",tempuni);
            bundle.putString("wind",winduni);
            bundle.putString("visi",visiuni);
            bundle.putString("press",presuni);
            frag2 fragmentChild = new frag2();
            fragmentChild.setArguments(bundle);
            adapter.addFrag(fragmentChild);
            adapter.notifyDataSetChanged();
            if (adapter.getCount() > 0)
                tabLayout.setupWithViewPager(viewPager);
            viewPager.setCurrentItem(adapter.getCount() - 1);
        }

    }

    public void addPage(LinkedList<Channel> linkedList,
                        String tempuni,
                        String winduni,
                        String visiuni,
                        String presuni) {
        for (int i = 0;i<linkedList.size();i++) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("data", linkedList.get(i));
            bundle.putString("temp",tempuni);
            bundle.putString("wind",winduni);
            bundle.putString("visi",visiuni);
            bundle.putString("press",presuni);
            frag2 fragmentChild = new frag2();
            fragmentChild.setArguments(bundle);
            adapter.addFrag(fragmentChild);
            adapter.notifyDataSetChanged();
            if (adapter.getCount() > 0)
                tabLayout.setupWithViewPager(viewPager);
            viewPager.setCurrentItem(adapter.getCount() - 1);
        }
    }

}
