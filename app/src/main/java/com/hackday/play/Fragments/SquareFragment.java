package com.hackday.play.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackday.play.Adapters.MyRecyAdapter;
import com.hackday.play.R;
import com.hackday.play.Utils.LocationInfor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wuhanson on 2017/6/3.
 */

public class SquareFragment extends Fragment {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private MyRecyAdapter myRecyAdapter;
    private List<LocationInfor> locationInforList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_square, container, false);
        recyclerView=(RecyclerView) view.findViewById(R.id.square_RecyclerView);
        refreshLayout=(SwipeRefreshLayout) view.findViewById(R.id.square_SwipeRefreshLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getContent();

            }
        });
        myRecyAdapter=new MyRecyAdapter(null,getActivity());
        recyclerView.setAdapter(myRecyAdapter);
        refreshLayout.setRefreshing(true);
        getArguments();
        getContent();
        return view;
    }

    private void getContent(){
        for (int i=1;i<10;i++){
            LocationInfor locationInfor=new LocationInfor();
            locationInfor.setTime(new Date().getTime()+"");
            locationInfor.setBuilding("启明学院");
            locationInforList.add(locationInfor);

        }
        myRecyAdapter.setLocationInforList(locationInforList);
        myRecyAdapter.notifyDataSetChanged();
        refreshLayout.setRefreshing(false);
    }
}