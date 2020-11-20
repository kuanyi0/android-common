package com.yikuan.androidcommon.util;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author yikuan
 * @date 2019/10/04
 */
public class IoUtils {
    private IoUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static String readTextFile(String path) {
        FileInputStream fis = null;
        String result = null;
        try {
            fis = new FileInputStream(path);
            int size = fis.available();
            byte[] bytes = new byte[size];
            fis.read(bytes);
            result = new String(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(fis);
        }
        return result;
    }

    public static boolean writeTextFile(String path, String content, boolean append) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path, append);
            byte[] bytes = content.getBytes();
            fos.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(fos);
        }
        return true;
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
                } catch (IOException ignored) {

                }
            }
        }
    }
}
