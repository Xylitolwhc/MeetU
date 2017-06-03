package com.hackday.play;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wuhanson on 2017/6/3.
 */

public class MyRecyAdapter extends RecyclerView.Adapter<MyRecyAdapter.mViewHolder> {


    @Override
    public mViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(mViewHolder mViewHolder, int i) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    class mViewHolder extends RecyclerView.ViewHolder {
        public mViewHolder(View itemView) {
            super(itemView);
        }
    }
}
