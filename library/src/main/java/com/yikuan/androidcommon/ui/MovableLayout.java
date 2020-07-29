package com.yikuan.androidcommon.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * @author yikuan
 * @date 2020/07/22
 */
public class MovableLayout extends ConstraintLayout {
    private int mDownX;
    private int mDownY;
    private int mLastX;
    private int mLastY;
    private int mTouchSlop;
    private boolean mMoved;
    private Listener mListener;

    public MovableLayout(Context context) {
        this(context, null);
    }

    public MovableLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MovableLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = x;
                mDownY = y;
                mMoved = false;
                break;
            case MotionEvent.ACTION_MOVE:
                int index = event.getActionIndex();
                int pointerId = event.getPointerId(index);
                if (pointerId != 0) {
                    break;
                }
                if (!mMoved && Math.abs(x - mDownX) > mTouchSlop || Math.abs(y - mDownY) > mTouchSlop) {
                    mMoved = true;
                }
                int offsetX = x - mLastX;
                int offsetY = y - mLastY;
                if (mListener != null) {
                    mListener.onMove(offsetX, offsetY);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (!mMoved) {
                    performClick();
                }
                break;
            default:
                break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    public interface Listener {
        void onMove(int offsetX, int offsetY);
    }

    private void initialize(Context context) {
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }
}
