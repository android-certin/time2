package com.ciandt.worldwonders.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ciandt.worldwonders.R;

import com.ciandt.worldwonders.adapter.WonderRecyclerAdapter;
import com.ciandt.worldwonders.adapter.WorldWonderPageAdapter;
import com.ciandt.worldwonders.database.WonderDAO;
import com.ciandt.worldwonders.model.Wonder;

import java.util.Collections;

import java.util.List;

public class WondersFragment extends Fragment {
    private final int NUM_PAGES = 3;

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_world_wonder, container, false);
        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WonderDAO wonderDAO = new WonderDAO(getActivity());
        List<Wonder> wonderList = wonderDAO.getAll();
        Collections.shuffle(wonderList);

        viewPager = (ViewPager) view.findViewById(R.id.pager);
        pagerAdapter = new WorldWonderPageAdapter(getFragmentManager(),wonderList);
        viewPager.setAdapter(pagerAdapter);

        recyclerView = (RecyclerView) view.findViewById(R.id.wonder_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(new WonderRecyclerAdapter(wonderList,getContext()));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}



