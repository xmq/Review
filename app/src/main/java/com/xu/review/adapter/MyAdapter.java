package com.xu.review.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xu.review.R;
import com.xu.review.poro.TestData;

import java.util.List;

/**
 * Created by Administrator on 2016/7/11.
 */
public class MyAdapter extends BaseRecyclerAdapter<TestData> {
    private Context context;
    private int resId;

    public MyAdapter(Context context, int resId) {
        this.context = context;
        this.resId = resId;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(resId,parent,false);

        return new BaseRecyclerAdapter<TestData>.Holder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, TestData data) {
        ImageView imageView = (ImageView) viewHolder.itemView.findViewById(R.id.iv_1);
        TextView textView = (TextView) viewHolder.itemView.findViewById(R.id.tv_1);
        textView.setText(data.text);
    }
}
