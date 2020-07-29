package com.yikuan.androidcommon.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.yikuan.androidcommon.R;

/**
 * @author yikuan
 * @date 2020/07/22
 */
public class Divider extends View {
    private int mOrientation;
    private int mSize;
    private int mColor;

    public Divider(Context context) {
        this(context, null);
    }

    public Divider(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Divider(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Divider(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context, attrs);
    }

    private void initialize(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Divider);
        mOrientation = ta.getInt(R.styleable.Divider_android_orientation, 0);
        mSize = ta.getDimensionPixelOffset(R.styleable.Divider_size, 1);
        mColor = ta.getColor(R.styleable.Divider_color, getResources().getColor(R.color.divider));
        ta.recycle();
        setBackgroundColor(mColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (mOrientation == 0 && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, mSize);
        } else if (mOrientation == 1 && widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mSize, heightSize);
        }
    }
}
