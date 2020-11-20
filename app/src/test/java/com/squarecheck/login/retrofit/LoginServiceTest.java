package com.squarecheck.login.retrofit;

import com.squarecheck.MockResponseFileReader;
import com.squarecheck.login.model.Token;
import com.squarecheck.login.model.User;
import com.squarecheck.shared.model.APIResponse;
import com.squarecheck.shared.model.APIResponseCollection;
import com.squarecheck.shared.retrofit.JWTAuthenticationInterceptor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;
import static org.junit.Assert.assertEquals;

public class LoginServiceTest {

    private MockResponseFileReader fileReader = new MockResponseFileReader();

    private MockWebServer server = new MockWebServer();

    private LoginService loginService;

    private LoginService loginAuthenticatedService;

    private String authToken = fileReader.readJson("auth_token.txt");

    @BeforeEach
    public void setup() throws IOException {
        server.start();

        loginService = new Retrofit.Builder()
                .baseUrl(server.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build()
                .create(LoginService.class);

        loginAuthenticatedService = new Retrofit.Builder()
                .baseUrl(server.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().addInterceptor(new JWTAuthenticationInterceptor(authToken)).build())
                .build()
                .create(LoginService.class);
    }

    @AfterEach
    public void teardown() throws IOException {
        server.shutdown();
    }

    @Test
    public void login() throws IOException {
        // Assign
        String json = fileReader.readJson("login/login_success_response.json");
        assert json != null;
        MockResponse response = new MockResponse()
                .setResponseCode(HTTP_OK)
                .setBody(json);
        server.enqueue(response);

        User user = new User("jenkins.ramon@example.com", "password");
        Call<Token> login = loginService.login(user);

        // Act
        Response<Token> tokenResponse = login.execute();

        // Assert
        assertEquals(response.toString().contains("200"), String.valueOf(tokenResponse.code()).contains("200"));
        assert tokenResponse.body() != null;
        assertEquals(321, tokenResponse.body().getToken().length());
    }

    @Test
    public void failedLogin() throws IOException {
        // Assign
        String json = fileReader.readJson("login/login_failed_response.json");
        assert json != null;
        MockResponse response = new MockResponse()
                .setResponseCode(HTTP_UNAUTHORIZED)
                .setBody(json);
        server.enqueue(response);

        User user = new User("jenkins@gmail.com", "password");
        Call<Token> login = loginService.login(user);

        // Act
        Response<Token> tokenResponse = login.execute();

        // Assert
        assertEquals(response.toString().contains("401"), String.valueOf(tokenResponse.code()).contains("401"));
        assert tokenResponse.errorBody() != null;
        assertEquals(json, tokenResponse.errorBody().string());
    }

    @Test
    public void logout() throws IOException {
        // Assign
        String json = fileReader.readJson("login/logout_response.json");
        assert json != null;
        MockResponse response = new MockResponse()
                .addHeader("Authorization", "Bearer " + authToken)
                .setResponseCode(HTTP_OK)
                .setBody(json);
        server.enqueue(response);

        Call<APIResponse> logout = loginAuthenticatedService.logout();

        // Act
        Response<APIResponse> logoutResponse = logout.execute();

        // Assert
        assertEquals(response.getHeaders(), logoutResponse.headers());
        assertEquals(response.toString().contains("200"), String.valueOf(logoutResponse.code()).contains("200"));
    }

    @Test
    public void refresh() throws IOException {
        // Assign
        String json = fileReader.readJson("login/refresh_response.json");
        assert json != null;
        MockResponse response = new MockResponse()
                .addHeader("Authorization", "Bearer " + authToken)
                .setResponseCode(HTTP_OK)
                .setBody(json);
        server.enqueue(response);

        Call<Token> refresh = loginAuthenticatedService.refresh();

        // Act
        Response<Token> tokenResponse = refresh.execute();

        // Assert
        assertEquals(response.getHeaders(), tokenResponse.headers());
        assertEquals(response.toString().contains("200"), String.valueOf(tokenResponse.code()).contains("200"));
    }

    @Test
    public void me() throws IOException {
        // Assign
        String json = fileReader.readJson("login/me_response.json");
        assert json != null;
        MockResponse response = new MockResponse()
                .addHeader("Authorization", "Bearer " + authToken)
                .setResponseCode(HTTP_OK)
                .setBody(json);
        server.enqueue(response);

        Call<APIResponseCollection<User>> me = loginAuthenticatedService.me();

        // Act
        Response<APIResponseCollection<User>> meResponse = me.execute();

        // Assert
        assertEquals(response.getHeaders(), meResponse.headers());
        assert meResponse.body() != null;
        assertEquals("Eluh Santoso", meResponse.body().getData().getName());
        assertEquals(response.toString().contains("200"), String.valueOf(meResponse.code()).contains("200"));
    }
}