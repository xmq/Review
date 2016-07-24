//package com.xu.review.adapter;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.xu.review.R;
//
//
///**
// * Created by Administrator on 2016/6/30.
// */
//public class BaseAdapter extends RecyclerView.Adapter<? extends RecyclerView.ViewHolder> {
//
//    private Context context;
//    private OnItemClickListener onItemClickListener;
//    private int resId;
//
//    public BaseAdapter(Context context, int resId) {
//        this.context = context;
//        this.resId = resId;
//    }
//
//    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }
//
////    @Override
////    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
////        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(
////                resId, parent, false)
////        );
////        return holder;
////    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public void onBindViewHolder(final MyViewHolder holder, int position) {
//        if (onItemClickListener != null) {
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int pos = holder.getLayoutPosition();
//                    onItemClickListener.onItemClick(holder.itemView, pos);
//                }
//            });
//
//            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    int pos = holder.getLayoutPosition();
//                    onItemClickListener.onItemLongClick(holder.itemView, pos);
//                    return true;
//                }
//            });
//        }
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new MyViewHolder(LayoutInflater.from(context).inflate(resId,parent));
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//
//    public interface OnItemClickListener {
//        void onItemClick(View view, int pos);
//
//        void onItemLongClick(View view, int pos);
//    }
//}
//
//class MyViewHolder extends RecyclerView.ViewHolder {
//
//    TextView singleTv;
//
//    public MyViewHolder(View itemView) {
//        super(itemView);
//        singleTv = (TextView) itemView.findViewById(R.id.tv_single);
//    }
//}