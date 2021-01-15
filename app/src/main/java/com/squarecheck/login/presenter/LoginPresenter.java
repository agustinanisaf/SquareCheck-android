package com.squarecheck.login.presenter;

import android.util.Log;

import com.squarecheck.base.util.UtilProvider;
import com.squarecheck.login.contract.LoginContract;
import com.squarecheck.login.interactor.LoginInteractor;
import com.squarecheck.login.model.Token;
import com.squarecheck.login.model.User;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.shared.util.TokenUtil;

public class LoginPresenter implements LoginContract.Presenter {
    private static final String TAG = LoginPresenter.class.getSimpleName();
    private final LoginInteractor loginInteractor;
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view, LoginInteractor loginInteractor) {
        this.view = view;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void start() {
        checkIfAlreadyLogin();
        view.initView();
    }

    @Override
    public void authenticate(String email, String password) {
        Log.d(TAG, "authenticate: { email: " + email + ", password: " + password + " }");
        view.startLoading();
        loginInteractor.requestLogin(email, password, new RequestCallback<Token>() {
            @Override
            public void requestSuccess(Token data) {
                saveToken(data);
                registerFCMToken(data);
                requestUser(data);
                view.endLoading();
            }

            @Override
            public void requestError(String message) {
                view.endLoading();
                view.showError(message);
            }
        });
    }

    @Override
    public void saveToken(Token apiToken) {
        Log.d(TAG, "saveToken: Save token to SharedPrefs");
        loginInteractor.requestSaveToken(apiToken);
    }

    @Override
    public void requestUser(Token apiToken) {
        Log.d(TAG, "requestUser: Request user from API");
        loginInteractor.requestUser(apiToken, new RequestCallback<User>() {
            @Override
            public void requestSuccess(User data) {
                saveUser(data);
                view.redirectToHome(data.getRole());
            }

            @Override
            public void requestError(String message) {
                view.showError(message);
            }
        });
    }

    @Override
    public void saveUser(User user) {
        Log.d(TAG, "saveUser: Save user to SharedPrefs");
        loginInteractor.requestSaveUser(user);
    }

    @Override
    public void registerFCMToken(Token apiToken) {
        Log.d(TAG, "registerFCMToken: Get Device FCM Token and send back to server");
        // loginInteractor.requestRegisterFCMToken(apiToken, );
    }

    public void checkIfAlreadyLogin(){
        Token token = ((TokenUtil) UtilProvider.getUtil(TokenUtil.class)).getSessionData();

        if(token != null){
            this.requestUser(token);
        }
    }
}
