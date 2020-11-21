package com.squarecheck.login.interactor;

import android.util.Log;

import com.squarecheck.base.util.SharedPreferencesUtil;
import com.squarecheck.login.api.LoginResponse;
import com.squarecheck.login.callback.RequestCallback;
import com.squarecheck.login.contract.LoginContract;
import com.squarecheck.login.model.Token;
import com.squarecheck.login.model.User;
import com.squarecheck.login.retrofit.LoginService;
import com.squarecheck.shared.retrofit.ServiceGenerator;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInteractor implements LoginContract.Interactor {

    private SharedPreferencesUtil sharedPreferencesUtil;

    public LoginInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestLogin(String username, String password, RequestCallback<LoginResponse> requestCallback) throws IOException {
        LoginService loginService = ServiceGenerator.createService(LoginService.class);
        Call<Token> call = loginService.login(new User(username, password));
        Token token = call.execute().body();
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful()) {
                    String currentToken = token.getToken().toString();
                    requestCallback.requestSuccess(new LoginResponse(true, currentToken, "Authentication Success"));
                    saveToken(currentToken);
                }
                else {
                    requestCallback.requestFailed("Authentication Failed");
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                requestCallback.requestFailed(t.getMessage());
                Log.d("Error", t.getMessage());
            }
        });
    }

    @Override
    public void saveToken(String token) {
        sharedPreferencesUtil.setToken(token);
    }
}
