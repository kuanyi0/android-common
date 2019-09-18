package com.yikuan.androidcommon.base.observe;

/**
 * @author yikuan
 * @date 2019/09/19
 */
public class SimpleContract {

    /**
     * 被观察者
     */
    public static class SimpleObservable extends BaseObservable<SimpleObserver> {
        /**
         * 被观察者发布通知
         * @param arg
         */
        public void notifySimpleChanged(String arg) {
            synchronized (mObservers) {
                for (SimpleObserver observer : mObservers) {
                    observer.onSimpleChanged(arg);
                }
            }
        }
    }

    /**
     * 观察者
     */
    public interface SimpleObserver extends BaseObserver {
        /**
         * 观察者收到通知后调用
         * @param arg
         */
        void onSimpleChanged(String arg);
    }

}
