package com.hackday.play.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackday.play.Adapters.MyFragAdapter;
import com.hackday.play.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuhanson on 2017/6/3.
 */

public class MyFragment extends Fragment {
    private ViewPager viewPager;
    private MyFragAdapter myFragAdapter;
    private List<Fragment> fragmentList=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myumbrellas, container, false);
        viewPager=(ViewPager) view.findViewById(R.id.umbrella_ViewPager);
        SquareFragment fragment=new SquareFragment(),fragment1=new SquareFragment(),fragment2=new SquareFragment();
        fragmentList.add(fragment);
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        myFragAdapter=new MyFragAdapter(getChildFragmentManager(),fragmentList);
        viewPager.setAdapter(myFragAdapter);
        return view;
    }
}
