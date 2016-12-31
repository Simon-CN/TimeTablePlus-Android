package com.sx.timetableplus.View.Activity;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;

import com.lhh.ptrrv.library.PullToRefreshRecyclerView;
import com.lhh.ptrrv.library.footer.loadmore.DefaultLoadMoreView;

/**
 * Created by sx on 2016/12/21.
 */

public abstract class BasePullLoadActivity extends BaseActivity {

    public PullToRefreshRecyclerView mRecyclerView;

    public abstract void getData(boolean isRefresh);

    protected void setupPullLoad() {
        mRecyclerView.setSwipeEnable(true);
        mRecyclerView.setLoadMoreFooter(new DefaultLoadMoreView(this, mRecyclerView.getRecyclerView()));
        mRecyclerView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(true);
            }
        });
        mRecyclerView.setPagingableListener(new PullToRefreshRecyclerView.PagingableListener() {
            @Override
            public void onLoadMoreItems() {
                loadData(false);
            }
        });

    }

    protected void initView() {
        startRefresh();
    }

    protected void loadData(final boolean isRefresh) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData(isRefresh);
            }
        }, 1000);
    }

    protected void startRefresh() {
        mRecyclerView.setRefreshing(true);
        loadData(true);
    }

    protected void endLoading() {
        mRecyclerView.setOnLoadMoreComplete();
        mRecyclerView.onFinishLoading(true, false);
    }

    protected void endRefresh() {
        mRecyclerView.setOnRefreshComplete();
        mRecyclerView.onFinishLoading(true, false);
    }

    protected void endLoadingRefresh(boolean isRefresh) {
        if (isRefresh)
            endRefresh();
        else
            endLoading();
    }

    protected void setNoMoreData() {
        mRecyclerView.setOnLoadMoreComplete();
        mRecyclerView.onFinishLoading(false, false);
    }
}
