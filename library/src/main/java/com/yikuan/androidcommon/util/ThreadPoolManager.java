package com.yikuan.androidcommon.util;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yikuan
 * @date 2019/09/13
 */
public class ThreadPoolManager {
    private static final String TAG = "ThreadPoolManager";

    private ThreadPoolExecutor mExecutor;

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAXIMUM_POOL_SIZE = CORE_POOL_SIZE * 2 + 1;
    private static final int KEEP_ALIVE = 10;

    private static final BlockingDeque<Runnable> sQueue = new LinkedBlockingDeque<>(128);

    private static final ThreadFactory sFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, TAG + " #" + mCount.getAndDecrement());
        }
    };

    private static final RejectedExecutionHandler sHandler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            throw new RejectedExecutionException(TAG + " task " + runnable.toString() +
                    " rejected from " + threadPoolExecutor.toString());
        }
    };

    private static class Instance {
        private static final ThreadPoolManager INSTANCE = new ThreadPoolManager();
    }

    private ThreadPoolManager() {
        mExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE,
                KEEP_ALIVE, TimeUnit.SECONDS, sQueue, sFactory, sHandler);
    }

    public static ThreadPoolManager getInstance() {
        return Instance.INSTANCE;
    }

    public void execute(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        mExecutor.execute(runnable);
    }

    public Future<?> submit(Runnable runnable) {
        if (runnable == null) {
            return null;
        }
        return mExecutor.submit(runnable);
    }

    public void remove(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        mExecutor.remove(runnable);
    }


    private ExecutorService mFixedThreadPool;
    private ExecutorService mCachedThreadPool;
    private ScheduledExecutorService mScheduledThreadPool;
    private ExecutorService mSingleThreadPool;

    public ExecutorService getFixedThreadPool() {
        if (mFixedThreadPool == null) {
            mFixedThreadPool = Executors.newFixedThreadPool(CORE_POOL_SIZE, sFactory);
        }
        return mFixedThreadPool;
    }

    public ExecutorService getCachedThreadPool() {
        if (mCachedThreadPool == null) {
            mCachedThreadPool = Executors.newCachedThreadPool(sFactory);
        }
        return mCachedThreadPool;
    }

    public ScheduledExecutorService getScheduledThreadPool() {
        if (mScheduledThreadPool == null) {
            mScheduledThreadPool = Executors.newScheduledThreadPool(CORE_POOL_SIZE, sFactory);
        }
        return mScheduledThreadPool;
    }

    public ExecutorService getSingleThreadPool() {
        if (mSingleThreadPool == null) {
            mSingleThreadPool = Executors.newSingleThreadExecutor(sFactory);
        }
        return mSingleThreadPool;
    }

}
