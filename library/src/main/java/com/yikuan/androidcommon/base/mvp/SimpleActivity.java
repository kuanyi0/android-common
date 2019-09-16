package com.yikuan.androidcommon.base.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Simple activity handle view and presenter
 *
 * @author yikuan
 * @date 2019/09/13
 */
public class SimpleActivity extends Activity {
    private SimpleContract.SimplePresenter mPresenter;

    private SimpleContract.SimpleView mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = new SimpleContract.SimpleView();
        mPresenter = new SimpleContract.SimplePresenter(mView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
