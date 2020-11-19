package com.squarecheck.login.presenter;

import com.squarecheck.login.contract.LoginContract;

public class LoginPresenter implements LoginContract.Presenter{
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    public void start() {}

    public void authenticate(String email, String password) {

    }
}
