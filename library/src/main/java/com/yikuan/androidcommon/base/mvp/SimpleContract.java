package com.yikuan.androidcommon.base.mvp;

/**
 * @author yikuan
 * @date 2019/09/13
 */
public class SimpleContract {

    // Presenter implementation, it can be implemented out of SimpleContract.
    public static class SimplePresenter implements Presenter {
        private View mView;

        public SimplePresenter(View view) {
            mView = view;
            mView.setPresenter(this);
        }

        @Override
        public void doSomething() {
            // TODO: Something.
            mView.showSomething();
        }

        @Override
        public void onDestroy() {
            // TODO: Something on view destroy.
        }
    }

    // View implementation, it can be implemented by Activity、Fragment、Widget.
    public static class SimpleView implements View {
        private Presenter mPresenter;

        @Override
        public void showSomething() {
            // TODO: Show something.
            mPresenter.doSomething();
        }

        @Override
        public void setPresenter(Presenter presenter) {
            // TODO: Set SimplePresenter on view create.
            mPresenter = presenter;
        }
    }


    public interface Presenter extends BasePresenter {
        void doSomething();
    }

    public interface View extends BaseView<Presenter> {
        void showSomething();
    }
}
