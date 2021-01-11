package com.squarecheck.base.view;

public interface BaseView<T> {
    void setPresenter(T presenter);

    void initView();

    void startLoading();

    void endLoading();

    void showError(String message);
}
