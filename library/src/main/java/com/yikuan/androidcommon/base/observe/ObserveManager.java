package com.yikuan.androidcommon.base.observe;

/**
 * @author yikuan
 * @date 2019/09/19
 */
public abstract class ObserveManager<T extends BaseObserver, V extends BaseObservable<T>> {
    protected final V mObservable;

    protected ObserveManager() {
        mObservable = createObserve();
    }

    public void registerObserver(T observer) {
        mObservable.registerObserver(observer);
    }

    public void unregisterObserver(T observer) {
        mObservable.unregisterObserver(observer);
    }

    public void unregisterAll() {
        mObservable.unregisterAll();
    }

    public void notifyChanged() {
        mObservable.notifyChanged();
    }

    protected abstract V createObserve();

}
