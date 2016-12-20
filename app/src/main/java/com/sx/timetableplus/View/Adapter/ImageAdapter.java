package com.sx.timetableplus.View.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sx.timetableplus.R;
import com.sx.timetableplus.databinding.ItemImageBinding;

import java.util.List;

/**
 * Created by sx on 2016/12/20.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> mData;
    private OnItemClickListener onItemClickListener;

    public ImageAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_image, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mBinding.setUrl(mData.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.OnItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ItemImageBinding mBinding;

        public MyViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}
