package com.sx.timetableplus.View.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sx.timetableplus.Model.Timeline;
import com.sx.timetableplus.R;
import com.sx.timetableplus.databinding.HeaderTimelineListBinding;
import com.sx.timetableplus.databinding.ItemTimelineBinding;

import java.util.List;


/**
 * Created by sx on 2016/12/19.
 */

public class MineTimelineAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Timeline> mData;
    private ImageAdapter imageAdapter;

    public MineTimelineAdapter(Context mContext, List<Timeline> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(mContext).inflate(R.layout.header_timeline_list, parent, false);
            return new HeaderViewHolder(view);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_timeline, parent, false);
            return new MyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).mBinding.setTimeline(mData.get(position));
            if (!mData.get(position).getPictures().isEmpty()) {
                imageAdapter = new ImageAdapter(mContext, mData.get(position).getPictures());
                ((MyViewHolder) holder).mBinding.timelinePictureRecycler.setVisibility(View.VISIBLE);
                ((MyViewHolder) holder).mBinding.timelinePictureRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                ((MyViewHolder) holder).mBinding.timelinePictureRecycler.setAdapter(imageAdapter);
            } else {
                ((MyViewHolder) holder).mBinding.timelinePictureRecycler.setVisibility(View.GONE);
            }
        } else if (holder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) holder).mBinding.setUser("Miaopasi~~");
            ((HeaderViewHolder) holder).mBinding.setPortrait("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3729610624,381369976&fm=11&gp=0.jpg");
            ((HeaderViewHolder) holder).mBinding.setBackground("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1610/31/c5/29213364_1477922832832_800x800.jpg");
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return 0;
        else
            return 1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ItemTimelineBinding mBinding;

        public MyViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        HeaderTimelineListBinding mBinding;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}
