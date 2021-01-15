package com.squarecheck.login.retrofit;

import com.squarecheck.login.model.Token;
import com.squarecheck.login.model.User;
import com.squarecheck.shared.api_response.APIResponse;
import com.squarecheck.shared.api_response.APIResponseCollection;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("auth/login")
    Call<Token> login(@Body User user);

    @POST("auth/logout")
    Call<APIResponse> logout();

    @POST("auth/refresh")
    Call<Token> refresh();

    @POST("auth/me")
    Call<APIResponseCollection<User>> me();
}
