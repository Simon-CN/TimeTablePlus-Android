package com.sx.timetableplus.View.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.utils.ImageUtils;
import com.sx.timetableplus.R;
import com.sx.timetableplus.databinding.HeaderSelectImagesBinding;
import com.sx.timetableplus.databinding.ItemSelectedImagesBinding;

import java.util.List;


/**
 * Created by sx on 2016/12/21.
 */

public class SelectPicturesAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<String> mData;
    private OnItemClickListener onItemClickListener;

    public SelectPicturesAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 1) {
            view = LayoutInflater.from(mContext).inflate(R.layout.header_select_images, parent, false);
            return new CameraViewHolder(view);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_selected_images, parent, false);
            return new PictureViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof CameraViewHolder) {
            ((CameraViewHolder) holder).mBinding.selectImagesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.OnClick(position);
                    }
                }
            });
        } else if (holder instanceof PictureViewHolder) {
            Bitmap bm = ImageUtils.getBitmap(mData.get(position - 1));
            ((PictureViewHolder) holder).mBinding.selectedImage.setImageBitmap(bm);
            ((PictureViewHolder) holder).mBinding.removeImageBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.OnClick(position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else
            return 2;
    }

    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }

    class CameraViewHolder extends RecyclerView.ViewHolder {
        HeaderSelectImagesBinding mBinding;

        public CameraViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }

    class PictureViewHolder extends RecyclerView.ViewHolder {
        ItemSelectedImagesBinding mBinding;

        PictureViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }

    public interface OnItemClickListener {
        void OnClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}
