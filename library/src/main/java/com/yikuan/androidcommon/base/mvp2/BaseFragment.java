package com.yikuan.androidcommon.base.mvp2;

import android.content.Context;
import androidx.fragment.app.Fragment;

/**
 * @author yikuan
 * @date 2019/09/18
 */
public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {
    protected T mPresenter;

    @SuppressWarnings("unchecked")
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.detachView();
    }

    protected abstract T createPresenter();
}
