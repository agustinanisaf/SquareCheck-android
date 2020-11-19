package com.squarecheck.login.retrofit;

import com.squarecheck.login.model.Token;
import com.squarecheck.login.model.User;
import com.squarecheck.shared.model.APIResponse;
import com.squarecheck.shared.model.APIResponseCollection;
import com.squarecheck.shared.retrofit.JWTAuthenticationInterceptor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

    private MockWebServer server = new MockWebServer();

    private LoginService loginService;

    private LoginService loginAuthenticatedService;

    private String authToken;

    @Before
    public void setup() throws IOException {
        server.start();

        authToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3RcL2FwaVwvYXV0aFwvbG9naW4iLCJpYXQiOjE2MDU3NTc3NDgsImV4cCI6MTYwNTc2MTM0OCwibmJmIjoxNjA1NzU3NzQ4LCJqdGkiOiIwVjhpeFljU0NvQ05tQ2tnIiwic3ViIjo1MSwicHJ2IjoiMjNiZDVjODk0OWY2MDBhZGIzOWU3MDFjNDAwODcyZGI3YTU5NzZmNyJ9.J01uqo9wuTpJ6PBxHi40uflnM0zoxN4ZX9Qg8c7bK9c";

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

    @After
    public void teardown() throws IOException {
        server.shutdown();
    }

    @Test
    public void testLogin() throws IOException {
        // Assign
        String responseBody = "{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3RcL2FwaVwvYXV0aFwvbG9naW4iLCJpYXQiOjE2MDU3NTc3NDgsImV4cCI6MTYwNTc2MTM0OCwibmJmIjoxNjA1NzU3NzQ4LCJqdGkiOiIwVjhpeFljU0NvQ05tQ2tnIiwic3ViIjo1MSwicHJ2IjoiMjNiZDVjODk0OWY2MDBhZGIzOWU3MDFjNDAwODcyZGI3YTU5NzZmNyJ9.J01uqo9wuTpJ6PBxHi40uflnM0zoxN4ZX9Qg8c7bK9c\",\"token_type\":\"bearer\",\"expires_in\":3600}";
        MockResponse response = new MockResponse()
                .setResponseCode(HTTP_OK)
                .setBody(responseBody);
        server.enqueue(response);

        User user = new User();
        user.setEmail("jenkins.ramon@example.com");
        user.setPassword("password");
        Call<Token> login = loginService.login(user);

        // Act
        Response<Token> tokenResponse = login.execute();

        // Assert
        assertEquals(response.toString().contains("200"), String.valueOf(tokenResponse.code()).contains("200"));
        assert tokenResponse.body() != null;
        assertEquals("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3RcL2FwaVwvYXV0aFwvbG9naW4iLCJpYXQiOjE2MDU3NTc3NDgsImV4cCI6MTYwNTc2MTM0OCwibmJmIjoxNjA1NzU3NzQ4LCJqdGkiOiIwVjhpeFljU0NvQ05tQ2tnIiwic3ViIjo1MSwicHJ2IjoiMjNiZDVjODk0OWY2MDBhZGIzOWU3MDFjNDAwODcyZGI3YTU5NzZmNyJ9.J01uqo9wuTpJ6PBxHi40uflnM0zoxN4ZX9Qg8c7bK9c", tokenResponse.body().getToken());
    }

    @Test
    public void testFailedLogin() throws IOException {
        // Assign
        String responseBody = "{\"code\":401,\"message\":\"Unauthorized\",\"description\":\"User unauthorized.\"}";
        MockResponse response = new MockResponse()
                .setResponseCode(HTTP_UNAUTHORIZED)
                .setBody(responseBody);
        server.enqueue(response);

        User user = new User("jenkins@gmail.com", "password");
        Call<Token> login = loginService.login(user);

        // Act
        Response<Token> tokenResponse = login.execute();

        // Assert
        assertEquals(response.toString().contains("401"), String.valueOf(tokenResponse.code()).contains("401"));
        assert tokenResponse.errorBody() != null;
        assertEquals(responseBody, tokenResponse.errorBody().string());
    }

    @Test
    public void testLogout() throws IOException {
        // Assign
        String responseBody = "{\"message\":\"Successfully logged out\"}";
        MockResponse response = new MockResponse()
                .addHeader("Authorization", "Bearer " + authToken)
                .setResponseCode(HTTP_OK)
                .setBody(responseBody);
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
        String responseBody = "{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9sb2NhbGhvc3RcL2FwaVwvYXV0aFwvbG9naW4iLCJpYXQiOjE2MDU3NTc3NDgsImV4cCI6MTYwNTc2MTM0OCwibmJmIjoxNjA1NzU3NzQ4LCJqdGkiOiIwVjhpeFljU0NvQ05tQ2tnIiwic3ViIjo1MSwicHJ2IjoiMjNiZDVjODk0OWY2MDBhZGIzOWU3MDFjNDAwODcyZGI3YTU5NzZmNyJ9.J01uqo9wuTpJ6PBxHi40uflnM0zoxN4ZX9Qg8c7bK9c\",\"token_type\":\"bearer\",\"expires_in\":3600}";
        MockResponse response = new MockResponse()
                .addHeader("Authorization", "Bearer " + authToken)
                .setResponseCode(HTTP_OK)
                .setBody(responseBody);
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
        String responseBody = "{\"data\":{\"id\":51,\"name\":\"Eluh Santoso\",\"email\":\"jenkins.ramon@example.com\",\"role\":\"student\",\"photo\":\"MLYQJG792q.png\"}}";
        MockResponse response = new MockResponse()
                .addHeader("Authorization", "Bearer " + authToken)
                .setResponseCode(HTTP_OK)
                .setBody(responseBody);
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