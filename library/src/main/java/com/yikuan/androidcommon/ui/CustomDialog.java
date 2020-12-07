package com.yikuan.androidcommon.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
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
    private Object[] mValues;
    private View.OnClickListener[] mOnClickListeners;
    private boolean mCancelOnClickRoot;

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
            throw new RuntimeException("u must set layout res first");
        }
        return mLayoutRes;
    }

    @Override
    protected void initView() {
        if (mLayoutRes == 0) {
            return;
        }
        if (mIds == null || mValues == null) {
            return;
        }
        mViews = new View[mIds.length];
        for (int i = 0; i < mIds.length; i++) {
            int id = mIds[i];
            View view = findViewById(id);
            if (view == null) {
                continue;
            }
            Object value = null;
            if (i < mValues.length) {
                value = mValues[i];
            }
            if (value != null) {
                setViewValue(view, value);
            }
            View.OnClickListener listener = null;
            if (mOnClickListeners != null && i < mOnClickListeners.length) {
                listener = mOnClickListeners[i];
            }
            if (listener != null) {
                view.setOnClickListener(listener);
            }
            mViews[i] = view;
        }
        View root = findViewById(android.R.id.content);
        if (mCancelOnClickRoot) {
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
    }

    private void setViewValue(View view, Object value) {
        if (view instanceof TextView) {
            setText((TextView) view, value);
        } else if (view instanceof ImageView) {
            setImage((ImageView) view, value);
        }
    }

    public void setLayoutRes(@LayoutRes int layoutRes) {
        mLayoutRes = layoutRes;
    }

    /**
     * 设置控件id
     */
    public void setIds(int[] ids) {
        mIds = ids;
    }

    /**
     * 设置控件的值
     *
     * @param values 长度需与{@link #mIds}一致，空值{@code null}表示不设置对应id的控件的值
     */
    public void setValues(Object[] values) {
        mValues = values;
    }

    /**
     * 设置控件的点击事件监听器
     *
     * @param listeners 长度需与{@link #mIds}一致，空值{@code null}表示不设置对应id的控件的值
     */
    public void setOnClickListeners(View.OnClickListener[] listeners) {
        mOnClickListeners = listeners;
    }

    public void setCancelOnClickRoot(Boolean cancel) {
        mCancelOnClickRoot = cancel;
    }

    private View getView(int id) {
        if (mViews == null) {
            return null;
        }
        int index = getIndex(id);
        if (index >= 0 && index < mViews.length) {
            return mViews[index];
        }
        return null;
    }

    private void setText(TextView textView, Object value) {
        if (value instanceof Integer) {
            textView.setText((Integer) value);
        } else if (value instanceof CharSequence) {
            textView.setText((CharSequence) value);
        } else {
            throw new IllegalArgumentException("value is illegal");
        }
    }

    private void setImage(ImageView imageView, Object value) {
        if (value instanceof Drawable) {
            imageView.setImageDrawable((Drawable) value);
        } else if (value instanceof Bitmap) {
            imageView.setImageBitmap((Bitmap) value);
        } else if (value instanceof Integer) {
            imageView.setImageResource((Integer) value);
        } else {
            throw new IllegalArgumentException("value is illegal");
        }
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
