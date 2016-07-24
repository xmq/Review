package com.xu.review;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

/**
 * Created by liyuanjing on 2016/1/26.
 */
public class LYJGridLayout extends GridLayout {
    /***
     * 获取的是那个子View
     */
    private int childPosition;
    private int currentPosition;
    private View tempChildView;

    public LYJGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("liyuanjinglyj", ev.getRawX() + ":rawx=");
        Log.i("liyuanjinglyj", ev.getRawY() + ":rawy=");
        Log.i("liyuanjinglyj", getChildAt(4).getX() + ":x=");
        Log.i("liyuanjinglyj", getChildAt(4).getY() + ":y=");
        Log.i("liyuanjinglyj", getChildAt(4).getWidth() + ":width=");
        Log.i("liyuanjinglyj", getChildAt(4).getHeight() + ":height=");
        float rawX = ev.getRawX();
        float rawY = ev.getRawY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for (int i = 0; i < getChildCount(); i++) {
                    float startX = getChildAt(i).getX();
                    float stopX = getChildAt(i).getX() + getChildAt(i).getWidth();
                    float startY = getChildAt(i).getY();
                    float stopY = getChildAt(i).getY() + getChildAt(i).getHeight();
                    if (rawX >= startX && rawX <= stopX && rawY >= startY && rawY <= stopY) {
                        childPosition = i;
                        Log.i("liyuanjinglyj", "position=" + i);
                        break;
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (getChildAt(childPosition) != null) {
                    getChildAt(childPosition).setX((int) (rawX - getChildAt(childPosition).getWidth() / 2));
                    getChildAt(childPosition).setY((int) (rawY - getChildAt(childPosition).getHeight() / 2));
                    getChildAt(childPosition).invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                for (int i = 0; i < getChildCount(); i++) {
                    float startX = getChildAt(i).getX();
                    float stopX = getChildAt(i).getX() + getChildAt(i).getWidth();
                    float startY = getChildAt(i).getY();
                    float stopY = getChildAt(i).getY() + getChildAt(i).getHeight();
                    if (rawX >= startX && rawX <= stopX && rawY >= startY && rawY <= stopY) {
                        if (getChildAt(i) != null && i != childPosition) {
                            tempChildView = getChildAt(childPosition);
                            removeView(tempChildView);
                            tempChildView.setPivotX(startX);
                            tempChildView.setPivotY(startY);
                            tempChildView.invalidate();
                            addView(tempChildView, i);
                        }
                        Log.i("liyuanjinglyj", "position=" + i);
                        break;
                    }
                }
                return true;
            default:
                return false;
        }
        return false;
    }
}
