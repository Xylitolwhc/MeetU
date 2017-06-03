package com.hackday.play.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hackday.play.MyApplication;
import com.hackday.play.R;
import com.hackday.play.EditUmbrellaActivity;
import com.hackday.play.Utils.LocationInfor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuhanson on 2017/6/3.
 */

public class MyRecyAdapter extends RecyclerView.Adapter<MyRecyAdapter.mViewHolder> {
    private List<LocationInfor> locationInforList = new ArrayList<>();

    public MyRecyAdapter(List<LocationInfor> locationInforList) {
        if (locationInforList != null)
            this.locationInforList = locationInforList;
    }

    public void setLocationInforList(List<LocationInfor> locationInforList) {
        this.locationInforList = locationInforList;
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        mViewHolder holder;
        if (i != 1)
            holder = new mViewHolder(LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.frag_square_item, viewGroup, false), i);
        else
            holder = new mViewHolder(LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.item_empty, viewGroup, false), i);
        return holder;
    }

    @Override
    public void onBindViewHolder(mViewHolder mViewHolder, int i) {
        Log.d(" ", "" + i);
        if (locationInforList.size() != 0) {
            final LocationInfor locationInfor = locationInforList.get(i);
            mViewHolder.location.setText(locationInfor.getBuilding());
            mViewHolder.remain_time.setText(locationInfor.getTime());
            mViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = MyApplication.getContext();
                    Intent intent=new Intent();
                    intent.setClass(context,EditUmbrellaActivity.class);
                    intent.putExtra("Mode",1);
                    context.startActivity(intent);
                }
            });
        } else {
            mViewHolder.title.setText("暂时没有内容哦");
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (locationInforList.size() != 0)
            return super.getItemViewType(position);
        else return 1;
    }

    @Override
    public int getItemCount() {
        if (locationInforList.size() != 0)
            return locationInforList.size();
        else {
            return 1;
        }

    }

    class mViewHolder extends RecyclerView.ViewHolder {
        protected TextView remain_time, location, title;
        protected ImageView avatar;
        protected CardView cardView;

        public mViewHolder(View itemView, int type) {
            super(itemView);
            if (type != 1) {
                avatar = (ImageView) itemView.findViewById(R.id.item_avatar);
                remain_time = (TextView) itemView.findViewById(R.id.item_remain_time);
                location = (TextView) itemView.findViewById(R.id.item_location);
                title = (TextView) itemView.findViewById(R.id.item_title);
                cardView = (CardView) itemView.findViewById(R.id.item_CardView);
            } else
                title = (TextView) itemView.findViewById(R.id.item_text);
        }
    }
}
