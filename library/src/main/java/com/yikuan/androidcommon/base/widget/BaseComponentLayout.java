package com.yikuan.androidcommon.base.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

/**
 * @author yikuan
 * @date 2020/08/25
 */
public abstract class BaseComponentLayout extends BaseLifecycleBindingLayout implements ViewModelStoreOwner {
    private ViewModelStore mViewModelStore;

    public BaseComponentLayout(Context context) {
        this(context, null);
    }

    public BaseComponentLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseComponentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source,
                                       @NonNull Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    getViewModelStore().clear();
                }
            }
        });
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        if (mViewModelStore == null) {
            mViewModelStore = new ViewModelStore();
        }
        return mViewModelStore;
    }
}
