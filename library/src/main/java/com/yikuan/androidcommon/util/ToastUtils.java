package com.yikuan.androidcommon.util;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.StringRes;

import com.yikuan.androidcommon.AndroidCommon;

/**
 * @author yikuan
 * @date 2019/10/24
 */
public class ToastUtils {
    private static Toast sToast;
    private static Handler sHandler;

    static {
        sToast = new Toast(AndroidCommon.getApp());
        sHandler = new Handler(Looper.getMainLooper());
    }

    private ToastUtils() {
        throw new UnsupportedOperationException("cannot be instantiated.");
    }

    public static void showShort(@StringRes final int resId, Object... args) {
        show(resId, Toast.LENGTH_SHORT, args);
    }

    public static void showLong(@StringRes final int resId, Object... args) {
        show(resId, Toast.LENGTH_LONG, args);
    }

    public static void showShortSafely(@StringRes final int resId, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                showShort(resId, args);
            }
        });
    }

    public static void showLongSafely(@StringRes final int resId, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                showLong(resId, args);
            }
        });
    }

    public static void cancel() {
        sToast.cancel();
    }

    private static void show(@StringRes final int resId, int duration, Object... args) {
        show(AndroidCommon.getApp().getString(resId, args), duration);
    }

    private static void show(CharSequence text, int duration) {
        sToast.cancel();
        sToast.setText(text);
        sToast.setDuration(duration);
        sToast.show();
    }
}
