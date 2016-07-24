package com.xu.review;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity implements LYJRecyclerViewAdapter.LYJItemClickListener{
    /***
     * 数据项
     */
    private List<String> messageItems=new ArrayList<>();
    /***
     * 自定义adapter
     */
    private LYJRecyclerViewAdapter adapter;
    /***
     * 获取资源文件字符串中间转换集合
     */
    private List<String> strFlag;
    @Override
    public void initView() {
        Toolbar toolbar=(Toolbar)findViewById(R.id.activity_main_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        this.swipeRefreshLayout.setColorSchemeColors(Color.RED);//设置加载内圈颜色
        this.swipeRefreshLayout.setOnRefreshListener(this);//设置下拉刷新事件
        this.swipeRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.activity_main_tablayout_bg));//设置加载外圈颜色
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        mLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);//设置布局样式
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        loadingRecyclerView(Constants.LISTVIEW_INIT);//初始化RecyclerView
    }

    @Override
    public void onRecyclerViewStateChanged(int newState) {
        if (messageItems == null || messageItems.size() <= 0) {
            Snackbar.make(swipeRefreshLayout, "没有数据得先下拉刷新", Snackbar.LENGTH_SHORT).show();
            return;
        }
        //滚动事件结束并且到达最底端
        if (newState == RecyclerView.SCROLL_STATE_IDLE
                && lastVisibleItem + 1 == adapter.getItemCount()) {
//            loadingRecyclerView(Constants.LISTVIEW_DOWNLOAD);//下滑RecyclerView
        }
    }

    @Override
    public void onRecyclerViewRefresh() {
        loadingRecyclerView(Constants.LISTVIEW_REFRESH);//下拉刷新RecyclerView
    }
    public void loadingRecyclerView(int recyclerViewState){
        swipeRefreshLayout.setRefreshing(true);//打开加载动画
        if (!LYJNetwork.isNetworkAvailable(MainActivity.this)) {
            Snackbar.make(swipeRefreshLayout, "没有网络你逗我玩啊？", Snackbar.LENGTH_SHORT).show();
            swipeRefreshLayout.setRefreshing(false);//没有网络时候直接关闭加载动画
            return;
        }
        //当为初始化的时候
        if(recyclerViewState==Constants.LISTVIEW_INIT){
            addStringToList();
            adapter=new LYJRecyclerViewAdapter(messageItems,100);
            recyclerView.setAdapter(adapter);
        }else if(recyclerViewState==Constants.LISTVIEW_REFRESH){
            //当为下拉刷新的时候
            messageItems.clear();
            addStringToList();
            recyclerView.setAdapter(null);
            adapter = new LYJRecyclerViewAdapter(messageItems,100);
            adapter.setOnItemClickListener(MainActivity.this);
            recyclerView.setAdapter(adapter);
        }else{
            //当为下滑加载的时候
            if(messageItems.size()!=100){
                addStringToList();
                adapter.notifyDataSetChanged();
            }
        }
        swipeRefreshLayout.setRefreshing(false);//执行完成也要关闭加载动画
    }

    @Override
    public void onItemClick(View view, int postion) {
        //每项的点击事件
    }

    //模拟增加数据
    public void addStringToList(){
        strFlag= Arrays.asList(getResources().getStringArray(R.array.welltest_array_string));
        for(int i=0;i<strFlag.size();i++){
            messageItems.add(strFlag.get(i));
        }
    }
}
