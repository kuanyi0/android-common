package com.yikuan.androidcommon.util;


import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author yikuan
 * @date 2019/10/04
 */
public class DateUtils {
    private static final Map<String, ThreadLocal<SimpleDateFormat>> sMap = new HashMap<>();

    private static final String PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    private static final String PATTERN_DATE_FILE_NAME = "yyyyMMdd";
    private static final String PATTERN_TIME_FILE_NAME = "yyyyMMddHHmmsss";

    private DateUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static String format(Date date) {
        return format(date, PATTERN_DEFAULT);
    }

    public static String format(Date date, String pattern) {
        return getSdf(pattern).format(date);
    }

    public static String format(long date) {
        return format(date, PATTERN_DEFAULT);
    }

    public static String format(long date, String pattern) {
        return getSdf(pattern).format(new Date(date));
    }

    public static String formatDateFileName() {
        return getSdf(PATTERN_DATE_FILE_NAME).format(new Date());
    }

    public static String formatTimeFileName() {
        return getSdf(PATTERN_TIME_FILE_NAME).format(new Date());
    }

    public static Date parse(String dateStr) throws ParseException {
        return parse(dateStr, PATTERN_DEFAULT);
    }

    public static Date parse(String dateStr, String pattern) throws ParseException {
        return getSdf(pattern).parse(dateStr);
    }

    private static SimpleDateFormat getSdf(final String pattern) {
        if (pattern == null) {
            throw new NullPointerException("pattern cannot be null");
        }
        ThreadLocal<SimpleDateFormat> threadLocal = sMap.get(pattern);
        if (threadLocal == null) {
            synchronized (DateUtils.class) {
                threadLocal = sMap.get(pattern);
                if (threadLocal == null) {
                    threadLocal = new ThreadLocal<SimpleDateFormat>() {
                        @Nullable
                        @Override
                        protected SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(pattern, Locale.getDefault());
                        }
                    };
                    sMap.put(pattern, threadLocal);
                }
            }
        }
        return threadLocal.get();
    }
}
