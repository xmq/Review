package com.xu.review.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;

import com.xu.review.R;

public class LYJSwipeRefreshLayout extends SwipeRefreshLayout {
    private static final String TAG = LYJSwipeRefreshLayout.class.getCanonicalName();
    private int mScrollableChildId;//控件ID
    private View mScrollableChild;//子控件

    public LYJSwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public LYJSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取监听子控件的ID
        TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.LYJSwipeLayoutAttrs);
        mScrollableChildId = a.getResourceId(R.styleable.LYJSwipeLayoutAttrs_scrollableChildId, 0);
        mScrollableChild = findViewById(mScrollableChildId);
        a.recycle();
    }

    @Override
    public boolean canChildScrollUp() {
        //判断有没有传入子控件
        ensureScrollableChild();
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (mScrollableChild instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mScrollableChild;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return mScrollableChild.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(mScrollableChild, -1);
        }
    }

    private void ensureScrollableChild() {
        if (mScrollableChild == null) {
            mScrollableChild = findViewById(mScrollableChildId);
        }
    }
}