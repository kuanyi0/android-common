package com.yikuan.androidcommon;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.yikuan.androidcommon.util.LogUtils;
import com.yikuan.androidcommon.util.ScreenUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private static final String TAG = "ExampleInstrumentedTest";

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.yikuan.androidcommon.test", appContext.getPackageName());
    }

    @Test
    public void testScreenUtils() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        AndroidCommon.init(appContext);
        LogUtils.d(TAG, "" + ScreenUtils.getScreenHeight());
    }
}
