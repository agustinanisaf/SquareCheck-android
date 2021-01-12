package com.squarecheck.login.interactor;

import com.squarecheck.base.util.UtilProvider;
import com.squarecheck.login.contract.LoginContract;
import com.squarecheck.login.model.Token;
import com.squarecheck.login.model.User;
import com.squarecheck.login.retrofit.LoginService;
import com.squarecheck.shared.api_response.APIResponse;
import com.squarecheck.shared.api_response.APIResponseCollection;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.shared.callback.RetrofitCallback;
import com.squarecheck.shared.retrofit.ServiceGenerator;
import com.squarecheck.shared.util.TokenUtil;
import com.squarecheck.shared.util.UserUtil;

import retrofit2.Call;

public class LoginInteractor implements LoginContract.Interactor {
    private static final String TAG = LoginInteractor.class.getSimpleName();
    private final LoginService service;

    public LoginInteractor() {
        service = ServiceGenerator.createService(LoginService.class);
    }

    @Override
    public void requestLogin(String email, String pass, RequestCallback<Token> callback) {
        Call<Token> call = service.login(new User(email, pass));
        call.enqueue(new RetrofitCallback<>(callback, TAG, "requestLogin"));
    }

    @Override
    public void requestSaveToken(Token token) {
        TokenUtil tokenUtil = (TokenUtil) UtilProvider.getUtil(TokenUtil.class);
        tokenUtil.initialize(token);
    }

    @Override
    public void requestUser(Token token, RequestCallback<User> callback) {
        LoginService service = ServiceGenerator.createService(LoginService.class, token.getToken());
        Call<APIResponseCollection<User>> call = service.me();
        call.enqueue(new RetrofitCallback<>(callback, TAG, "requestUser"));
    }

    @Override
    public void requestSaveUser(User user) {
        UserUtil userUtil = (UserUtil) UtilProvider.getUtil(UserUtil.class);
        userUtil.initialize(user);
    }

    @Override
    public void requestRegisterFCMToken(Token apiToken, Token fcmToken, RequestCallback<APIResponse> callback) {
//        LoginService registerService = ServiceGenerator.createService(LoginService.class, apiToken.getToken());
//        Call<APIResponse> call = registerService.registerFCMToken(fcmToken);
//        call.enqueue(new Callback<APIResponse>() {
//            @Override
//            public void onResponse(@NonNull Call<APIResponse> call, @NonNull Response<APIResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    callback.requestSuccess(response.body());
//                } else {
//                    APIResponse apiResponse = ErrorUtil.parseError(response);
//                    Log.d(TAG, "registerFCMToken: onResponse: failed: " + apiResponse.getDescription());
//                    callback.requestError(apiResponse.getDescription());
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<APIResponse> call, @NonNull Throwable t) {
//                Log.d(TAG, "registerFCMToken: onFailure: failure: " + t.getMessage());
//                callback.requestError(t.getMessage());
//            }
//        });
    }
}
