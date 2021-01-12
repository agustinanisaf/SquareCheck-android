package com.squarecheck.login.contract;

import com.squarecheck.base.presenter.BasePresenter;
import com.squarecheck.base.view.BaseView;
import com.squarecheck.login.model.Token;
import com.squarecheck.login.model.User;
import com.squarecheck.shared.api_response.APIResponse;
import com.squarecheck.shared.callback.RequestCallback;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void redirectToHome(String role);
    }

    interface Presenter extends BasePresenter {
        void authenticate(String email, String pass);

        void saveToken(Token apiToken);

        void requestUser(Token apiToken);

        void saveUser(User user);

        void registerFCMToken(Token apiToken);
    }

    interface Interactor {
        void requestLogin(String email, String pass, RequestCallback<Token> callback);

        void requestSaveToken(Token token);

        void requestUser(Token token, RequestCallback<User> callback);

        void requestSaveUser(User user);

        void requestRegisterFCMToken(Token apiToken, Token fcmToken, RequestCallback<APIResponse> callback);
    }
}
