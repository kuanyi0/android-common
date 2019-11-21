package com.yikuan.androidcommon.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

/**
 * @author yikuan
 * @date 2019/10/14
 */
public class BitmapUtils {

    private BitmapUtils() {
        throw new UnsupportedOperationException("cannot be instantiated.");
    }

    /**
     *
     * @param view whole shown
     * @return bitmap
     */
    public static Bitmap createBitmapByShownView(View view) {
        view.buildDrawingCache();
        return view.getDrawingCache();
    }

    /**
     *
     * @param view partial shown
     * @return bitmap
     */
    public static Bitmap createBitmapByPartialShownView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    /**
     *
     * @param view hidden
     * @param width bitmap width
     * @param height bitmap height
     * @return bitmap
     */
    public static Bitmap createBitmapByHiddenView(View view, int width, int height) {
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
        view.measure(measuredWidth, measuredHeight);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }
}
