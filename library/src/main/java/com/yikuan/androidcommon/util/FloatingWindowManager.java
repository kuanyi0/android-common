package com.yikuan.androidcommon.util;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.yikuan.androidcommon.AndroidCommon;

/**
 * @author yikuan
 * @date 2019/10/13
 */
public class FloatingWindowManager {
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;

    private static class instance {
        private static final FloatingWindowManager INSTANCE = new FloatingWindowManager();
    }

    private FloatingWindowManager() {
        mWindowManager = (WindowManager) AndroidCommon.getApp().getSystemService(Context.WINDOW_SERVICE);
        mLayoutParams = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                0, 0,
                PixelFormat.TRANSPARENT);
        mLayoutParams.type = getType();
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        mLayoutParams.gravity = Gravity.START | Gravity.TOP;
        mLayoutParams.x = 0;
        mLayoutParams.y = 0;
    }

    public static FloatingWindowManager getInstance() {
        return instance.INSTANCE;
    }

    public void addView(View view) {
        mWindowManager.addView(view, mLayoutParams);
    }

    public void removeView(View view) {
        mWindowManager.removeView(view);
    }

    private int getType() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            return WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
    }
}
