package com.squarecheck.login.contract;

import com.squarecheck.base.presenter.BasePresenter;
import com.squarecheck.base.view.BaseView;
import com.squarecheck.login.callback.RequestCallback;
import com.squarecheck.login.api.LoginResponse;

import java.io.IOException;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void redirectToHome();
    }

    interface Presenter extends BasePresenter {
        void performLogin(String email, String pass);
    }

    interface Interactor {
        void requestLogin(String username, String password, RequestCallback<LoginResponse> requestCallback) throws IOException;
        void saveToken(String token);
    }
}
