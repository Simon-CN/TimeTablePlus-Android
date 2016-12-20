package com.sx.timetableplus.View.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sx.timetableplus.Model.Timeline;
import com.sx.timetableplus.R;
import com.sx.timetableplus.databinding.ItemTimelineBinding;

import java.util.List;


/**
 * Created by sx on 2016/12/19.
 */

public class MineTimelineAdapter extends RecyclerView.Adapter<MineTimelineAdapter.MyViewHolder> {
    private Context mContext;
    private List<Timeline> mData;

    public MineTimelineAdapter(Context mContext, List<Timeline> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MineTimelineAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_timeline, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MineTimelineAdapter.MyViewHolder holder, int position) {
        holder.mBinding.setTimeline(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ItemTimelineBinding mBinding;

        public MyViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}
