package com.xu.review;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class LYJRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /***
     * 每项的数据集合
     */
    private List<String> messageItems;
    /***
     * 监听item点击事件。
     */
    private LYJItemClickListener mItemClickListener;
    /***
     * 一共显示多少条数据
     */
    private int totalSize;

    public LYJRecyclerViewAdapter(List<String> messageItems,int size){
        this.messageItems=messageItems;
        this.totalSize=size;
    }

    /***
     * 监听点击事件接口
     */
    public interface LYJItemClickListener {
        public void onItemClick(View view, int postion);
    }

    /***
     * 设置item点击事件
     * @param listener
     */
    public void setOnItemClickListener(LYJItemClickListener listener) {
        this.mItemClickListener = listener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == Constants.TYPE_ITEM) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.activity_main_adapter_item, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            return new ItemViewHolder(view,this.mItemClickListener);
        }
        //滑动到底部返回footview
        else if (i == Constants.TYPE_FOOTER) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.activity_main_adapter_footview, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof ItemViewHolder){
            ((ItemViewHolder) viewHolder).name.setText(messageItems.get(i));
        }else{
            if(this.totalSize==(getItemCount()-1)){
                ((FooterViewHolder)viewHolder).flagTxt.setText("已经加载完全部内容");
            }else{
                ((FooterViewHolder)viewHolder).flagTxt.setText("正在加载中........");
            }
        }
    }

    @Override
    public int getItemCount() {
        return messageItems.size()+1;//加1是多的footview那一项，也就是滑动到footview就加载，而不是最后数据项。
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return Constants.TYPE_FOOTER;
        } else {
            return Constants.TYPE_ITEM;
        }
    }

    /***
     * 底部布局
     */
    public class FooterViewHolder extends RecyclerView.ViewHolder {
        private TextView flagTxt;
        public FooterViewHolder(View itemView) {
            super(itemView);
            this.flagTxt=(TextView)itemView.findViewById(R.id.activity_main_adapter_footview_txt);
        }
    }

    /***
     * 数据项布局
     */
    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        private LYJItemClickListener mListener;//设置点击事件

        public ItemViewHolder(View itemView, LYJItemClickListener listener) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.activity_main_adapter_item_name);
            this.mListener = listener;
            itemView.setOnClickListener(this);//设置点击事件
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(v, getPosition());
            }

        }
    }
}