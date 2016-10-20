package com.sx.timetableplus.View.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sx.timetableplus.Modle.TimetablePerDay;
import com.sx.timetableplus.R;
import com.sx.timetableplus.databinding.ItemTimetablePerDayBinding;
import com.sx.timetableplus.databinding.ItemTimetableTotalBinding;
import java.util.List;

/**
 * Created by sx on 2016/10/20.
 */

public class TimetableRecyclerAdapter extends RecyclerView.Adapter<TimetableRecyclerAdapter.MyViewHolder> {
    private Context mContext;
    private List<TimetablePerDay> mData;
    private OnItemClickListener mOnItemClickListener;

    public TimetableRecyclerAdapter(Context mContext, List<TimetablePerDay> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 1)
            view = LayoutInflater.from(mContext).inflate(R.layout.item_timetable_per_day, parent, false);
        else
            view = LayoutInflater.from(mContext).inflate(R.layout.item_timetable_total, parent, false);
        return new MyViewHolder(view, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        return position == 7 ? 0 : 1;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (position == 7) {
            holder.totalBinding.totalLessonTxt.setText("10");
        } else {
            holder.dayBinding.dayOfWeekTxt.setText(String.valueOf(position + 1));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null)
                    mOnItemClickListener.OnItemClick(view);
            }
        });
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ItemTimetablePerDayBinding dayBinding;
        ItemTimetableTotalBinding totalBinding;

        public MyViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == 1)
                dayBinding = DataBindingUtil.bind(itemView);
            else
                totalBinding = DataBindingUtil.bind(itemView);
        }
    }


    public interface OnItemClickListener {
        void OnItemClick(View view);
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
