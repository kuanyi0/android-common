package com.yikuan.androidcommon;

import com.yikuan.androidcommon.util.DateUtils;
import com.yikuan.androidcommon.util.Defaults;
import com.yikuan.androidcommon.util.ThreadPoolManager;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testDateUtils() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                String result = DateUtils.formatDateFileName();
                System.out.println(Thread.currentThread().getName() + " : " + result);
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                String result = DateUtils.formatTimeFileName();
                System.out.println(Thread.currentThread().getName() + " : " + result);
            }
        };
        Runnable r3 = new Runnable() {
            @Override
            public void run() {
                String result = DateUtils.formatDateFileName();
                System.out.println(Thread.currentThread().getName() + " : " + result);
            }
        };
        Runnable r4 = new Runnable() {
            @Override
            public void run() {
                String result = DateUtils.formatTimeFileName();
                System.out.println(Thread.currentThread().getName() + " : " + result);
            }
        };
        ThreadPoolManager.getInstance().execute(r1);
        ThreadPoolManager.getInstance().execute(r2);
        ThreadPoolManager.getInstance().execute(r3);
        ThreadPoolManager.getInstance().execute(r4);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDefaults() {
        assertEquals(false, Defaults.get(boolean.class));
        assertEquals('\0', Defaults.get(char.class).charValue());
        assertEquals(0, Defaults.get(byte.class).byteValue());
        assertEquals(0, Defaults.get(short.class).shortValue());
        assertEquals(0, Defaults.get(int.class).intValue());
        assertEquals(0, Defaults.get(long.class).longValue());
        assertEquals(0, Defaults.get(float.class), 0);
        assertEquals(0, Defaults.get(double.class), 0);
        assertNull(Defaults.get(String.class));
    }
}