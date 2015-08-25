package com.ciandt.worldwonders.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.ciandt.worldwonders.fragment.HighlightFragment;
import com.ciandt.worldwonders.model.Wonder;

import java.util.List;

/**
 * Created by wgomes on 25/08/15.
 */
public class WorldWonderPageAdapter extends FragmentPagerAdapter {
    private final int NUM_PAGES = 3;
    List<Wonder> wonderList;

    public WorldWonderPageAdapter(FragmentManager fm, List<Wonder> wonders) {
        super(fm);
        wonderList = wonders;
    }


    @Override
    public Fragment getItem(int position) {
        return HighlightFragment.newInstance(wonderList.get(position));
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }


}