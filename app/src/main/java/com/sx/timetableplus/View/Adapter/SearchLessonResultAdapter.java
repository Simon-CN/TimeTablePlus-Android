package com.sx.timetableplus.View.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.sx.timetableplus.databinding.ItemSearchResultBinding;

/**
 * Created by sx on 2016/10/30.
 */

public class SearchLessonResultAdapter extends RecyclerView.Adapter<SearchLessonResultAdapter.MyViewHolder> {


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ItemSearchResultBinding mBinding;

        public MyViewHolder(View itemView, ItemSearchResultBinding mBinding) {
            super(itemView);
            this.mBinding = mBinding;
        }
    }
}
