package com.yikuan.androidcommon.util;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.yikuan.androidcommon.AndroidCommon;
import com.yikuan.androidcommon.ui.MovableLayout;

/**
 * @author yikuan
 * @date 2019/10/13
 */
public class FloatingWindowManager {
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;

    private static class Instance {
        private static final FloatingWindowManager INSTANCE = new FloatingWindowManager();
    }

    private FloatingWindowManager() {
        mWindowManager = (WindowManager) AndroidCommon.getApp().getSystemService(Context.WINDOW_SERVICE);
        mLayoutParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                0, 0,
                PixelFormat.TRANSPARENT);
        mLayoutParams.type = getType();
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        mLayoutParams.gravity = Gravity.CENTER;
        mLayoutParams.x = 0;
        mLayoutParams.y = 0;
    }

    public static FloatingWindowManager getInstance() {
        return Instance.INSTANCE;
    }

    public void addView(View view) {
        mWindowManager.addView(view, mLayoutParams);
    }

    public void addView(View view, WindowManager.LayoutParams params) {
        mWindowManager.addView(view, params);
    }

    public void addView(final MovableLayout movableLayout) {
        mWindowManager.addView(movableLayout, mLayoutParams);
        setMoveListener(movableLayout, mLayoutParams);
    }

    public void addView(final MovableLayout movableLayout, final WindowManager.LayoutParams params) {
        mWindowManager.addView(movableLayout, params);
        setMoveListener(movableLayout, params);
    }

    public void updateViewLayout(View view) {
        mWindowManager.updateViewLayout(view, mLayoutParams);
    }

    public void updateViewLayout(View view, WindowManager.LayoutParams params) {
        mWindowManager.updateViewLayout(view, params);
    }

    public void removeView(View view) {
        mWindowManager.removeView(view);
    }

    public int getType() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            return WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
    }

    public void setLayoutParams(WindowManager.LayoutParams layoutParams) {
        mLayoutParams = layoutParams;
    }

    public WindowManager.LayoutParams getLayoutParams() {
        return mLayoutParams;
    }

    private void setMoveListener(final MovableLayout movableLayout, final WindowManager.LayoutParams params) {
        movableLayout.setListener(new MovableLayout.Listener() {
            @Override
            public void onMove(int offsetX, int offsetY) {
                params.x += offsetX;
                params.y += offsetY;
                updateViewLayout(movableLayout, params);
            }
        });
    }
}
