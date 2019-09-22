package com.yikuan.androidcommon.util;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;

import com.yikuan.androidcommon.AndroidCommon;

import java.util.Map;
import java.util.Set;

/**
 * @author yikuan
 * @date 2019/09/18
 */
public class SpUtils {
    private static SharedPreferences sPreferences;
    private static SharedPreferences.Editor sEditor;

    private static final boolean DEFAULT_BOOLEAN = false;
    private static final int DEFAULT_INT = 0;
    private static final float DEFAULT_FLOAT = 0f;
    private static final long DEFAULT_LONG = 0L;
    private static final String DEFAULT_STRING = null;
    private static final Set<String> DEFAULT_STRING_SET = null;

    static {
        sPreferences = PreferenceManager.getDefaultSharedPreferences(AndroidCommon.getApp());
        sEditor = sPreferences.edit();
    }

    private SpUtils() {
        throw new UnsupportedOperationException("cannot be instantiated.");
    }

    public static void setBoolean(String key, boolean value) {
        sEditor.putBoolean(key, value);
        sEditor.apply();
    }

    public static Boolean getBoolean(String key) {
        return getBoolean(key, DEFAULT_BOOLEAN);
    }

    public static Boolean getBoolean(String key, boolean defaultValue) {
        return sPreferences.getBoolean(key, defaultValue);
    }

    public static void setInt(String key, int value) {
        sEditor.putInt(key, value);
        sEditor.apply();
    }

    public static int getInt(String key) {
        return getInt(key, DEFAULT_INT);
    }

    public static int getInt(String key, int defaultValue) {
        return sPreferences.getInt(key, defaultValue);
    }

    public static void setFloat(String key, float value) {
        sEditor.putFloat(key, value);
        sEditor.apply();
    }

    public static float getFloat(String key) {
        return getFloat(key, DEFAULT_FLOAT);
    }

    public static float getFloat(String key, float defaultValue) {
        return sPreferences.getFloat(key, defaultValue);
    }

    public static void setLong(String key, long value) {
        sEditor.putLong(key, value);
        sEditor.apply();
    }

    public static long getLong(String key) {
        return getLong(key, DEFAULT_LONG);
    }

    public static long getLong(String key, long defaultValue) {
        return sPreferences.getLong(key, defaultValue);
    }

    public static void setString(String key, String value) {
        sEditor.putString(key, value);
        sEditor.apply();
    }

    public static String getString(String key) {
        return getString(key, DEFAULT_STRING);
    }

    public static String getString(String key, String defaultValue) {
        return sPreferences.getString(key, defaultValue);
    }

    public static void setStringSet(String key, Set<String> values) {
        sEditor.putStringSet(key, values);
        sEditor.apply();
    }

    public static Set<String> getDefaultStringSet(String key) {
        return getStringSet(key, DEFAULT_STRING_SET);
    }

    public static Set<String> getStringSet(String key, Set<String> defaultValues) {
        return sPreferences.getStringSet(key, defaultValues);
    }

    public static Map<String, ?> getAll() {
        return sPreferences.getAll();
    }

    public static void remove(String key) {
        sEditor.remove(key);
    }

    public static void clear() {
        sEditor.clear();
    }
}
