package com.yikuan.androidcommon.util;

import java.lang.reflect.Array;

/**
 * @author yikuan
 * @date 2020/09/16
 */
public class Defaults {
    private Defaults() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(Class<T> cls) {
        if (cls == null || cls == void.class) {
            throw new IllegalArgumentException("cls must not be null or void");
        }
        return (T) Array.get(Array.newInstance(cls, 1), 0);
    }
}
