package com.yikuan.androidcommon.base.observe;

/**
 * @author yikuan
 * @date 2019/09/19
 */
public class SimpleBaseObserveManager extends BaseObserveManager<SimpleContract.SimpleObserver, SimpleContract.SimpleObservable> {

    private SimpleBaseObserveManager() {

    }

    public void notifySimpleChanged(String arg) {
        mObservable.notifySimpleChanged(arg);
    }

    @Override
    protected SimpleContract.SimpleObservable createObservable() {
        return new SimpleContract.SimpleObservable();
    }
}
