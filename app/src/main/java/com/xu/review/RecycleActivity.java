package com.xu.review;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xu.review.adapter.MyAdapter;
import com.xu.review.poro.TestData;
import com.xu.review.recycle.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/22.
 */
public class RecycleActivity extends Activity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private Button changeBtn;
    private List<TestData> datas;
    private MyAdapter adapter1;
    private MyAdapter adapter2;
    private boolean isClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        initData();
        initView();
    }

    private void initData() {
        datas = new ArrayList<TestData>();
        for (int i = 'A';i <= 'Z'; i++){
            datas.add(new TestData((char)i+""));
        }
        adapter1 = new MyAdapter(this,R.layout.view_lv_item_1);
        adapter2 = new MyAdapter(this,R.layout.view_lv_item_2);

//        adapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int pos) {
//                Toast.makeText(RecycleActivity.this,"click",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onItemLongClick(View view, int pos) {
//                Toast.makeText(RecycleActivity.this,"longClick",Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void initView() {
        changeBtn = (Button) this.findViewById(R.id.btn_change);
        changeBtn.setOnClickListener(this);
        recyclerView = (RecyclerView) this.findViewById(R.id.rv_main);
//        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        recyclerView.setAdapter(adapter1);
//        adapter1.addDatas(datas);
        recyclerView.setAdapter(adapter2);
        adapter2.addDatas(datas);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void onClick(View v) {
        isClick = !isClick;
        updateLayout();
    }

    private void updateLayout() {
        if (isClick){
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(adapter1);
            adapter1.addDatas(datas);
        }else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            recyclerView.setAdapter(adapter2);
            adapter2.addDatas(datas);
        }
    }

//    class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
//
//        private OnItemClickListener onItemClickListener;
//
//        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
//            this.onItemClickListener = onItemClickListener;
//        }
//
//        @Override
//        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(RecycleActivity.this).inflate(
//                    R.layout.view_rv_item_single,parent,false)
//            );
//            return holder;
//        }
//
//        @Override
//        public void onBindViewHolder(final MyViewHolder holder, final int position) {
//            holder.singleTv.setText(datas.get(position));
//            if (onItemClickListener != null){
//                holder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        int pos = holder.getLayoutPosition();
//                        onItemClickListener.onItemClick(holder.itemView,pos);
//                    }
//                });
//
//                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                    @Override
//                    public boolean onLongClick(View v) {
//                        int pos = holder.getLayoutPosition();
//                        onItemClickListener.onItemLongClick(holder.itemView,pos);
//                        return true;
//                    }
//                });
//            }
//        }
//
//        @Override
//        public int getItemCount() {
//            return datas.size();
//        }
//    }
//
//    class MyViewHolder extends RecyclerView.ViewHolder{
//
//        TextView singleTv;
//
//        public MyViewHolder(View itemView) {
//            super(itemView);
//            singleTv = (TextView) itemView.findViewById(R.id.tv_single);
//        }
//    }
//
//    public interface OnItemClickListener{
//        void onItemClick(View view,int pos);
//        void onItemLongClick(View view,int pos);
//    }
}
