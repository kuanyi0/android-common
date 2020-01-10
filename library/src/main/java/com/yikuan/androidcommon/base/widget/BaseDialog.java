package com.yikuan.androidcommon.base.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yikuan.androidcommon.util.FloatingWindowManager;

/**
 * @author yikuan
 * @date 2020/01/10
 */
public abstract class BaseDialog extends Dialog {

    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        if (window != null) {
            window.setType(FloatingWindowManager.getInstance().getType());
        }
        setContentView(getLayoutRes());
        initView();
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract void initView();
}
