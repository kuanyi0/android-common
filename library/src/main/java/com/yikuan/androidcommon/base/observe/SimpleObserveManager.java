package com.yikuan.androidcommon.base.observe;

/**
 * @author yikuan
 * @date 2019/09/19
 */
public class SimpleObserveManager extends ObserveManager<SimpleContract.SimpleObserver, SimpleContract.SimpleObservable> {

    private SimpleObserveManager() {

    }

    public void notifySimpleChanged(String arg) {
        mObservable.notifySimpleChanged(arg);
    }

    @Override
    protected SimpleContract.SimpleObservable createObserve() {
        return new SimpleContract.SimpleObservable();
    }
}
