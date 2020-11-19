package com.squarecheck.shared.retrofit;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class JWTAuthenticationInterceptor implements Interceptor {
    private String token;

    public JWTAuthenticationInterceptor(String token) {
        this.token = token;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
                .header("Authorization", token);

        Request request = builder.build();
        return chain.proceed(request);
    }
}
