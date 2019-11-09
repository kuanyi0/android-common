package com.yikuan.androidcommon.base.mvp.softview;

/**
 * @author yikuan
 * @date 2019/09/18
 */
public class SimpleContract {
    public static class SimplePresenter extends BasePresenter<SimpleView> {
        void doSomething() {
            // TODO: something
            getView().showSomething();
        }
    }

    public interface SimpleView {
        void showSomething();
    }

}
