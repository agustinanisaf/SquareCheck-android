package com.squarecheck.login.contract;

import com.squarecheck.base.presenter.BasePresenter;
import com.squarecheck.base.view.BaseView;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void redirectToHome();
    }

    interface Presenter extends BasePresenter {
        void performLogin(String email, String pass);
    }
}
