package com.yikuan.androidcommon.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yikuan
 * @date 2019/09/22
 */
public class JsonUtils {
    private static final int TYPE_BOOLEAN = 1;
    private static final int TYPE_INT = 2;
    private static final int TYPE_LONG = 3;
    private static final int TYPE_DOUBLE = 4;
    private static final int TYPE_STRING = 5;
    private static final int TYPE_JSON_OBJECT = 6;
    private static final int TYPE_JSON_ARRAY = 7;

    private static Gson sGson;

    static {
        sGson = new Gson();
    }

    private JsonUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean getBoolean(String json, String key) {
        return getValue(json, key, TYPE_BOOLEAN, false);
    }

    public static boolean getBoolean(JSONObject jsonObject, String key) {
        return getValue(jsonObject, key, TYPE_BOOLEAN, false);
    }

    public static int getInt(String json, String key) {
        return getValue(json, key, TYPE_INT, 0);
    }

    public static int getInt(JSONObject jsonObject, String key) {
        return getValue(jsonObject, key, TYPE_INT, 0);
    }

    public static long getLong(String json, String key) {
        return getValue(json, key, TYPE_LONG, 0);
    }

    public static long getLong(JSONObject jsonObject, String key) {
        return getValue(jsonObject, key, TYPE_LONG, 0);
    }

    public static double getDouble(String json, String key) {
        return getValue(json, key, TYPE_DOUBLE, 0);
    }

    public static double getDouble(JSONObject jsonObject, String key) {
        return getValue(jsonObject, key, TYPE_DOUBLE, 0);
    }

    public static int getString(String json, String key) {
        return getValue(json, key, TYPE_STRING, null);
    }

    public static int getString(JSONObject jsonObject, String key) {
        return getValue(jsonObject, key, TYPE_BOOLEAN, null);
    }

    public static JSONObject getJSONObject(String json, String key) {
        return getValue(json, key, TYPE_JSON_OBJECT, null);
    }

    public static JSONObject getJSONObject(JSONObject jsonObject, String key) {
        return getValue(jsonObject, key, TYPE_JSON_OBJECT, null);
    }

    public static JSONArray getJSONArray(String json, String key) {
        return getValue(json, key, TYPE_JSON_ARRAY, null);
    }

    public static JSONArray getJSONArray(JSONObject jsonObject, String key) {
        return getValue(jsonObject, key, TYPE_JSON_ARRAY, null);
    }


    private static <T> T getValue(String json, String key, int type, T defValue) {
        if (TextUtils.isEmpty(json) || TextUtils.isEmpty(key)) {
            return defValue;
        }
        try {
            return getValue(new JSONObject(json), key, type, defValue);
        } catch (JSONException e) {
            e.printStackTrace();
            return defValue;
        }
    }


    @SuppressWarnings("unchecked")
    private static <T> T getValue(JSONObject jsonObject, String key, int type, T defValue) {
        if (jsonObject == null || TextUtils.isEmpty(key)) {
            return defValue;
        }
        Object value = defValue;
        try {
            switch (type) {
                case TYPE_BOOLEAN:
                    value = jsonObject.getBoolean(key);
                    break;
                case TYPE_INT:
                    value = jsonObject.getInt(key);
                    break;
                case TYPE_LONG:
                    value = jsonObject.getLong(key);
                    break;
                case TYPE_DOUBLE:
                    value = jsonObject.getDouble(key);
                    break;
                case TYPE_STRING:
                    value = jsonObject.getString(key);
                    break;
                case TYPE_JSON_OBJECT:
                    value = jsonObject.getJSONObject(key);
                    break;
                case TYPE_JSON_ARRAY:
                    value = jsonObject.getJSONArray(key);
                    break;
                default:
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return (T) value;
    }


    public static <T> T fromJson(String json, Class<T> clazz) {
        return sGson.fromJson(json, clazz);
    }

    public static <T> T fromJson(String json, Type type) {
        return sGson.fromJson(json, type);
    }

    public static String toJson(Object object) {
        return sGson.toJson(object);
    }

    public static String toJson(Object object, Type type) {
        return sGson.toJson(object, type);
    }

    public static Type getType(Type rawType, Type... typeArguments) {
        return TypeToken.getParameterized(rawType, typeArguments).getType();
    }

    public static Type getArray(Type type) {
        return TypeToken.getArray(type).getType();
    }

    public static Type getListType(Type type) {
        return TypeToken.getParameterized(List.class, type).getType();
    }

    public static Type getMapType(Type keyType, Type valueType) {
        return TypeToken.getParameterized(Map.class, keyType, valueType).getType();
    }

    public static Type getSetType(Type type) {
        return TypeToken.getParameterized(Set.class, type).getType();
    }
}
