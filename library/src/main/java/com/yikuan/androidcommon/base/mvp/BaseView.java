package com.yikuan.androidcommon.base.mvp;

/**
 * @author yikuan
 * @date 2019/09/08
 */
public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
}
