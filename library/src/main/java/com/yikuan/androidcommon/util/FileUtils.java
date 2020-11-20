package com.yikuan.androidcommon.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author yikuan
 * @date 2019/10/04
 */
public class FileUtils {
    private FileUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static File getFile(File dir, String... names) {
        if (dir == null) {
            throw new NullPointerException("dir cannot be null");
        }
        if (names == null) {
            throw new NullPointerException("names cannot be null");
        }
        File file = dir;
        for (String name : names) {
            file = new File(file, name);
        }
        return file;
    }

    public static File getFile(String... names) {
        if (names == null) {
            throw new NullPointerException("names cannot be null");
        }
        File file = null;
        for (String name : names) {
            if (file == null) {
                file = new File(name);
            } else {
                file = new File(file, name);
            }
        }
        return file;
    }

    public static FileInputStream openInputStream(File file) throws IOException {
        if (file == null) {
            throw new NullPointerException("file cannot be null");
        }
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException(file + " is a directory");
            }
            if (!file.canRead()) {
                throw new IOException(file + " cannot be read");
            }
        } else {
            throw new IOException(file + "does not exist");
        }
        return new FileInputStream(file);
    }

    public static FileOutputStream openOutputStream(File file) throws IOException {
        return openOutputStream(file, false);
    }

    public static FileOutputStream openOutputStream(File file, boolean append) throws IOException {
        if (file == null) {
            throw new NullPointerException("file cannot be null");
        }
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException(file + " is a directory");
            }
            if (!file.canWrite()) {
                throw new IOException(file + " cannot be written");
            }
        } else {
            File parent = file.getParentFile();
            if (parent != null) {
                if (!parent.mkdirs() && !parent.isDirectory()) {
                    throw new IOException(parent + " cannot be created");
                }
            }
        }
        return new FileOutputStream(file, append);
    }

    public static void cleanDir(File dir) throws IOException {
        if (dir == null) {
            throw new NullPointerException("dir cannot be null");
        }
        if (!dir.exists()) {
            throw new IllegalArgumentException(dir + " does not exist");
        }
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(dir + " is not a directory");
        }
        File[] files = dir.listFiles();
        if (files == null) {
            throw new IOException("failed to list contents of " + dir);
        }
        IOException exception = null;
        for (File file : files) {
            try {
                forceDelete(file);
            } catch (IOException e) {
                exception = e;
            }
        }
        if (exception != null) {
            throw exception;
        }
    }

    public static void deleteDir(File dir) throws IOException {
        if (dir == null) {
            throw new NullPointerException("dir cannot be null");
        }
        if (!dir.exists()) {
            return;
        }
        cleanDir(dir);
        if (!dir.delete()) {
            throw new IOException("failed to delete dir " + dir);
        }
    }

    public static void forceDelete(File file) throws IOException {
        if (file == null) {
            throw new NullPointerException("file cannot be null");
        }
        if (file.isDirectory()) {
            deleteDir(file);
        } else {
            boolean fileExist = file.exists();
            if (!file.delete()) {
                if (!fileExist) {
                    throw new FileNotFoundException(file + " does not exist");
                }
                throw new IOException("failed to delete file " + file);
            }
        }
    }

    public static boolean deleteQuietly(File file) {
        if (file == null) {
            return false;
        }
        try {
            if (file.isDirectory()) {
                cleanDir(file);
            }
        } catch (Exception ignored) {

        }
        try {
            return file.delete();
        } catch (Exception ignored) {
            return false;
        }
    }

    public static void forceMkdir(File dir) throws IOException {
        if (dir == null) {
            throw new NullPointerException("dir cannot be null");
        }
        if (dir.exists()) {
            if (!dir.isDirectory()) {
                throw new IOException(dir + " is not a directory");
            }
        } else {
            if (!dir.mkdirs()) {
                if (!dir.isDirectory()) {
                    throw new IOException("failed to create directory " + dir);
                }
            }
        }
    }

    public static void forceCreateNewFile(File file) throws IOException {
        if (file == null) {
            throw new NullPointerException("file cannot be null");
        }
        if (file.exists()) {
            if (!file.isFile()) {
                throw new IOException(file + " is not a file");
            }
        } else {
            if (!file.createNewFile()) {
                if (!file.isFile()) {
                    throw new IOException("failed to create file " + file);
                }
            }
        }
    }

    public static long sizeOf(File file) {
        if (file == null) {
            throw new NullPointerException("file cannot be null");
        }
        if (!file.exists()) {
            throw new IllegalArgumentException(file + " does not exist");
        }
        if (file.isDirectory()) {
            return sizeOfDir(file);
        } else {
            return file.length();
        }
    }

    public static long sizeOfDir(File dir) {
        checkDir(dir);
        final File[] files = dir.listFiles();
        if (files == null) {
            return 0;
        }
        long size = 0;
        for (final File file : files) {
            size += sizeOf(file);
            if (size < 0) {
                break;
            }
        }
        return size;
    }

    public static void checkDir(File dir) {
        if (dir == null) {
            throw new NullPointerException("dir cannot be null");
        }
        if (!dir.exists()) {
            throw new IllegalArgumentException(dir + " does not exist");
        }
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(dir + " is not a directory");
        }
    }

    public static boolean exists(String path) {
        File file = new File(path);
        return file.exists();
    }

    public static boolean isFile(String path) {
        File file = new File(path);
        return file.isFile();
    }

    public static boolean isDir(String path) {
        File file = new File(path);
        return file.isDirectory();
    }

    public static boolean canRead(String path) {
        File file = new File(path);
        return file.canRead();
    }

    public static boolean canWrite(String path) {
        File file = new File(path);
        return file.canWrite();
    }

    public static boolean canExecute(String path) {
        File file = new File(path);
        return file.canExecute();
    }

    public static boolean rename(File file, String path) {
        File dest = new File(path);
        return file.renameTo(dest);
    }

    public static void copy(File src, File dest) throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = openInputStream(src).getChannel();
            outputChannel = openOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            IoUtils.close(inputChannel, outputChannel);
        }
    }

}
