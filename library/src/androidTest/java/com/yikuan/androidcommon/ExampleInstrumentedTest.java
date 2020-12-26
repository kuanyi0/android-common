package com.yikuan.androidcommon;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.yikuan.androidcommon.util.BitmapUtils;
import com.yikuan.androidcommon.util.LogDumper;
import com.yikuan.androidcommon.util.LogUtils;
import com.yikuan.androidcommon.util.PathUtils;
import com.yikuan.androidcommon.util.ScreenUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private static final String TAG = "ExampleInstrumentedTest";

    public ExampleInstrumentedTest() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        AndroidCommon.init(appContext);
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.yikuan.androidcommon.test", appContext.getPackageName());
    }

    @Test
    public void testScreenUtils() {
        LogUtils.d(TAG, String.valueOf(ScreenUtils.getScreenHeight()));
    }

    @Test
    public void testPathUtils() {
        LogUtils.d(TAG, PathUtils.getDownloadCachePath());
    }

    @Test
    public void testLogDumper() throws Exception {
        LogDumper.getInstance().start();
        Thread.sleep(3000);
        LogDumper.getInstance().stop();
    }

    @Test
    public void testLogUtils() {
        LogUtils.write(TAG, "message1");
        LogUtils.write(TAG, "message2");
        LogUtils.write(TAG, "message3");
    }

    @Test
    public void testBitmapUtils() {
        BitmapUtils.base64ToBitmap("");
    }
}
