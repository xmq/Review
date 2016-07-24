package com.xu.review;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

public abstract class BaseActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    /***
     * 处理下拉和刷新滴
     */
    protected SwipeRefreshLayout swipeRefreshLayout;
    /***
     * 进化的ListView
     */
    protected RecyclerView recyclerView;
    /***
     * 该布局在没有网络的时候，显示的布局
     */
    protected LinearLayout linearLayout;
    /***
     * RecyclerView的样式（网格，瀑布，线性）
     */
    protected LinearLayoutManager mLayoutManager;
    /***
     * 记录最后一项的位置
     */
    protected int lastVisibleItem=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.activity_main_swipe);
        this.recyclerView=(RecyclerView)findViewById(R.id.activity_main_recyclerview);
        this.linearLayout=(LinearLayout)findViewById(R.id.activity_main_linearlayout);
        initView();
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                onRecyclerViewStateChanged(newState);
            }
        });
    }

    @Override
    public void onRefresh() {
        onRecyclerViewRefresh();
    }

    /***
     * 初始化界面
     */
    public abstract void initView();

    /***
     * 监听RecyclerView滑动事件
     * @param newState 滑动状态
     */
    public abstract void onRecyclerViewStateChanged(int newState);

    /***
     * 下拉刷新处理
     */
    public abstract void onRecyclerViewRefresh();
}