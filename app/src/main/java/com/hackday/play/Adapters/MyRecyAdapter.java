package com.hackday.play.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hackday.play.MyApplication;
import com.hackday.play.R;
import com.hackday.play.Utils.LocationInfor;

import java.util.List;

/**
 * Created by wuhanson on 2017/6/3.
 */

public class MyRecyAdapter extends RecyclerView.Adapter<MyRecyAdapter.mViewHolder> {
    private List<LocationInfor> locationInforList;

    public MyRecyAdapter(List<LocationInfor> locationInforList) {
        this.locationInforList = locationInforList;
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        mViewHolder holder = new mViewHolder(LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.frag_square_item, viewGroup, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(mViewHolder mViewHolder, int i) {
        LocationInfor locationInfor = locationInforList.get(i);
        mViewHolder.location.setText(locationInfor.getBuilding());
        mViewHolder.remain_time.setText(locationInfor.getTime());
    }


    @Override
    public int getItemCount() {
        return 0;
    }

    class mViewHolder extends RecyclerView.ViewHolder {
        protected TextView remain_time, location, title;
        protected ImageView avatar;

        public mViewHolder(View itemView) {
            super(itemView);
            avatar = (ImageView) itemView.findViewById(R.id.item_avatar);
            remain_time = (TextView) itemView.findViewById(R.id.item_remain_time);
            location = (TextView) itemView.findViewById(R.id.item_location);
            title = (TextView) itemView.findViewById(R.id.item_title);
        }
    }
}
