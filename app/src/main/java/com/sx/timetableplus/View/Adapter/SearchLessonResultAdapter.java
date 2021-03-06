package com.sx.timetableplus.View.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sx.timetableplus.Model.LessonInfo;
import com.sx.timetableplus.R;
import com.sx.timetableplus.databinding.ItemSearchResultBinding;

import java.util.List;

/**
 * Created by sx on 2016/10/30.
 */

public class SearchLessonResultAdapter extends RecyclerView.Adapter<SearchLessonResultAdapter.MyViewHolder> {
    private Context mContext;
    private List<LessonInfo> mData;
    private OnItemClickListener mListener;

    public SearchLessonResultAdapter(Context mContext, List<LessonInfo> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_search_result, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mBinding.setLesson(mData.get(position));

        holder.mBinding.addLessonToTimetableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null)
                    mListener.OnClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface OnItemClickListener {
        void OnClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ItemSearchResultBinding mBinding;

        public MyViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }

}
