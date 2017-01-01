package com.sx.timetableplus.View.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sx.timetableplus.Model.LessonInfo;
import com.sx.timetableplus.Model.Timeline;
import com.sx.timetableplus.Model.UserInfo;
import com.sx.timetableplus.R;
import com.sx.timetableplus.Utility.GlideUtil;
import com.sx.timetableplus.databinding.HeaderLessonTimelineBinding;
import com.sx.timetableplus.databinding.HeaderTimelineListBinding;
import com.sx.timetableplus.databinding.ItemTimelineBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by sx on 2016/12/19.
 */

public class MineTimelineAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Timeline> mData;
    private ImageAdapter imageAdapter;
    private LessonInfo mLesson;
    private int headerType;

    public MineTimelineAdapter(Context mContext, List<Timeline> mData) {
        this.mContext = mContext;
        this.mData = mData;
        headerType = 1;
    }

    public MineTimelineAdapter(Context mContext, List<Timeline> mData, LessonInfo mLesson) {
        this.mContext = mContext;
        this.mData = mData;
        this.mLesson = mLesson;
        headerType = 2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            if (headerType == 1) {
                view = LayoutInflater.from(mContext).inflate(R.layout.header_timeline_list, parent, false);
                return new HeaderViewHolder(view);
            } else {
                view = LayoutInflater.from(mContext).inflate(R.layout.header_lesson_timeline, parent, false);
                return new LessonHeaderViewHolder(view);
            }

        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_timeline, parent, false);
            return new MyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).mBinding.setTimeline(mData.get(position - 1));
            if (!TextUtils.isEmpty(mData.get(position - 1).getPictures())) {
                String[] pictures = mData.get(position - 1).getPictures().split(",");
                imageAdapter = new ImageAdapter(mContext, Arrays.asList(pictures));
                ((MyViewHolder) holder).mBinding.timelinePictureRecycler.setVisibility(View.VISIBLE);
                ((MyViewHolder) holder).mBinding.timelinePictureRecycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                ((MyViewHolder) holder).mBinding.timelinePictureRecycler.setAdapter(imageAdapter);
            } else {
                ((MyViewHolder) holder).mBinding.timelinePictureRecycler.setVisibility(View.GONE);
            }
        } else if (holder instanceof HeaderViewHolder) {
            UserInfo ui = UserInfo.getInstance(mContext);
            ((HeaderViewHolder) holder).mBinding.setUser(ui.getScreenName());
            ((HeaderViewHolder) holder).mBinding.setPortrait(ui.getPortrait());
            ((HeaderViewHolder) holder).mBinding.setBackground(ui.getBackground());
        } else if (holder instanceof LessonHeaderViewHolder) {
            ((LessonHeaderViewHolder) holder).mBinding.setLesson(mLesson);
        }

    }

    @Override
    public int getItemCount() {
        return mData.size() + 1;
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

    class LessonHeaderViewHolder extends RecyclerView.ViewHolder {
        HeaderLessonTimelineBinding mBinding;

        public LessonHeaderViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }
}
