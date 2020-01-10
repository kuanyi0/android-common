package com.yikuan.androidcommon.base.mvp2;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author yikuan
 * @date 2019/09/18
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {
    protected T mPresenter;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    protected abstract T createPresenter();
}
