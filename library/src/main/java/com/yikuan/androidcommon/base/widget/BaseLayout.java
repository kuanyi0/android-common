package com.yikuan.androidcommon.base.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * @author yikuan
 * @date 2019/10/12
 */
public abstract class BaseLayout extends ConstraintLayout implements View.OnClickListener {
    public BaseLayout(Context context) {
        this(context, null);
    }

    public BaseLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(getLayoutRes(), this);
        initView();
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract void initView();
}
