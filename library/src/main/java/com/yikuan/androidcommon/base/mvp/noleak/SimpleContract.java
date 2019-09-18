package com.yikuan.androidcommon.base.mvp.noleak;

/**
 * @author yikuan
 * @date 2019/09/18
 */
public class SimpleContract {
    public static class SimplePresenter extends BasePresenter<SimpleView> {
        void doSomething() {
            // todo something
            getView().showSomething();
        }
    }

    public interface SimpleView {
        void showSomething();
    }

}
