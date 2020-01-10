package com.yikuan.androidcommon.ui;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yikuan.androidcommon.base.widget.BaseDialog;

/**
 * @author yikuan
 * @date 2020/01/10
 */
public class CustomDialog extends BaseDialog {
    private int mLayoutRes;
    private View[] mViews;
    private int[] mIds;
    private int[] mValues;
    private View.OnClickListener[] mOnClickListeners;

    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    public CustomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CustomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected int getLayoutRes() {
        if (mLayoutRes == 0) {
            throw new RuntimeException("u must set layout res first.");
        }
        return mLayoutRes;
    }

    @Override
    protected void initView() {
        if (mLayoutRes == 0) {
            return;
        }
        if (mIds == null) {
            return;
        }
        mViews = new View[mIds.length];
        for (int i = 0; i < mIds.length; i++) {
            int id = mIds[i];
            mViews[i] = findViewById(id);
            setValue(id, mValues[i]);
            setOnClickListener(id, mOnClickListeners[i]);
        }
    }

    public void setLayoutRes(int layoutRes) {
        mLayoutRes = layoutRes;
    }

    /**
     * 设置控件id
     */
    public void setIds(int[] ids) {
        mIds = ids;
    }

    /**
     *  设置控件的值
     * @param values 长度需与{@link #mIds}一致，空值为0
     */
    public void setValues(int[] values) {
        mValues = values;
    }

    /**
     * 设置控件的点击事件监听器
     * @param listeners 长度需与{@link #mIds}一致，空值为null
     */
    public void setOnClickListeners(View.OnClickListener[] listeners) {
        mOnClickListeners = listeners;
    }

    /**
     * 设置控件的值
     * @param id 控件id
     * @param value 控件的值
     * @see TextView#setText(int)
     * @see ImageView#setImageResource(int)
     */
    private void setValue(int id, int value) {
        int index = getIndex(id);
        if (index >= 0) {
            View view = mViews[index];
            if (view instanceof TextView) {
                ((TextView) view).setText(value);
            } else if (view instanceof ImageView) {
                ((ImageView) view).setImageResource(value);
            }
        }
    }

    private void setOnClickListener(int id, View.OnClickListener listener) {
        int index = getIndex(id);
        if (index >= 0) {
            mViews[index].setOnClickListener(listener);
        }
    }

    public View getView(int id) {
        int index = getIndex(id);
        if (index >= 0) {
            return mViews[index];
        }
        return null;
    }

    private int getIndex(int id) {
        if (mIds == null) {
            return -1;
        }
        for (int i = 0; i < mIds.length; i++) {
            if (mIds[i] == id) {
                return i;
            }
        }
        return -1;
    }

}
