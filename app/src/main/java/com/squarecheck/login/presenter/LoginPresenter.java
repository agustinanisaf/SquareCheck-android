package com.squarecheck.login.presenter;

import android.util.Log;

import com.squarecheck.login.contract.LoginContract;

public class LoginPresenter implements LoginContract.Presenter {
    private static final String TAG = LoginPresenter.class.getSimpleName();
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    public void start() {
        view.initView();
    }

    public void authenticate(String email, String password) {
        Log.d(TAG, "authenticate: { email: " + email + ", password: " + password + " }");
    }
}
