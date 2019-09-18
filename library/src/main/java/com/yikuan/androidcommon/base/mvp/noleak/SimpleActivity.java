package com.yikuan.androidcommon.base.mvp.noleak;

/**
 * @author yikuan
 * @date 2019/09/18
 */
public class SimpleActivity extends BaseActivity<SimpleContract.SimpleView, SimpleContract.SimplePresenter>
        implements SimpleContract.SimpleView {

    @Override
    protected SimpleContract.SimplePresenter createPresenter() {
        return new SimpleContract.SimplePresenter();
    }

    @Override
    public void showSomething() {
        // todo show something
        mPresenter.doSomething();
    }
}
