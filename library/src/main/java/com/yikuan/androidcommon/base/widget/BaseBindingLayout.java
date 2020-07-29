package com.yikuan.androidcommon.base.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.LayoutRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @author yikuan
 * @date 2020/07/28
 */
public abstract class BaseBindingLayout extends ConstraintLayout {
    private ViewDataBinding mBinding;

    public BaseBindingLayout(Context context) {
        this(context, null);
    }

    public BaseBindingLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseBindingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), getLayoutRes(), this, true);
    }

    public ViewDataBinding getBinding() {
        return mBinding;
    }

    @LayoutRes
    protected abstract int getLayoutRes();
}
