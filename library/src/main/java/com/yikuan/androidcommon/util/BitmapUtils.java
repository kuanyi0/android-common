package com.yikuan.androidcommon.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author yikuan
 * @date 2019/10/14
 */
public class BitmapUtils {

    private BitmapUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * @param view view show fully
     * @return bitmap
     */
    public static Bitmap createBitmap(View view) {
        view.buildDrawingCache();
        return view.getDrawingCache();
    }

    /**
     * @param view view maybe show partly
     * @return bitmap
     */
    public static Bitmap createBitmap2(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    /**
     * @param view   view maybe not show
     * @param width  bitmap width
     * @param height bitmap height
     * @return bitmap
     */
    public static Bitmap createBitmap3(View view, int width, int height) {
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
        view.measure(measuredWidth, measuredHeight);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    /**
     * @param drawable drawable
     * @return byte array
     */
    public static byte[] drawable2Bytes(Drawable drawable) {
        return bitmap2bytes(drawable2Bitmap(drawable));
    }

    /**
     * @param drawable drawable
     * @return bitmap
     */
    public static Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * @param base64 base64 string
     * @return bitmap
     */
    public static Bitmap base64ToBitmap(String base64) {
        if (base64.startsWith("data:")) {
            base64 = base64.substring(base64.indexOf(",") + 1);
        }
        try {
            byte[] bytes = Base64.decode(base64, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * @param bitmap bitmap
     * @return byte array
     */
    public static byte[] bitmap2bytes(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        byte[] bytes = outputStream.toByteArray();
        IoUtils.close(outputStream);
        return bytes;
    }

    /**
     * @param bitmap bitmap
     * @return base64 string
     */
    public static String bitmap2Base64(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        byte[] bytes = outputStream.toByteArray();
        IoUtils.close(outputStream);
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    /**
     * @param drawable drawable
     * @return bitmap
     */
    public static String drawable2Base64(Drawable drawable) {
        Bitmap bitmap = drawable2Bitmap(drawable);
        return bitmap2Base64(bitmap);
    }

    /**
     * @param bitmap bitmap
     * @param path   save path
     * @return {@code true} if success, {@code false} otherwise
     */
    public static boolean saveBitmap2Png(Bitmap bitmap, String path) {
        return saveBitmap2File(bitmap, path, Bitmap.CompressFormat.PNG, 100);
    }

    /**
     * @param bitmap bitmap
     * @param path   save path
     * @return {@code true} if success, {@code false} otherwise
     */
    public static boolean saveBitmap2Jpeg(Bitmap bitmap, String path) {
        return saveBitmap2File(bitmap, path, Bitmap.CompressFormat.JPEG, 100);
    }

    /**
     * @param bitmap         bitmap
     * @param path           save path
     * @param compressFormat compress format
     * @param quality        compress quality, value is 0-100
     * @return {@code true} if success, {@code false} otherwise
     */
    public static boolean saveBitmap2File(Bitmap bitmap, String path, Bitmap.CompressFormat compressFormat, int quality) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            bitmap.compress(compressFormat, quality, fos);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            IoUtils.close(fos);
        }
    }
}
