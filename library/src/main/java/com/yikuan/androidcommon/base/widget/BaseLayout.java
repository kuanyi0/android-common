package com.yikuan.androidcommon.base.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.LayoutRes;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * @author yikuan
 * @date 2019/10/12
 */
public abstract class BaseLayout extends ConstraintLayout {
    public BaseLayout(Context context) {
        this(context, null);
    }

    public BaseLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, getLayoutRes(), this);
    }

    @LayoutRes
    protected abstract int getLayoutRes();
}
