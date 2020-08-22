package com.yikuan.androidcommon.base.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

/**
 * @author yikuan
 * @date 2020/08/21
 */
public abstract class BaseLifecycleBindingLayout extends BaseBindingLayout implements LifecycleOwner {
    private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

    public BaseLifecycleBindingLayout(Context context) {
        this(context, null);
    }

    public BaseLifecycleBindingLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseLifecycleBindingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getBinding().setLifecycleOwner(this);
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mLifecycleRegistry.markState(Lifecycle.State.STARTED);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mLifecycleRegistry.markState(Lifecycle.State.DESTROYED);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }
}
