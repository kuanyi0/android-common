package com.yikuan.androidcommon.base.mvp.noleak;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

/**
 * @author yikuan
 * @date 2019/09/18
 */
public abstract class BasePresenter<T> {
    protected Reference<T> mViewRef;

    public void attachView(T view) {
        mViewRef = new SoftReference<>(view);
    }

    protected T getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
