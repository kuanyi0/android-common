package com.yikuan.androidcommon.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author yikuan
 * @date 2019/10/04
 */
public class IoUtils {
    private IoUtils() {
        throw new UnsupportedOperationException("cannot be instantiated.");
    }

    public static void close(Closeable... closeables) {
        if (closeables == null) {
            return;
        }
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void closeQuietly(Closeable... closeables) {
        if (closeables == null) {
            return;
        }
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException ingored) {

                }
            }
        }
    }
}
