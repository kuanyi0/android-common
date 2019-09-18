package com.yikuan.androidcommon.base.observe;

import android.database.Observable;

/**
 * @author yikuan
 * @date 2019/09/19
 */
public abstract class BaseObservable<T extends BaseObserver> extends Observable<T> {

    public void notifyChanged() {
        synchronized (mObservers) {
            for (T observer : mObservers) {
                observer.onChanged();
            }
        }
    }

}
