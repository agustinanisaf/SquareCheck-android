package com.squarecheck.login.contract;

import com.squarecheck.base.presenter.BasePresenter;
import com.squarecheck.base.view.BaseView;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void startLoading();

        void stopLoading();

        void showError(String errorMessage);

        void redirectToHome(String role);
    }

    interface Presenter extends BasePresenter {
        void authenticate(String email, String pass);
    }
}
