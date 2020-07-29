package com.yikuan.androidcommon.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * @author yikuan
 * @date 2020/07/27
 */
public class RelativeGuideline extends View {

    public RelativeGuideline(Context context) {
        super(context);
    }

    public RelativeGuideline(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RelativeGuideline(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RelativeGuideline(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(0, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
    }
}
